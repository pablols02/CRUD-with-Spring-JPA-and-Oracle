package com.mvc.demo.service;

import java.util.List;

import com.mvc.demo.domain.dto.ClienteDTO;
import com.mvc.demo.exception.MiExcepcion;

public interface IServiceCliente {
	public void guardarCliente(ClienteDTO clienteDTO) throws MiExcepcion;
	public void actualizarCliente(ClienteDTO clienteDTO);
	public void eliminarCliente(String dni);
	public ClienteDTO obtenerClientePorId(String dni);
	public List<ClienteDTO> listarTodosLosClientes();
}
