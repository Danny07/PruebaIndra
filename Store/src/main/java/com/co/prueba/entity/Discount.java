package com.co.prueba.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "discounts")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class Discount implements Serializable {

	private static final long serialVersionUID = 4688790520855687328L;
	
	@Id
	@Getter
	@Setter
	@SequenceGenerator(name = "seqDiscounts", sequenceName = "seq_discounts", allocationSize = 1)
	private Long id;
	
	@Getter
	@Setter
	@Column
	private BigDecimal percentage;
	
	@Getter
	@Setter
	@Column(name = "begin_date")
	@Temporal(TemporalType.DATE)  
	private Date beginDate;
	
	@Getter
	@Setter
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

}
