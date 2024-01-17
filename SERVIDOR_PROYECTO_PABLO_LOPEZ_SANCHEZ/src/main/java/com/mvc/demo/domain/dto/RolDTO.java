package com.mvc.demo.domain.dto;

public class RolDTO {
	
	private Integer idRol;
    private String descripcion;
    
	public RolDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolDTO(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}
