package com.co.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.prueba.dto.TarjetaDTO;
import com.co.prueba.dto.TipoTransaccionDTO;
import com.co.prueba.dto.TransaccionDTO;
import com.co.prueba.dto.rest.AnularTransaccionDTOIn;
import com.co.prueba.dto.rest.EntityResponse;
import com.co.prueba.dto.rest.TarjetaDTOOut;
import com.co.prueba.dto.rest.TransaccionDTOIn;
import com.co.prueba.dto.rest.TransaccionDTOOut;
import com.co.prueba.entity.Cliente;
import com.co.prueba.entity.Tarjeta;
import com.co.prueba.entity.Transaccion;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.TarjetaMapper;
import com.co.prueba.mapper.TransaccionMapper;
import com.co.prueba.repository.TransaccionRepository;
import com.co.prueba.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class TransaccionServiceTest {
	
	@Mock
	private TransaccionRepository repository;
	 
	@Mock
	private TipoTransaccionService tipoTransaccionService;
	 
	@Mock
	private TarjetaService tarjetaService;
	 
	@Spy
	private TransaccionMapper mapper = Mappers.getMapper(TransaccionMapper.class);
	
	@InjectMocks
	private TransaccionService service;
	
	@Test
	public void testAgregarTransaccion() {
		when(tipoTransaccionService.findByTipo(Mockito.anyString())).thenReturn(buildTipoTransaccionDTO());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true, false, BigDecimal.TEN));
		when(tarjetaService.actualizarTarjeta(Mockito.any(TarjetaDTO.class))).thenReturn(buildTarjeta());
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.agregarTransaccion(buildTransaccionDTOIn(), Constantes.TIPO_TRANSACCION_COMPRA);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testAgregarTransaccionTarjetaNull() {
		when(tipoTransaccionService.findByTipo(Mockito.anyString())).thenReturn(buildTipoTransaccionDTO());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(null);
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.agregarTransaccion(buildTransaccionDTOIn(), Constantes.TIPO_TRANSACCION_COMPRA);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testAgregarTransaccionTarjetaInactiva() {
		when(tipoTransaccionService.findByTipo(Mockito.anyString())).thenReturn(buildTipoTransaccionDTO());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(false, false, BigDecimal.TEN));
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.agregarTransaccion(buildTransaccionDTOIn(), Constantes.TIPO_TRANSACCION_COMPRA);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	
	@Test
	public void testAgregarTransaccionTarjetaBloqueada() {
		when(tipoTransaccionService.findByTipo(Mockito.anyString())).thenReturn(buildTipoTransaccionDTO());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true,true, BigDecimal.TEN));
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.agregarTransaccion(buildTransaccionDTOIn(), Constantes.TIPO_TRANSACCION_COMPRA);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	
	@Test
	public void testAgregarTransaccionTarjetaSaldoInsuficiente() {
		when(tipoTransaccionService.findByTipo(Mockito.anyString())).thenReturn(buildTipoTransaccionDTO());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true,false, BigDecimal.ZERO));
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.agregarTransaccion(buildTransaccionDTOIn(), Constantes.TIPO_TRANSACCION_COMPRA);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	
	@Test
	public void testAgregarTransaccionTarjetaException() {
		when(tipoTransaccionService.findByTipo(Mockito.anyString())).thenReturn(buildTipoTransaccionDTO());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenThrow(new BankIncException(""));
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.agregarTransaccion(buildTransaccionDTOIn(), Constantes.TIPO_TRANSACCION_COMPRA);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testConsultarTransaccion() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTransaccion(false)));
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.consultarTransaccion(Mockito.anyLong());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testConsultarTransaccionNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.consultarTransaccion(Mockito.anyLong());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testConsultarTransaccionException() {
		when(repository.findById(Mockito.anyLong())).thenThrow(new BankIncException(""));
		
		ResponseEntity<EntityResponse<TransaccionDTOOut>> response = service.consultarTransaccion(Mockito.anyLong());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAnularTransaccion() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTransaccion(false)));
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true, false, BigDecimal.TEN));
		when(tarjetaService.actualizarTarjeta(Mockito.any(TarjetaDTO.class))).thenReturn(buildTarjeta());
		
		ResponseEntity<EntityResponse<HttpStatus>> response = service.anularTransaccion(buildAnularTransaccionDTOIn());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testAnularTransaccionNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true, false, BigDecimal.TEN));
		
		ResponseEntity<EntityResponse<HttpStatus>> response = service.anularTransaccion(buildAnularTransaccionDTOIn());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testAnularTransaccionTarjetaNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTransaccion(false)));
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(null);
		
		ResponseEntity<EntityResponse<HttpStatus>> response = service.anularTransaccion(buildAnularTransaccionDTOIn());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testAnularTransaccionConflict() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTransaccion(true)));
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true, false, BigDecimal.TEN));
		
		ResponseEntity<EntityResponse<HttpStatus>> response = service.anularTransaccion(buildAnularTransaccionDTOIn());
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	
	@Test
	public void testAnularTransaccionException() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTransaccion(false)));
		when(tarjetaService.findTarjetaByNumero(Mockito.anyLong())).thenReturn(buildTarjetaDTO(true, false, BigDecimal.TEN));
		when(tarjetaService.actualizarTarjeta(Mockito.any(TarjetaDTO.class))).thenThrow(new BankIncException(""));
		
		ResponseEntity<EntityResponse<HttpStatus>> response = service.anularTransaccion(buildAnularTransaccionDTOIn());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	private Transaccion buildTransaccion(boolean isFail) {
		Date dateNow = new Date();
		LocalDateTime minus = LocalDateTime.ofInstant(dateNow.toInstant(), ZoneId.systemDefault()).minusDays(100);
		Date lessDay = Date.from(minus.atZone(ZoneId.systemDefault()).toInstant());
		Transaccion transaccion = new Transaccion();
		transaccion.setIdTransacccion(1L);
		transaccion.setFechaTransaccion(isFail ? lessDay : dateNow);
		transaccion.setValor(BigDecimal.ONE);
		return transaccion;
	}
	
	private TransaccionDTOIn buildTransaccionDTOIn() {
		
		TransaccionDTOIn transaccion = new TransaccionDTOIn();
		transaccion.setBalance(BigDecimal.ONE);
		transaccion.setCardId(1L);		
		return transaccion;
	}
	
	private AnularTransaccionDTOIn buildAnularTransaccionDTOIn() {
		AnularTransaccionDTOIn anular = new AnularTransaccionDTOIn();
		anular.setCardId(1L);
		anular.setTransactionId(1L);
		return anular;
	}
	
	private TipoTransaccionDTO buildTipoTransaccionDTO() {
		TipoTransaccionDTO tipoTransaccion = new TipoTransaccionDTO();
		tipoTransaccion.setTipo(Constantes.TIPO_TRANSACCION_COMPRA);
		tipoTransaccion.setIdTipoTransaccion(1L);
		return tipoTransaccion;
	}
	
	private TarjetaDTO buildTarjetaDTO(boolean isActivate, boolean isBloqueada, BigDecimal saldo) {
		TarjetaDTO tarjeta = new TarjetaDTO();
		tarjeta.setNumero(1L);
		tarjeta.setFechaVencimiento(new Date());
		tarjeta.setCliente(1L);
		tarjeta.setSaldo(saldo);
		tarjeta.setActiva(isActivate);
		tarjeta.setBloqueada(isBloqueada);
		return tarjeta;
	}
	
	private Tarjeta buildTarjeta() {
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setNumero(1L);
		tarjeta.setFechaVencimiento(new Date());
		tarjeta.setCliente(createCliente());
		tarjeta.setSaldo(BigDecimal.TEN);
		tarjeta.setActiva(true);
		tarjeta.setBloqueada(false);
		return tarjeta;
	}
	
	private Cliente createCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setPrimerNombre("nombre1");
		cliente.setSegundoNombre("nombre2");
		cliente.setPrimerApellido("apellido1");
		cliente.setSegundoApellido("apellido2");
		return cliente;
	}
}
