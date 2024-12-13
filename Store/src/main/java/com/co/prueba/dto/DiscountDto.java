package com.co.prueba.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class DiscountDto implements Serializable {
	
	private static final long serialVersionUID = -2142080340238317426L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private BigDecimal percentage;
	
	@Getter
	@Setter
	private Date beginDate;
	
	@Getter
	@Setter
	private Date endDate;

}
