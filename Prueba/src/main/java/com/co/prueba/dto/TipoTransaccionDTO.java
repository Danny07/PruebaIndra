package com.co.prueba.dto;

import java.io.Serializable;

public class TipoTransaccionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTipoTransaccion;
	
	private String tipo;

	public TipoTransaccionDTO() {
	}

	public TipoTransaccionDTO(Long idTipoTransaccion, String tipo) {
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
