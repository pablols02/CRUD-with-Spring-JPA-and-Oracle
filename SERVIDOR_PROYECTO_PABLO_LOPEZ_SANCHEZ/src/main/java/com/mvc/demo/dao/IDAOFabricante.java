package com.mvc.demo.dao;

import java.util.List;

import com.mvc.demo.domain.entity.FabricanteEntity;

public interface IDAOFabricante {
	public void save(FabricanteEntity fabricante);
	public void merge(FabricanteEntity fabricante);
	public void remove(FabricanteEntity fabricante);
	public FabricanteEntity findById(String cif);
	public List<FabricanteEntity> findAll();
}
