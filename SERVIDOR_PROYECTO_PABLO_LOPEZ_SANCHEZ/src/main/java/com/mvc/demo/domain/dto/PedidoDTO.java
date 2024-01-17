package com.mvc.demo.domain.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {

	private Integer idPedido;
	private LocalDate fechaPedido;
	private LocalDate fechaEntrega;
    private ClienteDTO cliente;
	private FacturaDTO factura;
	private List<ProductoDTO> productos;
	
	public PedidoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedidoDTO(LocalDate fechaPedido, LocalDate fechaEntrega, ClienteDTO cliente, FacturaDTO factura, List<ProductoDTO> productos) {
		super();
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.cliente = cliente;
		this.factura = factura;
		this.productos = productos;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public FacturaDTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}

	public List<ProductoDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDTO> productos) {
		this.productos = productos;
	}
	
}
