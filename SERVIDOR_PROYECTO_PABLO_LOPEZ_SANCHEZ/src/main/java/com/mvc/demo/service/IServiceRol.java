package com.mvc.demo.service;

import java.util.List;

import com.mvc.demo.domain.dto.RolDTO;

public interface IServiceRol {
	public void guardarRol(RolDTO rolDTO);
	public void actualizarRol(RolDTO rolDTO);
	public void eliminarRol(Integer idRol);
	public RolDTO obtenerRolPorId(Integer idRol);
	public List<RolDTO> listarTodosLosRoles();
}
