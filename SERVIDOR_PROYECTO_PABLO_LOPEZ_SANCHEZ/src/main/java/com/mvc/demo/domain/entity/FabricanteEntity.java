package com.mvc.demo.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FABRICANTE")
public class FabricanteEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CIF")
	private String cif;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CLASIFICACION")
	private int clasificacion;

	public FabricanteEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FabricanteEntity(String cif, String nombre, int clasificacion) {
		super();
		this.cif = cif;
		this.nombre = nombre;
		this.clasificacion = clasificacion;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}
	
}
