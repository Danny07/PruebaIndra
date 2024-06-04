package com.co.prueba.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "tarjeta")
public class Tarjeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "numero")
	private Long numero;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column(name = "fecha_vencimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	@Column(name = "unicamente_dolar")
	private boolean unicamenteDolar;
	
	@Column(name = "activa")
	private boolean activa;
	
	@Column(name = "bloqueada")
	private boolean bloqueada;
	
	@Column(name = "saldo")
	private BigDecimal saldo;

	public Tarjeta() {
	}

	public Tarjeta(Long numero, Cliente cliente, Date fechaVencimiento, boolean unicamenteDolar, boolean activa,
			boolean bloqueada, BigDecimal saldo) {
		this.numero = numero;
		this.cliente = cliente;
		this.fechaVencimiento = fechaVencimiento;
		this.unicamenteDolar = unicamenteDolar;
		this.activa = activa;
		this.bloqueada = bloqueada;
		this.saldo = saldo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isUnicamenteDolar() {
		return unicamenteDolar;
	}

	public void setUnicamenteDolar(boolean unicamenteDolar) {
		this.unicamenteDolar = unicamenteDolar;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
