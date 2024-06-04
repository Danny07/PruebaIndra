package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
