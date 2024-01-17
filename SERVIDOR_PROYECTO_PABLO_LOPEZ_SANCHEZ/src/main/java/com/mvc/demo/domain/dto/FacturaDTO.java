package com.mvc.demo.domain.dto;

import java.time.LocalDate;

public class FacturaDTO {
	
	private Integer idFactura;
	private LocalDate fechaEmision;
	private double precioTotal;
	
	public FacturaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacturaDTO(LocalDate fechaEmision, double precioTotal) {
		super();
		this.fechaEmision = fechaEmision;
		this.precioTotal = precioTotal;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
}
