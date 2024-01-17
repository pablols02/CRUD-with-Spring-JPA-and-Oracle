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
import com.mvc.demo.domain.dto.ProductoDTO;
import com.mvc.demo.service.IServiceFabricante;
import com.mvc.demo.service.IServiceProducto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IServiceProducto productoService;
	
	@Autowired
	private IServiceFabricante fabricanteService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Productos");
	}
	
	@RequestMapping("/indice")
	public String verIndiceProducto() {
		return "producto/indiceProducto";
	}
	
	@RequestMapping("/verCrearProducto")
	public String verCrearProducto(Model model) {
		List<FabricanteDTO> fabricantes = fabricanteService.listarTodosLosFabricantes();
		model.addAttribute("fabricantes", fabricantes);
		return "producto/crearProducto";
	}
	
	@PostMapping("/crearProducto")
    public String crearProducto(HttpServletRequest request) {
		String descripcion = request.getParameter("descripcion");
		int existencias = Integer.parseInt(request.getParameter("existencias"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		String cif = request.getParameter("fabricante");
		FabricanteDTO fabricante = fabricanteService.obtenerFabricantePorId(cif);
		
		ProductoDTO productoDTO = new ProductoDTO(descripcion, existencias, precio,fabricante,null);
		
		productoService.guardarProducto(productoDTO);
        return "producto/indiceProducto";
    }
	
	@RequestMapping("/listarProductos")
    public String listarProductos(Model model) {
		 List<ProductoDTO> productos = productoService.listarTodosLosProductos();
		 model.addAttribute("productos", productos);
	     return "producto/listarProductos";
    }
	
	@RequestMapping("/verEditarProducto/{idProducto}")
	public String verEditarProducto(@PathVariable Integer idProducto, Model model) {
		ProductoDTO productoDTO = productoService.obtenerProductoPorId(idProducto);
		List<FabricanteDTO> fabricantes = fabricanteService.listarTodosLosFabricantes();
		model.addAttribute("producto", productoDTO);
		model.addAttribute("fabricantes", fabricantes);
		return "producto/editarProducto";
	}
	
	@PostMapping("/editarProducto")
	public String editarProducto(HttpServletRequest request) {
		ProductoDTO productoActualizado = new ProductoDTO();
		
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		productoActualizado.setIdProducto(idProducto);
		
		String descripcion = request.getParameter("descripcion");
		productoActualizado.setDescripcion(descripcion);
		
		int existencias = Integer.parseInt(request.getParameter("existencias"));
		productoActualizado.setExistencias(existencias);
		
		double precio = Double.parseDouble(request.getParameter("precio"));
		productoActualizado.setPrecio(precio);
		
		String cif = request.getParameter("fabricante");
		FabricanteDTO fabricante = fabricanteService.obtenerFabricantePorId(cif);
		productoActualizado.setFabricante(fabricante);
		
		productoService.actualizarProducto(productoActualizado);
		
	    return "redirect:/producto/listarProductos";
	}
	
	@RequestMapping("/eliminarProducto/{idProducto}")
	public String eliminarProducto(@PathVariable Integer idProducto) {
		productoService.eliminarProducto(idProducto);
	    return "redirect:/producto/listarProductos";
	}
}
