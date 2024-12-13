package com.co.prueba.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class Sale implements Serializable {

	private static final long serialVersionUID = 7359681077989615537L;
	
	@Id
	@Getter
	@Setter
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Getter
	@Setter
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sale")
	private List<ShoppingCar> idShoppingCars;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_coupon")
	private Coupon idCoupon;
	
	@Getter
	@Setter
	@Column(name = "sale_date")
	private Date saleDate;
}
