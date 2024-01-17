package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAOFabricante;
import com.mvc.demo.domain.entity.FabricanteEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class FabricanteDAOImpl implements IDAOFabricante {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(FabricanteEntity fabricante) {
		try {
			em.persist(fabricante);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void merge(FabricanteEntity fabricante) {
		try {
	        em.merge(fabricante);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public void remove(FabricanteEntity fabricante) {
		try {
			em.remove(fabricante);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public FabricanteEntity findById(String cif) {
		FabricanteEntity fabricante = null;
		try {
			fabricante = em.find(FabricanteEntity.class, cif);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return fabricante;
	}

	@Override
	public List<FabricanteEntity> findAll() {
		List<FabricanteEntity> lstFabricantes = null;
		TypedQuery<FabricanteEntity> query = null;
		
		try {
			query = em.createQuery("select f from FabricanteEntity f ",FabricanteEntity.class);
			lstFabricantes = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstFabricantes;
	}

}
