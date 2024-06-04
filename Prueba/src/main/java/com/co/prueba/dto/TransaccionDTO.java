package com.co.prueba.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransaccionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTransacccion;
	
	private Long tarjeta;
	
	private Long tipoTransaccion;
	
	private BigDecimal valor;
	
	private Date fechaTransaccion;
	
	private boolean habilitada;

	public TransaccionDTO() {
	}

	public TransaccionDTO(Long idTransacccion, Long tarjeta, Long tipoTransaccion, BigDecimal valor,
			Date fechaTransaccion, boolean habilitada) {
		this.idTransacccion = idTransacccion;
		this.tarjeta = tarjeta;
		this.tipoTransaccion = tipoTransaccion;
		this.valor = valor;
		this.fechaTransaccion = fechaTransaccion;
		this.habilitada = habilitada;
	}

	public Long getIdTransacccion() {
		return idTransacccion;
	}

	public void setIdTransacccion(Long idTransacccion) {
		this.idTransacccion = idTransacccion;
	}

	public Long getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Long tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Long getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(Long tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

}
