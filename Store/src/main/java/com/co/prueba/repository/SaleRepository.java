package com.co.prueba.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.Sale;

public interface SaleRepository extends CrudRepository<Sale, String> {

	@Query(value = "SELECT s.* "
			+ "FROM sale s, shopping_cars sc"
			+ "WHERE s.id = sc.id_sale "
			+ "AND sc.id_users =?1 "
			+ "AND s.id_coupon = ?2", nativeQuery = true)
	public Sale findByIdUserAndCoupon(String idUser,String idCupon);
}
