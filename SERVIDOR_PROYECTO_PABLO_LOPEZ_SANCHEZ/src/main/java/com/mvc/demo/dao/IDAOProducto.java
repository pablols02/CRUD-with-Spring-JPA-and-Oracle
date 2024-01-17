package com.mvc.demo.dao;

import java.util.List;

import com.mvc.demo.domain.entity.ProductoEntity;

public interface IDAOProducto {
	public void save(ProductoEntity producto);
	public void merge(ProductoEntity producto);
	public void remove(ProductoEntity producto);
	public ProductoEntity findById(int id);
	public List<ProductoEntity> findAll();
}
