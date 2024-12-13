package com.co.prueba.service;

import java.math.BigDecimal;
import java.util.Date;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.DiscountProductDto;
import com.co.prueba.dto.ShoppingCarDto;
import com.co.prueba.dto.ShoppingCarDtoGet;
import com.co.prueba.dto.ShoppingCarDtoPostPut;
import com.co.prueba.entity.Product;
import com.co.prueba.entity.ShoppingCar;
import com.co.prueba.entity.User;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.ShoppingCarMapper;
import com.co.prueba.repository.ShoppingCarRepository;
import com.co.prueba.utils.Utils;

@Service
public class ShoppingCarService {
	
	@Autowired
	private ShoppingCarRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DiscountProductService discountProductService;
	
	private ShoppingCarMapper mapper = Mappers.getMapper(ShoppingCarMapper.class);
	
	public ShoppingCar createUpate(ShoppingCarDtoPostPut shoppingCarDto) throws StoreException {
		try {
			if(shoppingCarDto.getQuantity().compareTo(0L)<=0) {
				if(!Utils.ValidateNullVoidString(shoppingCarDto.getId())) {
					repository.deleteById(shoppingCarDto.getId());
				}
				return null;
			}else {
				User user = userService.findUser(shoppingCarDto.getIdUser());
				Product product = productService.findProduct(shoppingCarDto.getIdProduct());
				ShoppingCar shoppingCar = mapper.shoppingCarDtoToShoppingCar(shoppingCarDto, user, product);
				repository.save(shoppingCar);
				return shoppingCar;
			}
		}catch(Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}
	
	public ShoppingCarDtoGet findById(String id, Date dateFind) throws StoreException{
		try {
			ShoppingCar shoppingCar = repository.findById(id).orElse(null);
			if(null != shoppingCar) {
				ShoppingCarDto shoppingCarDto = mapper.shoppingCarToShoppingCarDto(shoppingCar);
				DiscountProductDto discountProductDto =  discountProductService.findDiscountByIdProductAndDate(id, dateFind);
				BigDecimal multiplication = shoppingCarDto.getPriceProducts().multiply(new BigDecimal(shoppingCarDto.getQuantity()));
				shoppingCarDto.setSubTotal(multiplication);
				if(null == discountProductDto) {
					shoppingCarDto.setTotal(multiplication);
				}else {
					BigDecimal newValuePrice = shoppingCarDto.getPriceProducts().divide((discountProductDto.getPercentage().divide(shoppingCarDto.getPriceProducts()).divide(new BigDecimal(100))));
					shoppingCarDto.setTotal(newValuePrice.multiply(new BigDecimal(shoppingCarDto.getQuantity())));
				}
				return mapper.shoppingCarDtoToShoppingCarDtoGet(shoppingCarDto);
			}else {
				return null;
			}
		}catch(Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}

}
