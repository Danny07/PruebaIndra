package com.co.prueba.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.DiscountProduct;

public interface DiscountProductRepository extends CrudRepository<DiscountProduct, Long> {
	
	@Query(value = "Select * "
			+ "from DiscountProduct dp , Discount d "
			+ "where dp.id_products = ?1 "
			+ "and dp.id_discounts = d.id"
			+ "?2 BETWEEN d.begin_date AND d.end_date", 
			  nativeQuery = true)
	DiscountProduct findDiscountByIdProductAndDate(String idProduct, Date saleDate);

}
