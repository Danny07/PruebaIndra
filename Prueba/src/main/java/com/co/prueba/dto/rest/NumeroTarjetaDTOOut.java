package com.co.prueba.dto.rest;

import java.io.Serializable;

public class NumeroTarjetaDTOOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long numeroTarjeta;

	public NumeroTarjetaDTOOut() {
	}

	public NumeroTarjetaDTOOut(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	
}
