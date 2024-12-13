package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.ShoppingCar;

public interface ShoppingCarRepository extends CrudRepository<ShoppingCar, String> {

}
