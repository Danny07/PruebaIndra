package com.co.prueba.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.Sale;

@RepositoryRestResource(exported = false)
public interface SaleRepository extends CrudRepository<Sale, UUID> {

	@Query(value = "SELECT s.* "
			+ "FROM sale s, shopping_cars sc"
			+ "WHERE s.id = sc.id_sale "
			+ "AND sc.id_users =?1 "
			+ "AND s.id_coupon = ?2", nativeQuery = true)
	public Sale findByIdUserAndCoupon(UUID idUser,String idCupon);
}
