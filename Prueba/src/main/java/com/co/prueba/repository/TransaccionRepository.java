package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.Transaccion;

public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {

}
