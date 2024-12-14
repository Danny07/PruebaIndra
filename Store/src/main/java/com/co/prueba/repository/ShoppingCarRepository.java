package com.co.prueba.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.ShoppingCar;

@RepositoryRestResource(exported = false)
public interface ShoppingCarRepository extends CrudRepository<ShoppingCar, UUID> {

}
