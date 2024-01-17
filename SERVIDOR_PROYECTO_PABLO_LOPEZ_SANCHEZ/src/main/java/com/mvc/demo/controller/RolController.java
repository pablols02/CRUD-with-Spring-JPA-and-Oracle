package com.mvc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.demo.domain.dto.RolDTO;
import com.mvc.demo.service.IServiceRol;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rol")
public class RolController {

	@Autowired
	private IServiceRol rolService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Roles");
	}
	
	@RequestMapping("/indice")
	public String verIndiceRol() {
		return "rol/indiceRol";
	}
	
	@RequestMapping("/verCrearRol")
	public String verCrearRol() {
		return "rol/crearRol";
	}
	
	@PostMapping("/crearRol")
    public String crearRol(HttpServletRequest request) {
		String descripcion = request.getParameter("descripcion");
		
		RolDTO rolDTO = new RolDTO(descripcion);
		rolService.guardarRol(rolDTO);
		
        return "rol/indiceRol";
    }
	
	@RequestMapping("/listarRoles")
    public String listarRoles(Model model) {
		 List<RolDTO> roles = rolService.listarTodosLosRoles();
		 model.addAttribute("roles", roles);
	     return "rol/listarRoles";
    }
	
	@RequestMapping("/verEditarRol/{idRol}")
	public String verEditarRol(@PathVariable Integer idRol, Model model) {
		RolDTO rolDTO = rolService.obtenerRolPorId(idRol);
		model.addAttribute("rol", rolDTO);
		return "rol/editarRol";
	}
	
	@PostMapping("/editarRol")
	public String editarRol(HttpServletRequest request) {
		RolDTO rolActualizado = new RolDTO();
		
		Integer idRol = Integer.parseInt(request.getParameter("idRol"));
		rolActualizado.setIdRol(idRol);
		
		String descripcion = request.getParameter("descripcion");
		rolActualizado.setDescripcion(descripcion);
		
		rolService.actualizarRol(rolActualizado);
	    return "redirect:/rol/listarRoles";
	}
	
	@RequestMapping("/eliminarRol/{idRol}")
	public String eliminarRol(@PathVariable Integer idRol) {
		rolService.eliminarRol(idRol);
	    return "redirect:/rol/listarRoles";
	}
	
}
