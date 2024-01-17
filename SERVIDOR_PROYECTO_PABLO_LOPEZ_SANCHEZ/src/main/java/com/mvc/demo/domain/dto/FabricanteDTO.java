package com.mvc.demo.domain.dto;

public class FabricanteDTO {

	private String cif;
    private String nombre;
    private int clasificacion;
    
	public FabricanteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FabricanteDTO(String cif, String nombre, int clasificacion) {
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
