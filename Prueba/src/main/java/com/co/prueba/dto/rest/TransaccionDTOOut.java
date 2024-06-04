package com.co.prueba.dto.rest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransaccionDTOOut implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idTransacccion;
	
	private TarjetaDTOOut tarjeta;
	
	private String tipoTransaccion;
	
	private BigDecimal valor;
	
	private Date fechaTransaccion;

	public TransaccionDTOOut() {
	}

	public TransaccionDTOOut(Long idTransacccion, TarjetaDTOOut tarjeta, String tipoTransaccion, BigDecimal valor,
			Date fechaTransaccion) {
		this.idTransacccion = idTransacccion;
		this.tarjeta = tarjeta;
		this.tipoTransaccion = tipoTransaccion;
		this.valor = valor;
		this.fechaTransaccion = fechaTransaccion;
	}

	public Long getIdTransacccion() {
		return idTransacccion;
	}

	public void setIdTransacccion(Long idTransacccion) {
		this.idTransacccion = idTransacccion;
	}

	public TarjetaDTOOut getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaDTOOut tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
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
	
	

}
