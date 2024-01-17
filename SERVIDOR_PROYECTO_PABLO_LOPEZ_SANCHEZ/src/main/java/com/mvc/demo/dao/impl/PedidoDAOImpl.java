package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAOPedido;
import com.mvc.demo.domain.entity.PedidoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class PedidoDAOImpl implements IDAOPedido {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(PedidoEntity pedido) {
		try {
	        em.persist(pedido);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public void merge(PedidoEntity pedido) {
		try {
			em.merge(pedido);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void remove(PedidoEntity pedido) {
		try {
			em.remove(pedido);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public PedidoEntity findById(int id) {
		PedidoEntity pedido = null;
	    try {
	        pedido = em.find(PedidoEntity.class, id);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    return pedido;
	}

	@Override
	public List<PedidoEntity> findAll() {
		List<PedidoEntity> lstPedidos = null;
		TypedQuery<PedidoEntity> query = null;
		
		try {
			query = em.createQuery("select p from PedidoEntity p ",PedidoEntity.class);
			lstPedidos = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstPedidos;
	}

}
