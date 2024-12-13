package com.co.prueba.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class SaleDto implements Serializable {
	
	private static final long serialVersionUID = -2654568346574067755L;

	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private List<ShoppingCarDtoGet> idShoppingCars;
	
	@Getter
	@Setter
	private String nameCoupon;
	
	@Getter
	@Setter
	private String valueCoupon;
	
	@Getter
	@Setter
	private Date saleDate;
	
	@Getter
	@Setter
	private BigDecimal subTotal;
	
	@Getter
	@Setter
	private BigDecimal total;
}
