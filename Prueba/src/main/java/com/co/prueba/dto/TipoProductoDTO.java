package com.co.prueba.dto;

import java.io.Serializable;

public class TipoProductoDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long idTipoProducto;
	
	private Long numeroProducto;
	
	private String nombreProducto;
	

	public TipoProductoDTO() {}

	public TipoProductoDTO(Long idTipoProducto, Long numeroProducto, String nombreProducto) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.numeroProducto = numeroProducto;
		this.nombreProducto = nombreProducto;
	}

	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public Long getNumeroProducto() {
		return numeroProducto;
	}

	public void setNumeroProducto(Long numeroProducto) {
		this.numeroProducto = numeroProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	

}
