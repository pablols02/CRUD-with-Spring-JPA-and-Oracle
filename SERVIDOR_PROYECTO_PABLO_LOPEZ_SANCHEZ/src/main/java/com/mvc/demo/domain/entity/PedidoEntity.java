package com.mvc.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class PedidoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPedido")
	@SequenceGenerator(name = "seqPedido", allocationSize = 1, sequenceName = "SEQ_PEDIDO")
	@Column(name = "ID_PEDIDO")
	private Integer idPedido;
	
	@Column(name = "FECHA_PEDIDO")
	private LocalDate fechaPedido;
	
	@Column(name = "FECHA_ENTREGA")
	private LocalDate fechaEntrega;
	
	@ManyToOne
    @JoinColumn(name = "CLIENTE_DNI", referencedColumnName = "DNI")
    private ClienteEntity cliente;
	
	@OneToOne
	@JoinColumn(name = "FACTURA_ID")
	private FacturaEntity factura;
	
	@JoinTable(name = "PRODUCTO_PEDIDO",
    		joinColumns = @JoinColumn(name = "ID_PEDIDO"),
    		inverseJoinColumns = @JoinColumn(name = "ID_PRODUCTO")
    )
    @ManyToMany
	private List<ProductoEntity> lstProductos;

	public PedidoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedidoEntity(Integer idPedido, LocalDate fechaPedido, LocalDate fechaEntrega, ClienteEntity cliente,
			FacturaEntity factura, List<ProductoEntity> lstProductos) {
		super();
		this.idPedido = idPedido;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.cliente = cliente;
		this.factura = factura;
		this.lstProductos = lstProductos;
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

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public FacturaEntity getFactura() {
		return factura;
	}

	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	public List<ProductoEntity> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(List<ProductoEntity> lstProductos) {
		this.lstProductos = lstProductos;
	}
	
}
