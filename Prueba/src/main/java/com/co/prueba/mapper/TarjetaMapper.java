package com.co.prueba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.ClienteDTO;
import com.co.prueba.dto.TarjetaDTO;
import com.co.prueba.dto.rest.TarjetaDTOOut;
import com.co.prueba.entity.Tarjeta;


@Mapper(componentModel = "spring")
public interface TarjetaMapper {
	
	TarjetaMapper mapper = Mappers.getMapper(TarjetaMapper.class);

	@Mapping(target = "cliente", source = "cliente.id")
	TarjetaDTO mappingTarjetaToTarjetaDTO(Tarjeta tarjeta);
	
	@Mapping(target = "cliente", source = "cliente")
	Tarjeta mappingTarjetaDTOToTarjeta(TarjetaDTO tarjetaDTO, ClienteDTO cliente);
	
	@Mapping(target = "numeroTarjeta", source = "tarjeta.numero")
	@Mapping(target = "fechaVencimienta", source = "tarjeta.fechaVencimiento", dateFormat = "MM/yyyy")
	@Mapping(target = "titularCuenta", expression = "java(tarjeta.getCliente().getPrimerNombre().concat(\" \").concat(tarjeta.getCliente().getPrimerApellido()))")
	@Mapping(target = "saldo", source = "tarjeta.saldo")
	TarjetaDTOOut mappingInTarjetaDTOOut(Tarjeta tarjeta);
}
