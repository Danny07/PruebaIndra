package com.co.prueba.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tipo_transaccion")
public class TipoTransaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_transaccion")
	private Long idTipoTransaccion;
	
	@Column(name = "tipo")
	private String tipo;

	public TipoTransaccion() {
	}

	public TipoTransaccion(Long idTipoTransaccion, String tipo) {
		this.idTipoTransaccion = idTipoTransaccion;
		this.tipo = tipo;
	}

	public Long getIdTipoTransaccion() {
		return idTipoTransaccion;
	}

	public void setIdTipoTransaccion(Long idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
