package com.co.prueba.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = -7253585101349925352L;
	
	@Id
	@Getter
	@Setter
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Getter
	@Setter
	@Column
	private String description;
	
	@Getter
	@Setter
	@Column
	private BigDecimal price;

}
