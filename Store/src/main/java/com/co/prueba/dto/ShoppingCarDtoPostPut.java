package com.co.prueba.dto;

import java.io.Serializable;

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
public class ShoppingCarDtoPostPut implements Serializable {

	private static final long serialVersionUID = 9026159584798849620L;

	@Getter
	@Setter
    private String id;
	
	@Getter
	@Setter
	private String idUser;
	
	@Getter
	@Setter
    private String idProduct;
	
	@Getter
	@Setter
	private Long quantity;

}
