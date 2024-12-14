package com.co.prueba.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
public class ShoppingCarDtoPost implements Serializable {

	private static final long serialVersionUID = 9026159584798849620L;

	@Getter
	@Setter
	@NotEmpty(message="id not empty.")
	@NotNull(message = "id not null.")
	private String idUser;
	
	@Getter
	@Setter
	@NotEmpty(message="id not empty.")
	@NotNull(message = "id not null.")
    private String idProduct;
	
	@Getter
	@Setter
	@Min(value = 0, message = "quantity min 0")
	private Long quantity;

}
