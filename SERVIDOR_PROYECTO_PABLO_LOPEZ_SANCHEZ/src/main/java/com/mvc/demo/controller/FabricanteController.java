package com.mvc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.demo.domain.dto.FabricanteDTO;
import com.mvc.demo.exception.MiExcepcion;
import com.mvc.demo.service.IServiceFabricante;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/fabricante")
public class FabricanteController {

	@Autowired
	private IServiceFabricante fabricanteService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Fabricantes");
	}
	
	@RequestMapping("/indice")
	public String verIndiceFabricante() {
		return "fabricante/indiceFabricante";
	}
	
	@RequestMapping("/verCrearFabricante")
	public String verCrearFabricante() {
		return "fabricante/crearFabricante";
	}
	
	@PostMapping("/crearFabricante")
    public String crearFabricante(HttpServletRequest request) throws MiExcepcion {
		String forward = "";
		try {
			String cif = request.getParameter("cif");
			String nombre = request.getParameter("nombre");
			int clasificacion = Integer.parseInt(request.getParameter("clasificacion"));
			
			FabricanteDTO fabricanteDTO = new FabricanteDTO(cif,nombre,clasificacion);
			fabricanteService.guardarFabricante(fabricanteDTO);
			
	        forward = "fabricante/indiceFabricante";
		} catch (MiExcepcion e) {
			forward = "error/error";
		}
		return forward;
    }
	
	@RequestMapping("/listarFabricantes")
    public String listarFabricantes(Model model) {
		 List<FabricanteDTO> fabricantes = fabricanteService.listarTodosLosFabricantes();
		 model.addAttribute("fabricantes", fabricantes);
	     return "fabricante/listarFabricantes";
    }
	
	@RequestMapping("/verEditarFabricante/{cif}")
	public String verEditarCliente(@PathVariable String cif, Model model) {
		FabricanteDTO fabricanteDTO = fabricanteService.obtenerFabricantePorId(cif);
		model.addAttribute("fabricante", fabricanteDTO);
		return "fabricante/editarFabricante";
	}
	
	@PostMapping("/editarFabricante")
	public String editarFabricante(HttpServletRequest request) {
		String cif = request.getParameter("cif");
		String nombre = request.getParameter("nombre");
		int clasificacion = Integer.parseInt(request.getParameter("clasificacion"));
		
		FabricanteDTO fabricanteDTO = new FabricanteDTO(cif,nombre,clasificacion);
		fabricanteService.actualizarFabricante(fabricanteDTO);
	    return "redirect:/fabricante/listarFabricantes";
	}
	
	@RequestMapping("/eliminarFabricante/{cif}")
	public String eliminarFabricante(@PathVariable String cif) {
		fabricanteService.eliminarFabricante(cif);
	    return "redirect:/fabricante/listarFabricantes";
	}
}
