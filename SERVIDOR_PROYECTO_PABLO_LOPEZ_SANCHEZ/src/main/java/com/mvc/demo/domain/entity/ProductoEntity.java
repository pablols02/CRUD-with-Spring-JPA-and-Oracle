package com.mvc.demo.domain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class ProductoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProducto")
	@SequenceGenerator(name = "seqProducto", allocationSize = 1, sequenceName = "SEQ_PRODUCTO")
	@Column(name = "ID_PRODUCTO")
	private Integer idProducto;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "EXISTENCIAS")
    private int existencias;
	
	@Column(name = "PRECIO")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "FABRICANTE_CIF", referencedColumnName = "CIF")
    private FabricanteEntity fabricante;
    
    @ManyToMany(mappedBy = "lstProductos")
    private List<PedidoEntity> lstPedidos;

	public ProductoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoEntity(Integer idProducto, String descripcion, int existencias, double precio,
			FabricanteEntity fabricante, List<PedidoEntity> lstPedidos) {
		super();
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.existencias = existencias;
		this.precio = precio;
		this.fabricante = fabricante;
		this.lstPedidos = lstPedidos;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public FabricanteEntity getFabricante() {
		return fabricante;
	}

	public void setFabricante(FabricanteEntity fabricante) {
		this.fabricante = fabricante;
	}

	public List<PedidoEntity> getLstPedidos() {
		return lstPedidos;
	}

	public void setLstPedidos(List<PedidoEntity> lstPedidos) {
		this.lstPedidos = lstPedidos;
	}
    
}
