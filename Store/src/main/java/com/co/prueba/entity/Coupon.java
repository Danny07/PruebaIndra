package com.co.prueba.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "coupon")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class Coupon implements Serializable {

	private static final long serialVersionUID = 5219933208635477481L;

	@Id
	@Getter
	@Setter
	@SequenceGenerator(name = "seqCoupon", sequenceName = "seq_coupon", allocationSize = 1)
	private Long id;
	
	@Getter
	@Setter
	@Column(unique = true)
	private String name;
	
	@Getter
	@Setter
	@Column
	private BigDecimal value;
	
	@Getter
	@Setter
	@Column
	private BigDecimal percentage;
	
	@Getter
	@Setter
	@Column(name = "isvalue")
	private Boolean isValue;
}
