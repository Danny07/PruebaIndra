package com.co.prueba.entity;

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

@Entity(name = "transaccion")
public class Transaccion  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_transaccion")
	private Long idTransacccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tarjeta")
	private Tarjeta tarjeta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_transaccion")
	private TipoTransaccion tipoTransaccion;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "fecha_transaccion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTransaccion;
	
	@Column(name = "habilitada")
	private boolean habilitada;

	public Transaccion() {
	}

	public Transaccion(Long idTransacccion, Tarjeta tarjeta, TipoTransaccion tipoTransaccion, BigDecimal valor,
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

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
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
