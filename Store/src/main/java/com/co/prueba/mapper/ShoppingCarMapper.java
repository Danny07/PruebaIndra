package com.co.prueba.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.co.prueba.dto.ShoppingCarDto;
import com.co.prueba.dto.ShoppingCarDtoGet;
import com.co.prueba.dto.ShoppingCarDtoPost;
import com.co.prueba.dto.ShoppingCarDtoPut;
import com.co.prueba.entity.Product;
import com.co.prueba.entity.ShoppingCar;
import com.co.prueba.entity.User;


@Mapper
public interface ShoppingCarMapper {
	
	ShoppingCarMapper MAPPER = Mappers.getMapper(ShoppingCarMapper.class);
	
	@Mapping(source = "user", target = "idUser")
	@Mapping(source = "product", target = "idProduct")
	@Mapping(target = "id", ignore = true)
	ShoppingCar shoppingCarDtoPostToShoppingCar(ShoppingCarDtoPost shoppingCarDto, User user, Product product);
	
	@Mapping(source = "user", target = "idUser")
	@Mapping(source = "product", target = "idProduct")
	@Mapping(source = "shoppingCarDto.id", target = "id")
	ShoppingCar shoppingCarDtoPutToShoppingCar(ShoppingCarDtoPut shoppingCarDto, User user, Product product);
		
	@Mapping(source = "idUser.id", target = "idUser")
	@Mapping(source = "idProduct.id", target = "idProduct")
	@Mapping(source = "idProduct.description", target = "descriptionProducts")
	@Mapping(source = "idProduct.price", target = "priceProducts")
	ShoppingCarDto shoppingCarToShoppingCarDto(ShoppingCar shoppingCar);
	
	ShoppingCarDtoGet shoppingCarDtoToShoppingCarDtoGet(ShoppingCarDto shoppingCarDto);
	
	List<ShoppingCarDto> lstShoppingCarToLstShoppingCarDto(List<ShoppingCar> shoppingCar);
	
	List<ShoppingCarDtoGet> lstShoppingCarDtoToLstShoppingCarDtoGet(List<ShoppingCarDto> shoppingCarDto);
	
}
