package com.co.prueba.mapper;

import org.mapstruct.Mapper;

import com.co.prueba.dto.TipoProductoDTO;
import com.co.prueba.entity.TipoProducto;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {
	
	TipoProductoDTO mapperTipoProductoToTipoProductoDTO(TipoProducto tipoProducto);
	
}
