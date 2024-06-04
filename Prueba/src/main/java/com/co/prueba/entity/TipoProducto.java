package com.co.prueba.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tipo_producto")
public class TipoProducto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_producto")
	private Long idTipoProducto;
	
	@Column(name = "numero_producto")
	private Long numeroProducto;
	
	@Column(name = "nombre_producto")
	private String nombreProducto;

	public TipoProducto() {}

	public TipoProducto(Long idTipoProducto, Long numeroProducto, String nombreProducto) {
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
