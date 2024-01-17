package com.mvc.demo.dao;

import java.util.List;

import com.mvc.demo.domain.entity.PedidoEntity;

public interface IDAOPedido {
	public void save(PedidoEntity pedido);
	public void merge(PedidoEntity pedido);
	public void remove(PedidoEntity pedido);
	public PedidoEntity findById(int id);
	public List<PedidoEntity> findAll();
}
