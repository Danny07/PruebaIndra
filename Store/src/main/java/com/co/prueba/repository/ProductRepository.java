package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
