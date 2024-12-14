package com.co.prueba.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Builder
@Data
public class SaleDtoPost implements Serializable {
	
	private static final long serialVersionUID = -6642478722050946473L;
	
	@Getter
	@Setter
	@Valid
	private List<ShoppingCarDtoPost> shoppingCars;
	
	@Getter
	@Setter
	private String idCupon;
	
	@Getter
	@Setter
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date saleDate;
}
