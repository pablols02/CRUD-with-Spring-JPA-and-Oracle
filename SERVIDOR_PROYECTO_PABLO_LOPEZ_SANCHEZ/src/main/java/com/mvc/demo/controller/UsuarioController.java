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
import com.mvc.demo.domain.dto.UsuarioDTO;
import com.mvc.demo.service.IServiceRol;
import com.mvc.demo.service.IServiceUsuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IServiceUsuario usuarioService;
	
	@Autowired
	private IServiceRol rolService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Usuarios");
	}
	
	@RequestMapping("/indice")
	public String verIndiceUsuario() {
		return "usuario/indiceUsuario";
	}
	
	@RequestMapping("/verCrearUsuario")
	public String verCrearUsuario(Model model) {
		List<RolDTO> roles = rolService.listarTodosLosRoles();
		model.addAttribute("roles", roles);
		return "usuario/crearUsuario";
	}
	
	@PostMapping("/crearUsuario")
    public String crearUsuario(HttpServletRequest request) {
		String nick = request.getParameter("nick");
		String passwd = request.getParameter("passwd");
		
		Integer idRol = Integer.parseInt(request.getParameter("rol"));
		RolDTO rol = rolService.obtenerRolPorId(idRol);
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(nick, passwd, rol);
		
		usuarioService.guardarUsuario(usuarioDTO);
        return "usuario/indiceUsuario";
    }
	
	@RequestMapping("/listarUsuarios")
    public String listarUsuarios(Model model) {
		 List<UsuarioDTO> usuarios = usuarioService.listarTodosLosUsuarios();
		 model.addAttribute("usuarios", usuarios);
	     return "usuario/listarUsuarios";
    }
	
	@RequestMapping("/verEditarUsuario/{idUsuario}")
	public String verEditarUsuario(@PathVariable Integer idUsuario, Model model) {
		UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(idUsuario);
		List<RolDTO> roles = rolService.listarTodosLosRoles();
		model.addAttribute("usuario", usuarioDTO);
		model.addAttribute("roles", roles);
		return "usuario/editarUsuario";
	}
	
	@PostMapping("/editarUsuario")
	public String editarUsuario(HttpServletRequest request) {
		UsuarioDTO usuarioActualizado = new UsuarioDTO();
		
		Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		usuarioActualizado.setIdUsuario(idUsuario);
		
		String nick = request.getParameter("nick");
		usuarioActualizado.setNick(nick);
		
		String passwd = request.getParameter("passwd");
		usuarioActualizado.setPasswd(passwd);
		
		Integer idRol = Integer.parseInt(request.getParameter("rol"));
		RolDTO rol = rolService.obtenerRolPorId(idRol);
		usuarioActualizado.setRol(rol);
		
		usuarioService.actualizarUsuario(usuarioActualizado);
		
	    return "redirect:/usuario/listarUsuarios";
	}
	
	@RequestMapping("/eliminarUsuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable Integer idUsuario) {
		usuarioService.eliminarUsuario(idUsuario);
	    return "redirect:/usuario/listarUsuarios";
	}
	
}
