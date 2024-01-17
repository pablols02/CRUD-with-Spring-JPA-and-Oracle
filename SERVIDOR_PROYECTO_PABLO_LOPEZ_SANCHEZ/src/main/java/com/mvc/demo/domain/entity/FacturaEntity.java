package com.mvc.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FACTURA")
public class FacturaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFactura")
	@SequenceGenerator(name = "seqFactura", allocationSize = 1, sequenceName = "SEQ_FACTURA")
	@Column(name = "ID_FACTURA")
	private Integer idFactura;
	
	@Column(name = "FECHA_EMISION")
	private LocalDate fechaEmision;
	
	@Column(name = "PRECIO_TOTAL")
	private double precioTotal;

	public FacturaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacturaEntity(Integer idFactura, LocalDate fechaEmision, double precioTotal) {
		super();
		this.idFactura = idFactura;
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
