package com.co.prueba.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.TarjetaDTO;
import com.co.prueba.dto.TipoTransaccionDTO;
import com.co.prueba.dto.TransaccionDTO;
import com.co.prueba.dto.rest.AnularTransaccionDTOIn;
import com.co.prueba.dto.rest.EntityResponse;
import com.co.prueba.dto.rest.TransaccionDTOIn;
import com.co.prueba.dto.rest.TransaccionDTOOut;
import com.co.prueba.entity.Transaccion;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.TransaccionMapper;
import com.co.prueba.repository.TransaccionRepository;
import com.co.prueba.utils.Constantes;
import com.co.prueba.utils.Utils;

@Service
public class TransaccionService {
	
	 @Autowired
	private TransaccionRepository repository;
	 
	 @Autowired
	 private TipoTransaccionService tipoTransaccionService;
	 
	 @Autowired
	  private TarjetaService tarjetaService;
	 
	 @Autowired
	 private TransaccionMapper mapper;
	 
	 public ResponseEntity<EntityResponse<TransaccionDTOOut>> agregarTransaccion(TransaccionDTOIn transaccionDtoIn, String tipoTransaccion){
		 EntityResponse<TransaccionDTOOut> entityResponse = new EntityResponse<>();
		 try {
			 TipoTransaccionDTO tipoTransaccionDTO = tipoTransaccionService.findByTipo(tipoTransaccion);
			 TarjetaDTO tarjetaDTO = tarjetaService.findTarjetaByNumero(transaccionDtoIn.getCardId());
			 if(Objects.isNull(tarjetaDTO)) {
				 entityResponse.setMessage("No se encontro la tarjeta de numero: ".concat(transaccionDtoIn.getCardId().toString()));
					return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			 }
			 if(!tarjetaDTO.isActiva()) {
				 entityResponse.setMessage("La tarjeta se encuentra inactiva");
				 return new ResponseEntity<>(entityResponse, HttpStatus.CONFLICT);
			 }
			 if(tarjetaDTO.isBloqueada()) {
				 entityResponse.setMessage("La tarjeta se encuentra bloqueada");
				 return new ResponseEntity<>(entityResponse, HttpStatus.CONFLICT);
			 }
			 if(Constantes.TIPO_TRANSACCION_COMPRA.equals(tipoTransaccion)) {
				 if(0>=tarjetaDTO.getSaldo().compareTo(transaccionDtoIn.getBalance())) {
					 entityResponse.setMessage("Saldo insuficiente");
					 return new ResponseEntity<>(entityResponse, HttpStatus.CONFLICT);
				 }
				 tarjetaDTO.setSaldo(tarjetaDTO.getSaldo().subtract(transaccionDtoIn.getBalance()));
			 }
			 TransaccionDTO transaccionDTO = new TransaccionDTO();
			 transaccionDTO.setValor(transaccionDtoIn.getBalance());
			 transaccionDTO.setFechaTransaccion(new Date());
			 transaccionDTO.setHabilitada(true);
			 
			 Transaccion transaccionSave = repository.save(mapper.mapperTransaccionDTOToTransaccion(transaccionDTO, tarjetaService.actualizarTarjeta(tarjetaDTO), tipoTransaccionDTO));
			 entityResponse.setBody(mapper.mapperInTransaccionToTransaccionDTOOut(transaccionSave));
			 entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			 tarjetaService.actualizarTarjeta(tarjetaDTO);
			 return ResponseEntity.ok(entityResponse);
		 }catch(BankIncException e) {
			 entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			 return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	 
	 public ResponseEntity<EntityResponse<TransaccionDTOOut>> consultarTransaccion(Long transactionId){
		 EntityResponse<TransaccionDTOOut> entityResponse = new EntityResponse<>();
		 try {
			 Transaccion transaccionFind = repository.findById(transactionId).orElse(null);
			 if(Objects.isNull(transaccionFind)) {
				 entityResponse.setMessage("No se encontro la transacción de numero: ".concat(transactionId.toString()));
					return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			 }
			 entityResponse.setBody(mapper.mapperInTransaccionToTransaccionDTOOut(transaccionFind));
			 entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			 return ResponseEntity.ok(entityResponse);
		 }catch(BankIncException e) {
			 entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			 return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }

	 public ResponseEntity<EntityResponse<HttpStatus>> anularTransaccion(AnularTransaccionDTOIn anularTransaccionDTOIn){
		 EntityResponse<HttpStatus> entityResponse = new EntityResponse<>();
		 try {
			 Transaccion transaccionFind = repository.findById(anularTransaccionDTOIn.getTransactionId()).orElse(null);
			 TarjetaDTO tarjetaDTO = tarjetaService.findTarjetaByNumero(anularTransaccionDTOIn.getCardId());
			 if(Objects.isNull(tarjetaDTO)) {
				 entityResponse.setMessage("No se encontro la tarjeta de numero: ".concat(anularTransaccionDTOIn.getCardId().toString()));
					return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			 }
			 if(Objects.isNull(transaccionFind)) {
				 entityResponse.setMessage("No se encontro la transacción de numero: ".concat(anularTransaccionDTOIn.getTransactionId().toString()));
				 return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			 }
			 if(!validateDate(transaccionFind.getFechaTransaccion())) {
				 entityResponse.setMessage("Transacción fuera de fecha valida");
				 return new ResponseEntity<>(entityResponse, HttpStatus.CONFLICT);
			 }
			 tarjetaDTO.setSaldo(tarjetaDTO.getSaldo().add(transaccionFind.getValor()));
			 tarjetaService.actualizarTarjeta(tarjetaDTO);
			 entityResponse.setBody(HttpStatus.OK);
			 entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			 return ResponseEntity.ok(entityResponse);
		 }catch(BankIncException e) {
			 entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			 return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	 
	 private boolean validateDate(Date transaction) {
		 Date dateNow = new Date();
		 LocalDateTime minus = LocalDateTime.ofInstant(dateNow.toInstant(), ZoneId.systemDefault()).minusDays(1);
		 Date lessDay = Date.from(minus.atZone(ZoneId.systemDefault()).toInstant());
		 return (0<transaction.compareTo(lessDay)) && (0>transaction.compareTo(dateNow));
	 }
	 
}
