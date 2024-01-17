package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAOFactura;
import com.mvc.demo.domain.entity.FacturaEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class FacturaDAOImpl implements IDAOFactura {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(FacturaEntity factura) {
		try {
			em.persist(factura);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void merge(FacturaEntity factura) {
		try {
	        em.merge(factura);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public void remove(FacturaEntity factura) {
		try {
			em.remove(factura);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public FacturaEntity findById(int id) {
		FacturaEntity factura = null;
		try {
			factura = em.find(FacturaEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return factura;
	}

	@Override
	public List<FacturaEntity> findAll() {
		List<FacturaEntity> lstFacturas = null;
		TypedQuery<FacturaEntity> query = null;
		
		try {
			query = em.createQuery("select f from FacturaEntity f ",FacturaEntity.class);
			lstFacturas = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstFacturas;
	}
	

}
