package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.Coupon;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface CouponRepository extends CrudRepository<Coupon, Long> {

	List<Coupon> findByName(String name);
}
