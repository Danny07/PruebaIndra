package com.co.prueba.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, UUID> {

}
