package com.co.prueba.mapper;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.SaleDto;
import com.co.prueba.dto.SaleDtoPost;
import com.co.prueba.dto.SaleDtoPut;
import com.co.prueba.dto.ShoppingCarDtoGet;
import com.co.prueba.entity.Coupon;
import com.co.prueba.entity.Sale;
import com.co.prueba.entity.ShoppingCar;


@Mapper
public interface SaleMapper {
	
	SaleMapper MAPPER = Mappers.getMapper(SaleMapper.class);
	ShoppingCarMapper MAPPERSHOPPINGCAR = Mappers.getMapper(ShoppingCarMapper.class);
	
	@Mapping(target = "idCoupon", source = "idCoupon")
	@Mapping(target = "id", ignore = true)
	Sale saleDtoPostToSale(SaleDtoPost saleDto, Coupon idCoupon,  List<ShoppingCar> idShoppingCars);
	
	@Mapping(source = "saleDto.id", target = "id")
	Sale saleDtoPutToSale(SaleDtoPut saleDto, Coupon idCoupon,  List<ShoppingCar> idShoppingCars);
	
	@Mapping(source = "shoppingCarsDtoGet", target = "idShoppingCars")
	SaleDto saleToSaleDto(Sale sale, List<ShoppingCarDtoGet> shoppingCarsDtoGet,String nameCoupon, String valueCoupon);
	
	default String UUIDToString(UUID id) {
		return id.toString();
	}
}
