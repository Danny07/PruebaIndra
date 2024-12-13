package com.co.prueba.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.CouponDto;
import com.co.prueba.entity.Coupon;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.CouponMapper;
import com.co.prueba.repository.CouponRepository;

@Service
public class CouponService {

	private CouponMapper mapper = Mappers.getMapper(CouponMapper.class);

	@Autowired
	private CouponRepository repository;

	public Coupon findByName(String name) throws StoreException {
		try {
			List<Coupon> coupons = repository.findByName(name);
			if (coupons != null && !coupons.isEmpty()) {
				return coupons.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}

}
