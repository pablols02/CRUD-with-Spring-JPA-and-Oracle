package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAOCliente;
import com.mvc.demo.domain.entity.ClienteEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ClienteDAOImpl implements IDAOCliente{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(ClienteEntity cliente) {
		try {
			em.persist(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void merge(ClienteEntity cliente) {
		try {
	        em.merge(cliente);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public void remove(ClienteEntity cliente) {
		try {
			em.remove(cliente);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public ClienteEntity findById(String dni) {
		ClienteEntity cliente = null;
		try {
			cliente = em.find(ClienteEntity.class, dni);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cliente;
	}

	@Override
	public List<ClienteEntity> findAll() {
		List<ClienteEntity> lstClientes = null;
		TypedQuery<ClienteEntity> query = null;
		
		try {
			query = em.createQuery("select c from ClienteEntity c ",ClienteEntity.class);
			lstClientes = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstClientes;
	}

}
