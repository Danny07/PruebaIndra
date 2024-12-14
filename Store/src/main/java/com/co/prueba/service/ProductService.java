package com.co.prueba.service;

import java.util.UUID;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.ProductDto;
import com.co.prueba.entity.Product;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.ProductMapper;
import com.co.prueba.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	private ProductMapper mappers = Mappers.getMapper(ProductMapper.class);
	
	public Product findProduct(String id) throws StoreException {
		try {
			Product product = repository.findById(UUID.fromString(id)).orElse(null);
			return product;
		} catch (Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}
}
