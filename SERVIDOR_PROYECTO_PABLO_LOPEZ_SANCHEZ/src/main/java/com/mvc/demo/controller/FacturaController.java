package com.mvc.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.demo.domain.dto.FacturaDTO;
import com.mvc.demo.service.IServiceFactura;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	private IServiceFactura facturaService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Facturas");
	}
	
	@RequestMapping("/indice")
	public String verIndiceFactura() {
		return "factura/indiceFactura";
	}
	
	@RequestMapping("/verCrearFactura")
	public String verCrearFactura() {
		return "factura/crearFactura";
	}
	
	@PostMapping("/crearFactura")
    public String crearFactura(HttpServletRequest request) {
		LocalDate fechaEmision = LocalDate.parse(request.getParameter("fechaEmision"));
		double precioTotal = Double.parseDouble(request.getParameter("precioTotal"));
		
		FacturaDTO facturaDTO = new FacturaDTO(fechaEmision,precioTotal);
		facturaService.guardarFactura(facturaDTO);
		
        return "factura/indiceFactura";
    }
	
	@RequestMapping("/listarFacturas")
    public String listarFacturas(Model model) {
		 List<FacturaDTO> facturas = facturaService.listarTodasLasFacturas();
		 model.addAttribute("facturas", facturas);
	     return "factura/listarFacturas";
    }
	
	@RequestMapping("/verEditarFactura/{idFactura}")
	public String verEditarRol(@PathVariable Integer idFactura, Model model) {
		FacturaDTO facturaDTO = facturaService.obtenerFacturaPorId(idFactura);
		model.addAttribute("factura", facturaDTO);
		return "factura/editarFactura";
	}
	
	@PostMapping("/editarFactura")
	public String editarFactura(HttpServletRequest request) {
		FacturaDTO facturaActualizada = new FacturaDTO();
		
		Integer idFactura = Integer.parseInt(request.getParameter("idFactura"));
		facturaActualizada.setIdFactura(idFactura);
		
		LocalDate fechaEmision = LocalDate.parse(request.getParameter("fechaEmision"));
		facturaActualizada.setFechaEmision(fechaEmision);
		
		double precioTotal = Double.parseDouble(request.getParameter("precioTotal"));
		facturaActualizada.setPrecioTotal(precioTotal);
		
		facturaService.actualizarFactura(facturaActualizada);
	    return "redirect:/factura/listarFacturas";
	}
	
	@RequestMapping("/eliminarFactura/{idFactura}")
	public String eliminarFactura(@PathVariable Integer idFactura) {
		facturaService.eliminarFactura(idFactura);
	    return "redirect:/factura/listarFacturas";
	}
}
