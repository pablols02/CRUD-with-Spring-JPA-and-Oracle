package com.mvc.demo.service;

import java.util.List;

import com.mvc.demo.domain.dto.ProductoDTO;

public interface IServiceProducto {
	public void guardarProducto(ProductoDTO productoDTO);
	public void actualizarProducto(ProductoDTO productoDTO);
	public void eliminarProducto(Integer idProducto);
	public ProductoDTO obtenerProductoPorId(Integer idProducto);
	public List<ProductoDTO> listarTodosLosProductos();
}
