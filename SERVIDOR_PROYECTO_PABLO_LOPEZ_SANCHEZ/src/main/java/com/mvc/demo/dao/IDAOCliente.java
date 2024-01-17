package com.mvc.demo.dao;

import java.util.List;
import com.mvc.demo.domain.entity.ClienteEntity;

public interface IDAOCliente {
	public void save(ClienteEntity cliente);
	public void merge(ClienteEntity cliente);
	public void remove(ClienteEntity cliente);
	public ClienteEntity findById(String dni);
	public List<ClienteEntity> findAll();
}
