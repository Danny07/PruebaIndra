package com.co.prueba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.UserDto;
import com.co.prueba.entity.User;


@Mapper
public interface UserMapper {
	
	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
	
	@Mapping(source = "documentType.abbreviation", target = "documentType")
	UserDto userToUserDto(User user);
}
