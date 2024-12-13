package com.co.prueba.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.ProductDto;
import com.co.prueba.entity.Product;


@Mapper
public interface ProductMapper {
	
	ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
	
	ProductDto productToProductDto(Product product);
	
	List<ProductDto> lstProductToLstProductDto(List<Product> products);
}
