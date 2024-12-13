package com.co.prueba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.CouponDto;
import com.co.prueba.entity.Coupon;


@Mapper
public interface CouponMapper {
	
	CouponMapper MAPPER = Mappers.getMapper(CouponMapper.class);
	
	Coupon couponDtoToCoupon(CouponDto couponDto);
	
	CouponDto couponToCouponDto(Coupon coupon);
}
