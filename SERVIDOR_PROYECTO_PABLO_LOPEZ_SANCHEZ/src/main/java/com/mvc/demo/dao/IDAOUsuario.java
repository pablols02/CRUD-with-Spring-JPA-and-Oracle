package com.mvc.demo.dao;

import java.util.List;

import com.mvc.demo.domain.entity.UsuarioEntity;

public interface IDAOUsuario {
	public void save(UsuarioEntity usuario);
	public void merge(UsuarioEntity usuario);
	public void remove(UsuarioEntity usuario);
	public UsuarioEntity findById(int id);
	public List<UsuarioEntity> findAll();
}
