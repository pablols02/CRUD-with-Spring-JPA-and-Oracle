package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAORol;
import com.mvc.demo.dao.IDAOUsuario;
import com.mvc.demo.domain.dto.RolDTO;
import com.mvc.demo.domain.dto.UsuarioDTO;
import com.mvc.demo.domain.entity.RolEntity;
import com.mvc.demo.domain.entity.UsuarioEntity;
import com.mvc.demo.service.IServiceUsuario;

@Service
public class UsuarioServiceImpl implements IServiceUsuario {

	@Autowired
	private IDAOUsuario usuarioDAO;
	
	@Autowired
	private IDAORol rolDAO;
	
	@Override
	@Transactional
	public void guardarUsuario(UsuarioDTO usuarioDTO) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		try {
			usuarioEntity.setNick(usuarioDTO.getNick());
			usuarioEntity.setPasswd(usuarioDTO.getPasswd());
			RolEntity rolEntity = rolDAO.findById(usuarioDTO.getRol().getIdRol());
			if(rolEntity != null) {
				usuarioEntity.setRol(rolEntity);
			}
			usuarioDAO.save(usuarioEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void actualizarUsuario(UsuarioDTO usuarioDTO) {
		UsuarioEntity usuarioEntity = usuarioDAO.findById(usuarioDTO.getIdUsuario());
		
		if(usuarioEntity != null) {
			usuarioEntity.setNick(usuarioDTO.getNick());
			usuarioEntity.setPasswd(usuarioDTO.getPasswd());
			
			RolDTO rolDTO = usuarioDTO.getRol();
			if(rolDTO != null) {
				RolEntity rolEntity = new RolEntity();
				rolEntity.setIdRol(rolDTO.getIdRol());
				rolEntity.setDescripcion(rolDTO.getDescripcion());
				
				usuarioEntity.setRol(rolEntity);
			} else {
				usuarioEntity.setRol(null);
			}
			
			try {
				usuarioDAO.merge(usuarioEntity);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	@Override
	@Transactional
	public void eliminarUsuario(Integer idUsuario) {
		UsuarioEntity usuario = usuarioDAO.findById(idUsuario);
		if(usuario != null) {
			usuarioDAO.remove(usuario);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO obtenerUsuarioPorId(Integer idUsuario) {
		UsuarioEntity usuarioEntity = usuarioDAO.findById(idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setIdUsuario(usuarioEntity.getIdUsuario());
		usuarioDTO.setNick(usuarioEntity.getNick());
		usuarioDTO.setPasswd(usuarioEntity.getPasswd());
		
		RolEntity rolEntity = usuarioEntity.getRol();
		RolDTO rolDTO = new RolDTO();
		rolDTO.setIdRol(rolEntity.getIdRol());
		rolDTO.setDescripcion(rolEntity.getDescripcion());
		usuarioDTO.setRol(rolDTO);
		
		return usuarioDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> listarTodosLosUsuarios() {
		List<UsuarioEntity> usuariosEntities = usuarioDAO.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		
		for (UsuarioEntity usuarioEntity : usuariosEntities) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(usuarioEntity.getIdUsuario());
			usuarioDTO.setNick(usuarioEntity.getNick());
			usuarioDTO.setPasswd(usuarioEntity.getPasswd());
			
			RolEntity rolEntity = usuarioEntity.getRol();
			RolDTO rolDTO = new RolDTO();
			rolDTO.setIdRol(rolEntity.getIdRol());
			rolDTO.setDescripcion(rolEntity.getDescripcion());
			usuarioDTO.setRol(rolDTO);
			
			usuariosDTO.add(usuarioDTO);
		}
		return usuariosDTO;
	}

}
