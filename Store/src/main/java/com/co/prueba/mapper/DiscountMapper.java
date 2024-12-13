package com.co.prueba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.DiscountDto;
import com.co.prueba.entity.Discount;


@Mapper
public interface DiscountMapper {
	
	DiscountMapper MAPPER = Mappers.getMapper(DiscountMapper.class);
	
	Discount discountDtoToDiscount(DiscountDto discountDto);
	
	DiscountDto discountToDiscountDto(Discount discount);
}
