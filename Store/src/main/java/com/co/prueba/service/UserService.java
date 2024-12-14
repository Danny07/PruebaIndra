package com.co.prueba.service;

import java.util.UUID;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.UserDto;
import com.co.prueba.entity.User;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.UserMapper;
import com.co.prueba.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	public User findUser(String id) throws StoreException {
		try {
			User user = repository.findById(UUID.fromString(id)).orElse(null);
			return user;
		} catch (Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}

}
