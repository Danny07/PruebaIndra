package com.co.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.TipoProductoDTO;
import com.co.prueba.entity.TipoProducto;
import com.co.prueba.exception.BankIncException;
import com.co.prueba.mapper.TipoProductoMapper;
import com.co.prueba.repository.TipoProductoRepository;

@Service
public class TipoProductoService {

	@Autowired
	private TipoProductoRepository repository;
	
	@Autowired
	private TipoProductoMapper mapper;
	
	public TipoProductoDTO findtipoProductoById(Long productId) {
		TipoProductoDTO tipoProductoDTO = null; 
		try {
			tipoProductoDTO = mapper.mapperTipoProductoToTipoProductoDTO(repository.findById(productId).orElse(null));
		}catch(BankIncException e) {}
		return tipoProductoDTO;
	}	
}
