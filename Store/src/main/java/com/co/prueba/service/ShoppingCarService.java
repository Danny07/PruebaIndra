package com.co.prueba.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.DiscountProductDto;
import com.co.prueba.dto.ShoppingCarDto;
import com.co.prueba.dto.ShoppingCarDtoGet;
import com.co.prueba.dto.ShoppingCarDtoPost;
import com.co.prueba.dto.ShoppingCarDtoPut;
import com.co.prueba.entity.Product;
import com.co.prueba.entity.Sale;
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
	
	public List<ShoppingCar> create(List<ShoppingCarDtoPost> shoppingCarsDto) throws StoreException {
		try {
			List<ShoppingCar> shoppings = shoppingCarsDto.stream().map(shoppingCarDtoPost -> {
				try {
				if(shoppingCarDtoPost.getQuantity().compareTo(0L)>0) {
					User user = userService.findUser(shoppingCarDtoPost.getIdUser());
					Product product = productService.findProduct(shoppingCarDtoPost.getIdProduct());
					ShoppingCar shoppingCar = mapper.shoppingCarDtoPostToShoppingCar(shoppingCarDtoPost, user, product);
					shoppingCar.setIsSale(false);
					shoppingCar= repository.save(shoppingCar);
					return shoppingCar;
				}else {
					return null;
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).filter(shoppingCar -> null != shoppingCar).toList();
			return shoppings;
		}catch(Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}
	

	
	public List<ShoppingCar> update(List<ShoppingCarDtoPut> shoppingCarsDto) throws StoreException {
		try {
			List<ShoppingCar> shoppings = shoppingCarsDto.stream().map(shoppingCarDtoPost -> {
				try {
				if(shoppingCarDtoPost.getQuantity().compareTo(0L)>0) {
					User user = userService.findUser(shoppingCarDtoPost.getIdUser());
					Product product = productService.findProduct(shoppingCarDtoPost.getIdProduct());
					ShoppingCar shoppingCar = mapper.shoppingCarDtoPutToShoppingCar(shoppingCarDtoPost, user, product);
					shoppingCar.setIsSale(false);
					repository.save(shoppingCar);
					return shoppingCar;
				}else {
					if(!Utils.ValidateNullVoidString(shoppingCarDtoPost.getId())) {
						repository.deleteById(UUID.fromString(shoppingCarDtoPost.getId()));
					}
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).filter(shoppingCar -> null != shoppingCar).toList();
			return shoppings;
		}catch(Exception e) {
			throw new StoreException("Error inesperado: %s", e.getMessage());
		}
	}
	
	public ShoppingCarDtoGet findById(UUID id, Date dateFind) throws StoreException{
		try {
			ShoppingCar shoppingCar = repository.findById(id).orElse(null);
			if(null != shoppingCar) {
				ShoppingCarDto shoppingCarDto = mapper.shoppingCarToShoppingCarDto(shoppingCar);
				DiscountProductDto discountProductDto =  discountProductService.findDiscountByIdProductAndDate(shoppingCar.getIdProduct().getId(), dateFind);
				BigDecimal multiplication = shoppingCarDto.getPriceProducts().multiply(new BigDecimal(shoppingCarDto.getQuantity()));
				shoppingCarDto.setSubTotal(multiplication);
				if(null == discountProductDto) {
					shoppingCarDto.setTotal(multiplication);
				}else {
					BigDecimal newValuePrice = shoppingCarDto.getPriceProducts().subtract
							((discountProductDto.getPercentage().divide(new BigDecimal(100))
									.multiply(shoppingCarDto.getPriceProducts())));
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
