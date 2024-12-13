package com.co.prueba.service;

import java.util.Date;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.DiscountProductDto;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.DiscountProductMapper;
import com.co.prueba.repository.DiscountProductRepository;

@Service
public class DiscountProductService {
	
	@Autowired
	private DiscountProductRepository repository;
	
	private DiscountProductMapper mappers = Mappers.getMapper(DiscountProductMapper.class);
	
	
	public DiscountProductDto findDiscountByIdProductAndDate(String idProduct, Date saleDate) throws StoreException {
		try {
			return mappers.discountProductToDiscountProductDto(repository.findDiscountByIdProductAndDate(idProduct, saleDate));
		} catch (Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
		
	}

}
