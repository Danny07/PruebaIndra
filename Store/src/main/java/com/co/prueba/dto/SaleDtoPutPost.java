package com.co.prueba.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class SaleDtoPutPost implements Serializable {
	
	private static final long serialVersionUID = -6642478722050946473L;

	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private List<ShoppingCarDtoPostPut> shoppingCars;
	
	@Getter
	@Setter
	private String idCupon;
	
	@Getter
	@Setter
	private Date saleDate;
}
