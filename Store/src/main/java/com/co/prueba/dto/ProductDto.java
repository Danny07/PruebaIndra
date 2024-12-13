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
public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = 8243487493603673835L;

	@Getter
	@Setter
    private String id;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private BigDecimal price;

}
