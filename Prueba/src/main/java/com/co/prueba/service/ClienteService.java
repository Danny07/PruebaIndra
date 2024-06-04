package com.co.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.ClienteDTO;
import com.co.prueba.entity.Cliente;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.ClienteMapper;
import com.co.prueba.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteMapper mapper;
	
	public ClienteDTO findClienteByID(Long idCliente) {
		ClienteDTO clienteDTO = null;
		try {
			Cliente cliente = repository.findById(idCliente).orElse(null);
			clienteDTO = mapper.mappingClienteToClienteDTO(cliente);
		}catch(BankIncException e) {
			e.printStackTrace();
		}
		return clienteDTO;
	}

}
