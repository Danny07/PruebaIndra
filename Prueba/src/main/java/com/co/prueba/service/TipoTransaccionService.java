package com.co.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.TipoTransaccionDTO;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.TipoTransaccionMapper;
import com.co.prueba.repository.TipoTransaccionRepository;

@Service
public class TipoTransaccionService {
	
	@Autowired
	private TipoTransaccionRepository repository;
	
	@Autowired
	private TipoTransaccionMapper mapper;

	public TipoTransaccionDTO findByTipo(String tipo) {
		TipoTransaccionDTO tipoTransaccionDTO = null;
		try {
			tipoTransaccionDTO = mapper.mapperTipoTransaccionDTOToTipoTransaccion(repository.findByTipo(tipo));
		}catch(BankIncException e) {}
		return tipoTransaccionDTO;
	}
	
	
}
