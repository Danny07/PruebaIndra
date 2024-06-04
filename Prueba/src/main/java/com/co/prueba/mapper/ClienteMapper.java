package com.co.prueba.mapper;

import org.mapstruct.Mapper;

import com.co.prueba.dto.ClienteDTO;
import com.co.prueba.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

	ClienteDTO mappingClienteToClienteDTO(Cliente cliente);
}
