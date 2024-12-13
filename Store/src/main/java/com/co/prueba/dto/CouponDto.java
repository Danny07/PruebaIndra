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
public class CouponDto implements Serializable {

	private static final long serialVersionUID = 648646021779928404L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private BigDecimal value;
	
	@Getter
	@Setter
	private BigDecimal percentage;
	
	@Getter
	@Setter
	private Boolean isValue;
}
