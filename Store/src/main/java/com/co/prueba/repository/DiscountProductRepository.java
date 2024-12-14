package com.co.prueba.repository;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.DiscountProduct;

@RepositoryRestResource(exported = false)
public interface DiscountProductRepository extends CrudRepository<DiscountProduct, Long> {
	
	@Query(value = "Select dp.* "
			+ "from Discounts_Products dp , Discounts d "
			+ "where dp.id_products = ?1 "
			+ "and dp.id_discounts = d.id "
			+ "and ?2 BETWEEN d.begin_date AND d.end_date", 
			  nativeQuery = true)
	DiscountProduct findDiscountByIdProductAndDate(UUID idProduct, Date saleDate);

}
