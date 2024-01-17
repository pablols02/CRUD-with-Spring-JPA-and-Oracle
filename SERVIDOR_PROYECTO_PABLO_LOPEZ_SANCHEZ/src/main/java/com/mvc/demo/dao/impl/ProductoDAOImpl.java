package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAOProducto;
import com.mvc.demo.domain.entity.ProductoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductoDAOImpl implements IDAOProducto {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(ProductoEntity producto) {
		try {
			em.persist(producto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void merge(ProductoEntity producto) {
		try {
	        em.merge(producto);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public void remove(ProductoEntity producto) {
		try {
			em.remove(producto);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public ProductoEntity findById(int id) {
		ProductoEntity producto = null;
		try {
			producto = em.find(ProductoEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return producto;
	}

	@Override
	public List<ProductoEntity> findAll() {
		List<ProductoEntity> lstProductos = null;
		TypedQuery<ProductoEntity> query = null;
		
		try {
			query = em.createQuery("select p from ProductoEntity p ",ProductoEntity.class);
			lstProductos = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstProductos;
	}
}
