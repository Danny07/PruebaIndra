package com.co.prueba.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "shopping_cars")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class ShoppingCar implements Serializable {

	private static final long serialVersionUID = 1115485407543919564L;
	
	@Id
	@Getter
	@Setter
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_users")
	private User idUser;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_products")
	private Product idProduct;
	
	@Getter
	@Setter
	@Column
	private Long quantity;
	
	@Getter
	@Setter
	@Column(name = "is_sale")
	private Boolean isSale;
	
	/*@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
    
	private Sale sale;*/

}
