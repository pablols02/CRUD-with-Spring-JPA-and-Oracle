package com.mvc.demo.dao;

import java.util.List;

import com.mvc.demo.domain.entity.RolEntity;

public interface IDAORol {
	public void save(RolEntity rol);
	public void merge(RolEntity rol);
	public void remove(RolEntity rol);
	public RolEntity findById(int id);
	public List<RolEntity> findAll();
}
