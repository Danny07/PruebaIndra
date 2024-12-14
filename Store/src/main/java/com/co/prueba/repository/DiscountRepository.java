package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.Discount;

@RepositoryRestResource(exported = false)
public interface DiscountRepository extends CrudRepository<Discount, Long> {

}
