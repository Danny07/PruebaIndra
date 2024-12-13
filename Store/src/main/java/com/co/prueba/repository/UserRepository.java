package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
