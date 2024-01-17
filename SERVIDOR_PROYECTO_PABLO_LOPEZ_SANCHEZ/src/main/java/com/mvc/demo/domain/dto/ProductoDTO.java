package com.mvc.demo.domain.dto;

import java.util.List;

public class ProductoDTO {

	private Integer idProducto;
	private String descripcion;
    private int existencias;
    private double precio;
    private FabricanteDTO fabricante;
    private List<PedidoDTO> pedidos;
    private boolean estaEnLaLista;
    
	public ProductoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoDTO(String descripcion, int existencias, double precio, FabricanteDTO fabricante, List<PedidoDTO> pedidos) {
		super();
		this.descripcion = descripcion;
		this.existencias = existencias;
		this.precio = precio;
		this.fabricante = fabricante;
		this.pedidos = pedidos;
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

	public FabricanteDTO getFabricante() {
		return fabricante;
	}

	public void setFabricante(FabricanteDTO fabricante) {
		this.fabricante = fabricante;
	}

	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}

	public boolean isEstaEnLaLista() {
		return estaEnLaLista;
	}

	public void setEstaEnLaLista(boolean estaEnLaLista) {
		this.estaEnLaLista = estaEnLaLista;
	}
    
}
