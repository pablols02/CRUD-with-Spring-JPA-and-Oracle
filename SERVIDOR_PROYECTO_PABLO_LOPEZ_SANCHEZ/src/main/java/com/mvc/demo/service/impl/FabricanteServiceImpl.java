package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAOFabricante;
import com.mvc.demo.domain.dto.FabricanteDTO;
import com.mvc.demo.domain.entity.FabricanteEntity;
import com.mvc.demo.exception.MiExcepcion;
import com.mvc.demo.service.IServiceFabricante;

@Service
public class FabricanteServiceImpl implements IServiceFabricante {

	@Autowired
	private IDAOFabricante fabricanteDAO;
	
	@Override
	@Transactional
	public void guardarFabricante(FabricanteDTO fabricanteDTO) throws MiExcepcion {
		FabricanteEntity fabricanteEntity = null;
		try {
			fabricanteEntity = fabricanteDAO.findById(fabricanteDTO.getCif());
			
			if (null != fabricanteEntity || fabricanteDTO.getCif() == "") {
				throw new MiExcepcion(100, "Error en cliente duplicado");
			}
			fabricanteEntity = new FabricanteEntity();
			fabricanteEntity.setCif(fabricanteDTO.getCif());
			fabricanteEntity.setNombre(fabricanteDTO.getNombre());
			fabricanteEntity.setClasificacion(fabricanteDTO.getClasificacion());
			
			fabricanteDAO.save(fabricanteEntity);
		} catch (MiExcepcion e) {
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void actualizarFabricante(FabricanteDTO fabricanteDTO) {
		FabricanteEntity fabricanteEntity = new FabricanteEntity();
		fabricanteEntity.setCif(fabricanteDTO.getCif());
		fabricanteEntity.setNombre(fabricanteDTO.getNombre());
		fabricanteEntity.setClasificacion(fabricanteDTO.getClasificacion());
		
		try {
			fabricanteDAO.merge(fabricanteEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void eliminarFabricante(String cif) {
		FabricanteEntity fabricante = fabricanteDAO.findById(cif);
		if(fabricante != null) {
			fabricanteDAO.remove(fabricante);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public FabricanteDTO obtenerFabricantePorId(String cif) {
		FabricanteEntity fabricanteEntity = fabricanteDAO.findById(cif);
		FabricanteDTO fabricanteDTO = null;
		try {
			fabricanteDTO = new FabricanteDTO();
			fabricanteDTO.setCif(fabricanteEntity.getCif());
			fabricanteDTO.setNombre(fabricanteEntity.getNombre());
			fabricanteDTO.setClasificacion(fabricanteEntity.getClasificacion());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return fabricanteDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FabricanteDTO> listarTodosLosFabricantes() {
		List<FabricanteEntity> fabricantesEntities = fabricanteDAO.findAll();
		List<FabricanteDTO> fabricantesDTO = new ArrayList<>();
		for(FabricanteEntity fabricanteEntity : fabricantesEntities) {
			FabricanteDTO fabricanteDTO = new FabricanteDTO();
			fabricanteDTO.setCif(fabricanteEntity.getCif());
			fabricanteDTO.setNombre(fabricanteEntity.getNombre());
			fabricanteDTO.setClasificacion(fabricanteEntity.getClasificacion());
			fabricantesDTO.add(fabricanteDTO);
		}
		return fabricantesDTO;
	}

}
