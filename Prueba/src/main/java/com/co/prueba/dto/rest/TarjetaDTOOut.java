package com.co.prueba.dto.rest;

import java.io.Serializable;
import java.math.BigDecimal;

public class TarjetaDTOOut implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numeroTarjeta;
	
	private String titularCuenta;
	
	private String fechaVencimienta;
	
	private BigDecimal saldo;

	public TarjetaDTOOut() {
	}

	public TarjetaDTOOut(Long numeroTarjeta, String titularCuenta, String fechaVencimienta, BigDecimal saldo) {
		this.numeroTarjeta = numeroTarjeta;
		this.titularCuenta = titularCuenta;
		this.fechaVencimienta = fechaVencimienta;
		this.saldo = saldo;
	}

	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTitularCuenta() {
		return titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public String getFechaVencimienta() {
		return fechaVencimienta;
	}

	public void setFechaVencimienta(String fechaVencimienta) {
		this.fechaVencimienta = fechaVencimienta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
}
