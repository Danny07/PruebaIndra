package com.co.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.prueba.dto.ClienteDTO;
import com.co.prueba.dto.TipoProductoDTO;
import com.co.prueba.dto.rest.ActivarTarjetaDTOIn;
import com.co.prueba.dto.rest.EntityResponse;
import com.co.prueba.dto.rest.NumeroTarjetaDTOOut;
import com.co.prueba.dto.rest.TarjetaDTOOut;
import com.co.prueba.dto.rest.TransaccionDTOIn;
import com.co.prueba.entity.Cliente;
import com.co.prueba.entity.Tarjeta;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.TarjetaMapper;
import com.co.prueba.repository.TarjetaRepository;

@ExtendWith(MockitoExtension.class)
public class TarjetaServiceTest {

	@Mock
	private TarjetaRepository repository;
	
	
	@Spy
	private TarjetaMapper mapper = Mappers.getMapper(TarjetaMapper.class);
	
	@Mock
	private TipoProductoService tipoProductoService;
	
	@Mock
	private ClienteService clienteService;
	
	@InjectMocks
	private TarjetaService service;
	
	@Test
	public void testGenerarNumeroTarjeta() {
		when(tipoProductoService.findtipoProductoById(Mockito.anyLong())).thenReturn(buildTipoProductoDTO());
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResponseEntity<EntityResponse<NumeroTarjetaDTOOut>> response = service.generarNumeroTarjeta(Mockito.anyLong());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testActivarTarjeta() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		when(clienteService.findClienteByID(Mockito.anyLong())).thenReturn(createClienteDTO());
		when(repository.save(Mockito.any(Tarjeta.class))).thenReturn((buildTarjeta()));
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.activarTarjeta(buildActivarTarjetaDTOIn());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testActivarTarjetaNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.activarTarjeta(buildActivarTarjetaDTOIn());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testActivarTarjetaException() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		when(clienteService.findClienteByID(Mockito.anyLong())).thenThrow(new BankIncException(""));
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.activarTarjeta(buildActivarTarjetaDTOIn());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testBloquearTarjeta() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		when(clienteService.findClienteByID(Mockito.anyLong())).thenReturn(createClienteDTO());
		when(repository.save(Mockito.any(Tarjeta.class))).thenReturn((buildTarjeta()));
		ResponseEntity<EntityResponse<HttpStatus>> response = service.bloquearTarjeta(Mockito.anyLong());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testBloquearTarjetaNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResponseEntity<EntityResponse<HttpStatus>> response = service.bloquearTarjeta(Mockito.anyLong());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testBloquearTarjetaException() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		when(clienteService.findClienteByID(Mockito.anyLong())).thenThrow(new BankIncException(""));
		ResponseEntity<EntityResponse<HttpStatus>> response = service.bloquearTarjeta(Mockito.anyLong());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testRecargarTarjeta() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		when(clienteService.findClienteByID(Mockito.anyLong())).thenReturn(createClienteDTO());
		when(repository.save(Mockito.any(Tarjeta.class))).thenReturn((buildTarjeta()));
		
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.recagarSaldo(buildTransaccionDTOIn());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testRecargaTarjetaNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.recagarSaldo(buildTransaccionDTOIn());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testRecargaTarjetaException() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		when(clienteService.findClienteByID(Mockito.anyLong())).thenThrow(new BankIncException(""));
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.recagarSaldo(buildTransaccionDTOIn());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testConsultarTarjeta() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(buildTarjeta()));
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.consultarSaldo(Mockito.anyLong());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testConsultarTarjetaNoContent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.consultarSaldo(Mockito.anyLong());
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testConsultarTarjetaException() {
		when(repository.findById(Mockito.anyLong())).thenThrow(new BankIncException(""));
		ResponseEntity<EntityResponse<TarjetaDTOOut>> response = service.consultarSaldo(Mockito.anyLong());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	
	
	private TransaccionDTOIn buildTransaccionDTOIn() {
		TransaccionDTOIn transaccion = new TransaccionDTOIn();
		transaccion.setBalance(BigDecimal.ONE);
		transaccion.setCardId(1L);
		return transaccion;
	}
	
	private ActivarTarjetaDTOIn buildActivarTarjetaDTOIn() {
		ActivarTarjetaDTOIn in = new ActivarTarjetaDTOIn();
		in.setCardId(1L);
		return in;
	}
	
	private Tarjeta buildTarjeta() {
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setNumero(1L);
		tarjeta.setFechaVencimiento(new Date());
		tarjeta.setCliente(createCliente());
		tarjeta.setSaldo(BigDecimal.TEN);
		return tarjeta;
	}
	
	private TipoProductoDTO buildTipoProductoDTO() {
		TipoProductoDTO tipoProducto = new TipoProductoDTO();
		tipoProducto.setIdTipoProducto(1L);
		tipoProducto.setNumeroProducto(123456L);
		tipoProducto.setNombreProducto("nombreProducto");
		return tipoProducto;
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
	
	private ClienteDTO createClienteDTO() {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setId(1L);
		cliente.setPrimerNombre("nombre1");
		cliente.setSegundoNombre("nombre2");
		cliente.setPrimerApellido("apellido1");
		cliente.setSegundoApellido("apellido2");
		return cliente;
	}
}
