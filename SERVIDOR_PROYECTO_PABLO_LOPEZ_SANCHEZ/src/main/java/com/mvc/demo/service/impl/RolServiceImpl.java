package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAORol;
import com.mvc.demo.domain.dto.RolDTO;
import com.mvc.demo.domain.entity.RolEntity;
import com.mvc.demo.service.IServiceRol;

@Service
public class RolServiceImpl implements IServiceRol {

	@Autowired
	private IDAORol rolDAO;
	
	@Override
	@Transactional
	public void guardarRol(RolDTO rolDTO) {
		RolEntity rolEntity = new RolEntity();
	    try {
	    	rolEntity.setDescripcion(rolDTO.getDescripcion());
	        rolDAO.save(rolEntity);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	@Transactional
	public void actualizarRol(RolDTO rolDTO) {
		RolEntity rolEntity = new RolEntity();
		rolEntity.setIdRol(rolDTO.getIdRol());
		rolEntity.setDescripcion(rolDTO.getDescripcion());
		
	    try {
	    	rolDAO.merge(rolEntity);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	@Transactional
	public void eliminarRol(Integer idRol) {
		RolEntity rol = rolDAO.findById(idRol);
	    if (rol != null) {
	    	rolDAO.remove(rol);
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public RolDTO obtenerRolPorId(Integer idRol) {
		RolEntity rolEntity = rolDAO.findById(idRol);
		RolDTO rolDTO = null;
	    	try {
	    		rolDTO = new RolDTO();
	    		rolDTO.setIdRol(rolEntity.getIdRol());
	    		rolDTO.setDescripcion(rolEntity.getDescripcion());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

	    return rolDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RolDTO> listarTodosLosRoles() {
		List<RolEntity> rolesEntities = rolDAO.findAll();
	    List<RolDTO> rolesDTO = new ArrayList<>();
	    for (RolEntity rolEntity : rolesEntities) {
	    	RolDTO rolDTO = new RolDTO();
	    	rolDTO.setIdRol(rolEntity.getIdRol());
	    	rolDTO.setDescripcion(rolEntity.getDescripcion());
	    	rolesDTO.add(rolDTO);
	    }
	    return rolesDTO;
	}

}
