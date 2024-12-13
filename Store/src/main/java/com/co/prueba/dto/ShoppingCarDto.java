package com.co.prueba.dto;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class ShoppingCarDto implements Serializable {

	private static final long serialVersionUID = 1301461763380228939L;

	@Getter
	@Setter
    private String id;
	
	@Getter
	@Setter
	private String idUser;
	
	@Getter
	@Setter
	private String descriptionProducts;
	
	@Getter
	@Setter
	private String idProduct;
	
	@Getter
	@Setter
	private BigDecimal priceProducts;
	
	@Getter
	@Setter
	private Long quantity;
	
	@Getter
	@Setter
	private BigDecimal subTotal;
	
	@Getter
	@Setter
	private BigDecimal total;
	
	

}