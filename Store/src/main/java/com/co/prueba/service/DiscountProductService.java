package com.co.prueba.service;

import java.util.Date;
import java.util.UUID;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.DiscountProductDto;
import com.co.prueba.entity.DiscountProduct;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.DiscountProductMapper;
import com.co.prueba.repository.DiscountProductRepository;

@Service
public class DiscountProductService {
	
	@Autowired
	private DiscountProductRepository repository;
	
	private DiscountProductMapper mappers = Mappers.getMapper(DiscountProductMapper.class);
	
	
	public DiscountProductDto findDiscountByIdProductAndDate(UUID idProduct, Date saleDate) throws StoreException {
		try {
			DiscountProduct discountProduct = repository.findDiscountByIdProductAndDate(idProduct, saleDate);
			return mappers.discountProductToDiscountProductDto(discountProduct);
		} catch (Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
		
	}

}
