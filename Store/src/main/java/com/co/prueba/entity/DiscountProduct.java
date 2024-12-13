package com.co.prueba.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "discounts_products")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class DiscountProduct implements Serializable {

	private static final long serialVersionUID = -7803956822975048040L;
	
	@Id
	@Getter
	@Setter
	@SequenceGenerator(name = "seqDiscountsProducts", sequenceName = "seq_discounts_products", allocationSize = 1)
	private Long id;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_discounts")
	private Discount idDiscounts;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_products")
	private Product idProducts;
	
	

}
