package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAOCliente;
import com.mvc.demo.domain.dto.ClienteDTO;
import com.mvc.demo.domain.entity.ClienteEntity;
import com.mvc.demo.exception.MiExcepcion;
import com.mvc.demo.service.IServiceCliente;

@Service
public class ClienteServiceImpl implements IServiceCliente {

	@Autowired
	private IDAOCliente clienteDAO;
	
	@Override
	@Transactional
	public void guardarCliente(ClienteDTO clienteDTO) throws MiExcepcion {
		ClienteEntity clienteEntity = null;
		try {
			clienteEntity = clienteDAO.findById(clienteDTO.getDni());
			
			if (null != clienteEntity || clienteDTO.getDni() == "") {
				throw new MiExcepcion(100, "Error en cliente duplicado");
			}
			clienteEntity = new ClienteEntity();
			clienteEntity.setDni(clienteDTO.getDni());
			clienteEntity.setNombre(clienteDTO.getNombre());
			clienteEntity.setDireccion(clienteDTO.getDireccion());
			clienteEntity.setEmail(clienteDTO.getEmail());
			clienteEntity.setTelefono(clienteDTO.getTelefono());
			
			clienteDAO.save(clienteEntity);
		} catch (MiExcepcion e) {
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void actualizarCliente(ClienteDTO clienteDTO) {
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setDni(clienteDTO.getDni());
		clienteEntity.setNombre(clienteDTO.getNombre());
		clienteEntity.setDireccion(clienteDTO.getDireccion());
		clienteEntity.setEmail(clienteDTO.getEmail());
		clienteEntity.setTelefono(clienteDTO.getTelefono());
		
		try {
			clienteDAO.merge(clienteEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void eliminarCliente(String dni) {
		ClienteEntity cliente = clienteDAO.findById(dni);
		if(cliente != null) {
			clienteDAO.remove(cliente);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteDTO obtenerClientePorId(String dni) {
		ClienteEntity clienteEntity = clienteDAO.findById(dni);
		ClienteDTO clienteDTO = null;
		try {
			clienteDTO = new ClienteDTO();
			clienteDTO.setDni(clienteEntity.getDni());
			clienteDTO.setNombre(clienteEntity.getNombre());
			clienteDTO.setDireccion(clienteEntity.getDireccion());
			clienteDTO.setEmail(clienteEntity.getEmail());
			clienteDTO.setTelefono(clienteEntity.getTelefono());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return clienteDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDTO> listarTodosLosClientes() {
		List<ClienteEntity> clientesEntities = clienteDAO.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for(ClienteEntity clienteEntity : clientesEntities) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setDni(clienteEntity.getDni());
			clienteDTO.setNombre(clienteEntity.getNombre());
			clienteDTO.setDireccion(clienteEntity.getDireccion());
			clienteDTO.setEmail(clienteEntity.getEmail());
			clienteDTO.setTelefono(clienteEntity.getTelefono());
			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
	}

}
