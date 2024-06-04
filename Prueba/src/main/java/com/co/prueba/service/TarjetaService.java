package com.co.prueba.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.ClienteDTO;
import com.co.prueba.dto.TarjetaDTO;
import com.co.prueba.dto.TipoProductoDTO;
import com.co.prueba.dto.rest.ActivarTarjetaDTOIn;
import com.co.prueba.dto.rest.EntityResponse;
import com.co.prueba.dto.rest.NumeroTarjetaDTOOut;
import com.co.prueba.dto.rest.TarjetaDTOOut;
import com.co.prueba.dto.rest.TransaccionDTOIn;
import com.co.prueba.entity.Tarjeta;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.TarjetaMapper;
import com.co.prueba.repository.TarjetaRepository;
import com.co.prueba.utils.Utils;

@Service
public class TarjetaService {

	@Autowired
	private TarjetaRepository repository;
	
	
	@Autowired
	private TarjetaMapper mapper;
	
	@Autowired
	private TipoProductoService tipoProductoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public ResponseEntity<EntityResponse<NumeroTarjetaDTOOut>> generarNumeroTarjeta(Long productId) {
		EntityResponse<NumeroTarjetaDTOOut> entityResponse = new EntityResponse<>();
		try {
			Long numeroTarjeta = 0L;
			TipoProductoDTO tipoProductoDto = tipoProductoService.findtipoProductoById(productId);
			Long numeroProducto = tipoProductoDto.getNumeroProducto();
			boolean ifExist = true;
			while(ifExist) {
				numeroTarjeta = generarValorNumeroTarjeta(numeroProducto);
				ifExist = Objects.nonNull(findTarjetaByNumero(numeroTarjeta));
			}
			NumeroTarjetaDTOOut numeroTarjetaDTOOut = new NumeroTarjetaDTOOut();
			numeroTarjetaDTOOut.setNumeroTarjeta(numeroTarjeta);
			entityResponse.setBody(numeroTarjetaDTOOut);
			entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			return ResponseEntity.ok(entityResponse);
		}catch(BankIncException e) {
			entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<EntityResponse<TarjetaDTOOut>> activarTarjeta(ActivarTarjetaDTOIn activarTarjetaDTOIn){
		EntityResponse<TarjetaDTOOut> entityResponse = new EntityResponse<>();
		try {
			TarjetaDTO tarjetaDTO = findTarjetaByNumero(activarTarjetaDTOIn.getCardId());
			if(Objects.isNull(tarjetaDTO)) {
				entityResponse.setMessage("No se encontro la tarjeta de numero: ".concat(activarTarjetaDTOIn.getCardId().toString()));
				return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			}
			tarjetaDTO.setActiva(true);
			entityResponse.setBody(actualizarTarjetaDTOOut(tarjetaDTO));
			entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			return ResponseEntity.ok(entityResponse);
		}catch(BankIncException e) {
			entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<EntityResponse<HttpStatus>> bloquearTarjeta(Long cardId){
		EntityResponse<HttpStatus> entityResponse = new EntityResponse<>();
		try {
			TarjetaDTO tarjetaDTO = findTarjetaByNumero(cardId);
			if(Objects.isNull(tarjetaDTO)) {
				entityResponse.setMessage("No se encontro la tarjeta de numero: ".concat(cardId.toString()));
				return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			}
			tarjetaDTO.setBloqueada(true);
			actualizarTarjetaDto(tarjetaDTO);
			entityResponse.setBody(HttpStatus.OK);
			entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			return ResponseEntity.ok(entityResponse);
		}catch(BankIncException e) {
			entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<EntityResponse<TarjetaDTOOut>> recagarSaldo(TransaccionDTOIn recargaSaldoDTOIn){
		EntityResponse<TarjetaDTOOut> entityResponse = new EntityResponse<>();
		try {
			TarjetaDTO tarjetaDTO = findTarjetaByNumero(recargaSaldoDTOIn.getCardId());
			if(Objects.isNull(tarjetaDTO)) {
				entityResponse.setMessage("No se encontro la tarjeta de numero: ".concat(recargaSaldoDTOIn.getCardId().toString()));
				return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			}
			tarjetaDTO.setSaldo(tarjetaDTO.getSaldo().add(recargaSaldoDTOIn.getBalance()));
			entityResponse.setBody(actualizarTarjetaDTOOut(tarjetaDTO));
			entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			return ResponseEntity.ok(entityResponse);
		}catch(BankIncException e) {
			entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<EntityResponse<TarjetaDTOOut>> consultarSaldo(Long cardId){
		EntityResponse<TarjetaDTOOut> entityResponse = new EntityResponse<>();
		try {
			Tarjeta tarjeta = repository.findById(cardId).orElse(null);
			if(Objects.isNull(tarjeta)) {
				entityResponse.setMessage("No se encontro la tarjeta de numero: ".concat(cardId.toString()));
				return new ResponseEntity<>(entityResponse, HttpStatus.NO_CONTENT);
			}
			entityResponse.setBody(mapper.mappingInTarjetaDTOOut(tarjeta));
			entityResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			return ResponseEntity.ok(entityResponse);
		}catch(BankIncException e) {
			entityResponse.setMessage(Utils.validateString(e.getMessage()) ? e.getMessage() : "Error general.");
			return new ResponseEntity<>(entityResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Tarjeta actualizarTarjeta(TarjetaDTO tarjetaDTO) throws BankIncException {
		try {
			ClienteDTO cliente = clienteService.findClienteByID(tarjetaDTO.getCliente());
			Tarjeta tarjetaUpdate = repository.save(mapper.mappingTarjetaDTOToTarjeta(tarjetaDTO, cliente));
			return tarjetaUpdate;
		}catch(BankIncException e) {
			throw new BankIncException("Error durante el proceso de actualizacion");
		}
	}
	
	public TarjetaDTO actualizarTarjetaDto(TarjetaDTO tarjetaDTO) throws BankIncException {
		TarjetaDTO tarjetaValueReturn;
		try {
			tarjetaValueReturn = mapper.mappingTarjetaToTarjetaDTO(actualizarTarjeta(tarjetaDTO));
			return  tarjetaValueReturn;
		}catch(BankIncException e) {
			throw new BankIncException("Error durante el proceso de mapeo.");
		}
	}
	
	public TarjetaDTOOut actualizarTarjetaDTOOut(TarjetaDTO tarjetaDTO) throws BankIncException {
		try {
			return mapper.mappingInTarjetaDTOOut(actualizarTarjeta(tarjetaDTO));
		}catch(BankIncException e) {
			throw new BankIncException("Error durante el proceso de mapeo.");
		}
	}
	
	
	
	public TarjetaDTO findTarjetaByNumero(Long numeroTarjeta) {
		TarjetaDTO tarjetaDTO = null;
		try {
			Tarjeta tarjeta = repository.findById(numeroTarjeta).orElse(null);
			tarjetaDTO = mapper.mappingTarjetaToTarjetaDTO(tarjeta);
		}catch(BankIncException e) {
			e.printStackTrace();
		}
		return tarjetaDTO;
	}
	
	private Long generarValorNumeroTarjeta(Long numeroProducto){
		Long limiteInferior = 0L;
		Long limiteSuperior = 9999999999L;
		String valorAleatorio = String.valueOf(limiteInferior + (long) (Math.random() * (limiteSuperior - limiteInferior)));
		String valorGenerado = String.valueOf(numeroProducto).concat(validarLongitud(valorAleatorio));
		return Long.parseLong(valorGenerado);
	}
	
	private String validarLongitud(String valor) {
		return valor.length() == 10 ? valor : validarLongitud("0".concat(valor));
	}
}
