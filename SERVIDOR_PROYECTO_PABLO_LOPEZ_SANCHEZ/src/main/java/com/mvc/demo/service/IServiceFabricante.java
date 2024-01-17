package com.mvc.demo.service;

import java.util.List;

import com.mvc.demo.domain.dto.FabricanteDTO;
import com.mvc.demo.exception.MiExcepcion;

public interface IServiceFabricante {
	public void guardarFabricante(FabricanteDTO fabricanteDTO) throws MiExcepcion;
	public void actualizarFabricante(FabricanteDTO fabricanteDTO);
	public void eliminarFabricante(String cif);
	public FabricanteDTO obtenerFabricantePorId(String cif);
	public List<FabricanteDTO> listarTodosLosFabricantes();
}
