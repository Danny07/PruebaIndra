package com.co.prueba.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TarjetaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numero;
	
	private Long cliente;
	
	private Date fechaVencimiento;
	
	private boolean unicamenteDolar;
	
	private boolean activa;
	
	private boolean bloqueada;
	
	private BigDecimal saldo;

	public TarjetaDTO() {
	}

	public TarjetaDTO(Long numero, Long cliente, Date fechaVencimiento, boolean unicamenteDolar, boolean activa,
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

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
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
