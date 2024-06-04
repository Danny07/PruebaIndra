package com.co.prueba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.co.prueba.dto.TipoTransaccionDTO;
import com.co.prueba.dto.TransaccionDTO;
import com.co.prueba.dto.rest.TransaccionDTOOut;
import com.co.prueba.entity.Tarjeta;
import com.co.prueba.entity.Transaccion;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

	@Mapping(target = "tarjeta", source = "tarjeta")
	@Mapping(target = "tipoTransaccion", source = "tipoTransaccionDTO")
	Transaccion mapperTransaccionDTOToTransaccion(TransaccionDTO transaccionDTO, Tarjeta tarjeta, TipoTransaccionDTO tipoTransaccionDTO);
	
	
	@Mapping(target = "tarjeta", source = "tarjeta.numero")
	@Mapping(target = "tipoTransaccion", source = "tipoTransaccion.idTipoTransaccion")
	TransaccionDTO mapperTransaccionToTransaccionDTO(Transaccion transaccion);
	
	@Mapping(target = "tarjeta", expression = "java(TarjetaMapper.mapper.mappingInTarjetaDTOOut(transaccion.getTarjeta()))")
	@Mapping(target = "tipoTransaccion", source = "tipoTransaccion.tipo")
	TransaccionDTOOut mapperInTransaccionToTransaccionDTOOut(Transaccion transaccion);

	
}
