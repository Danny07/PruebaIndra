package com.co.prueba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.DiscountProductDto;
import com.co.prueba.entity.DiscountProduct;


@Mapper
public interface DiscountProductMapper {
	
	DiscountProductMapper MAPPER = Mappers.getMapper(DiscountProductMapper.class);
	
	@Mapping(source = "idDiscounts.percentage", target = "percentage")
	@Mapping(source = "idProducts.id", target = "idProducts")
	DiscountProductDto discountProductToDiscountProductDto(DiscountProduct discountProduct);
}
