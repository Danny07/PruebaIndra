package com.co.prueba.mapper;

import org.mapstruct.Mapper;

import com.co.prueba.dto.TipoTransaccionDTO;
import com.co.prueba.entity.TipoTransaccion;

@Mapper(componentModel = "spring")
public interface TipoTransaccionMapper {

	TipoTransaccionDTO mapperTipoTransaccionDTOToTipoTransaccion(TipoTransaccion tipoTransaccion);
}
