package com.mvc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.demo.domain.dto.ClienteDTO;
import com.mvc.demo.exception.MiExcepcion;
import com.mvc.demo.service.IServiceCliente;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IServiceCliente clienteService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Clientes");
	}
	
	@RequestMapping("/indice")
	public String verIndiceCliente() {
		return "cliente/indiceCliente";
	}
	
	@RequestMapping("/verCrearCliente")
	public String verCrearCliente() {
		return "cliente/crearCliente";
	}
	
	@PostMapping("/crearCliente")
    public String crearCliente(HttpServletRequest request) throws MiExcepcion {
		String forward = "";
		try {
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String direccion = request.getParameter("direccion");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			
			ClienteDTO clienteDTO = new ClienteDTO(dni,nombre,direccion,email,telefono);
			clienteService.guardarCliente(clienteDTO);
			
			forward = "cliente/indiceCliente";
		} catch (MiExcepcion e){
			forward = "error/error";
		}
		
        return forward;
    }
	
	@RequestMapping("/listarClientes")
    public String listarClientes(Model model) {
		 List<ClienteDTO> clientes = clienteService.listarTodosLosClientes();
		 model.addAttribute("clientes", clientes);
	     return "cliente/listarClientes";
    }
	
	@RequestMapping("/verEditarCliente/{dni}")
	public String verEditarCliente(@PathVariable String dni, Model model) {
		ClienteDTO clienteDTO = clienteService.obtenerClientePorId(dni);
		model.addAttribute("cliente", clienteDTO);
		return "cliente/editarCliente";
	}
	
	@PostMapping("/editarCliente")
	public String editarCliente(HttpServletRequest request) {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		
		ClienteDTO clienteDTO = new ClienteDTO(dni,nombre,direccion,email,telefono);
		clienteService.actualizarCliente(clienteDTO);
	    return "redirect:/cliente/listarClientes";
	}
	
	@RequestMapping("/eliminarCliente/{dni}")
	public String eliminarCliente(@PathVariable String dni) {
		clienteService.eliminarCliente(dni);
	    return "redirect:/cliente/listarClientes";
	}
	
}
