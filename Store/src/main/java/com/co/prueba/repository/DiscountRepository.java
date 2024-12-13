package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

}
