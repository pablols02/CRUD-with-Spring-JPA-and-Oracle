package com.mvc.demo.service;

import java.util.List;

import com.mvc.demo.domain.dto.UsuarioDTO;


public interface IServiceUsuario {
	public void guardarUsuario(UsuarioDTO usuarioDTO);
	public void actualizarUsuario(UsuarioDTO usuarioDTO);
	public void eliminarUsuario(Integer idUsuario);
	public UsuarioDTO obtenerUsuarioPorId(Integer idUsuario);
	public List<UsuarioDTO> listarTodosLosUsuarios();
}
