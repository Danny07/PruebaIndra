package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.Coupon;
import java.util.List;


public interface CouponRepository extends CrudRepository<Coupon, Long> {

	List<Coupon> findByName(String name);
}
