package com.mvc.demo.service;

import java.util.List;
import com.mvc.demo.domain.dto.PedidoDTO;

public interface IServicePedido {
	public void guardarPedido(PedidoDTO pedidoDTO);
	public void actualizarPedido(PedidoDTO pedidoDTO);
	public void eliminarPedido(Integer idPedido);
	public PedidoDTO obtenerPedidoPorId(Integer idPedido);
	public List<PedidoDTO> listarTodosLosPedidos();
}
