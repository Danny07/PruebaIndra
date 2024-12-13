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
public class DiscountProductDto implements Serializable {
	
	private static final long serialVersionUID = 4133704247089576634L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private BigDecimal percentage;
	
	@Getter
	@Setter
	private String idProducts;
	
	

}
