package com.co.prueba.service;

import java.math.BigDecimal;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.prueba.dto.SaleDto;
import com.co.prueba.dto.SaleDtoPutPost;
import com.co.prueba.dto.ShoppingCarDtoGet;
import com.co.prueba.entity.Coupon;
import com.co.prueba.entity.Sale;
import com.co.prueba.entity.ShoppingCar;
import com.co.prueba.exception.StoreException;
import com.co.prueba.mapper.SaleMapper;
import com.co.prueba.repository.SaleRepository;
import com.co.prueba.utils.Utils;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	@Autowired
	private CouponService couponService;
	
	private SaleMapper mapper = Mappers.getMapper(SaleMapper.class);
	
	
	public SaleDto createOrUpdate(SaleDtoPutPost saleDto) throws StoreException {
		
		List<ShoppingCar> shoppings = saleDto.getShoppingCars().stream().map(shoppingCarDto-> {
			ShoppingCar shoppingCar =  null;
			try {
				
				shoppingCar = shoppingCarService.createUpate(shoppingCarDto);
			} catch (StoreException e) {
				e.printStackTrace();
			}
			return shoppingCar;
		}).toList();
		Coupon coupon = null;
		if(!Utils.ValidateNullVoidString(saleDto.getIdCupon())) {
			coupon =  couponService.findByName(saleDto.getIdCupon());
		}
		Sale sale = mapper.saleDtoToSale(saleDto, coupon, shoppings);
		sale = repository.save(sale);
		return buildSaleDto(sale);
		
	}
	
	public SaleDto findSale(String id) {
		Sale sale = repository.findById(id).orElse(null);
		return null == sale ? null : buildSaleDto(sale);
	}
	
	private SaleDto buildSaleDto(Sale sale ) {
		List<ShoppingCarDtoGet> shoppingCarsDtoGet = sale.getIdShoppingCars().stream().map(shoppingCar -> {
			ShoppingCarDtoGet shoppingCarDtoGet = null;
			try {
				shoppingCarDtoGet = shoppingCarService.findById(shoppingCar.getId(), sale.getSaleDate());
			} catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return shoppingCarDtoGet;
		}).toList();
		BigDecimal subTotal = BigDecimal.valueOf(shoppingCarsDtoGet.stream().mapToDouble(shoppingCarDtoGet -> shoppingCarDtoGet.getTotal().doubleValue()).sum());
		BigDecimal total = subTotal;
		String valueStr = null;
		String valueName = null;
		if(null != sale.getIdCoupon()) {
			Coupon couponInteraction =  sale.getIdCoupon();
			BigDecimal value = BigDecimal.ZERO;
			valueName = couponInteraction.getName();
			if(couponInteraction.getIsValue()) {
				value = couponInteraction.getValue();
				valueStr = "$".concat(value.toString());
			}else {
				value= subTotal.divide((couponInteraction.getPercentage().divide(subTotal).divide(new BigDecimal(100))));
				valueStr = couponInteraction.getPercentage().toString().concat("%");
			}
			total = subTotal.subtract(value);
		}
		
		//BigDecimal value =  ? sale.getIdCoupon().getValue() : sale.getIdCoupon().getPercentage(); 
		
		SaleDto saleGetDto = mapper.saleToSaleDto(sale, shoppingCarsDtoGet, valueName, valueStr);
		saleGetDto.setSubTotal(subTotal);
		saleGetDto.setTotal(total);
		return saleGetDto;
	}

}
