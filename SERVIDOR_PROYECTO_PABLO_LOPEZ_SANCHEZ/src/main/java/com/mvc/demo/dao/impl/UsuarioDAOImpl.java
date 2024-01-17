package com.mvc.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.demo.dao.IDAOUsuario;
import com.mvc.demo.domain.entity.UsuarioEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UsuarioDAOImpl implements IDAOUsuario {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(UsuarioEntity usuario) {
		try {
			em.persist(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void merge(UsuarioEntity usuario) {
		try {
	        em.merge(usuario);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public void remove(UsuarioEntity usuario) {
		try {
			em.remove(usuario);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public UsuarioEntity findById(int id) {
		UsuarioEntity usuario = null;
		try {
			usuario = em.find(UsuarioEntity.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return usuario;
	}

	@Override
	public List<UsuarioEntity> findAll() {
		List<UsuarioEntity> lstUsuarios = null;
		TypedQuery<UsuarioEntity> query = null;
		
		try {
			query = em.createQuery("select r from UsuarioEntity r ",UsuarioEntity.class);
			lstUsuarios = query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lstUsuarios;
	}

}
