package com.mvc.demo.dao;

import java.util.List;

import com.mvc.demo.domain.entity.FacturaEntity;

public interface IDAOFactura {
	public void save(FacturaEntity factura);
	public void merge(FacturaEntity factura);
	public void remove(FacturaEntity factura);
	public FacturaEntity findById(int id);
	public List<FacturaEntity> findAll();
}
