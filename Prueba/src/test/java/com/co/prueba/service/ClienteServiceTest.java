package com.co.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.prueba.dto.ClienteDTO;
import com.co.prueba.entity.Cliente;
import com.co.prueba.mapper.ClienteMapper;
import com.co.prueba.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClienteRepository repository;
	
	@Spy
	private ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);
	
	@InjectMocks
	private ClienteService service;
	
	@Test
	public void testFindCliente() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(createCliente()));
		
		ClienteDTO clienteDTo = service.findClienteByID(Mockito.anyLong());
		assertEquals(createCliente().getId(), clienteDTo.getId());
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
