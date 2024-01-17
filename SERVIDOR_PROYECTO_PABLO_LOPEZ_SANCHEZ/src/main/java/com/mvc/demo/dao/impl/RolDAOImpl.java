package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAORol;
import com.mvc.demo.domain.entity.RolEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class RolDAOImpl implements IDAORol {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(RolEntity rol) {
		try {
			em.persist(rol);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void merge(RolEntity rol) {
		try {
	        em.merge(rol);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public void remove(RolEntity rol) {
		try {
			em.remove(rol);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public RolEntity findById(int id) {
		RolEntity rol = null;
		try {
			rol = em.find(RolEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rol;
	}

	@Override
	public List<RolEntity> findAll() {
		List<RolEntity> lstRoles = null;
		TypedQuery<RolEntity> query = null;
		
		try {
			query = em.createQuery("select r from RolEntity r ",RolEntity.class);
			lstRoles = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstRoles;
	}

}
