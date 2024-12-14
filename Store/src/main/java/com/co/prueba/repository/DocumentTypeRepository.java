package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.co.prueba.entity.DocumentType;

@RepositoryRestResource(exported = false)
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long> {

}
