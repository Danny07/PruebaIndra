package com.co.prueba.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class SaleDtoPut implements Serializable {
	
	private static final long serialVersionUID = -6642478722050946473L;

	@Getter
	@Setter
	@NotNull
	private String id;
	
	@Getter
	@Setter
	@Valid
	private List<ShoppingCarDtoPut> shoppingCars;
	
	@Getter
	@Setter
	private String idCupon;
	
	@Getter
	@Setter
	@NotNull
	private Date saleDate;
}
