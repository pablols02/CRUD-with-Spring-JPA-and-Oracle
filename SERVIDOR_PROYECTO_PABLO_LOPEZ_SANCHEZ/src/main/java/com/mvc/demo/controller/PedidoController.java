package com.mvc.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.demo.domain.dto.ClienteDTO;
import com.mvc.demo.domain.dto.FacturaDTO;
import com.mvc.demo.domain.dto.PedidoDTO;
import com.mvc.demo.domain.dto.ProductoDTO;
import com.mvc.demo.service.IServiceCliente;
import com.mvc.demo.service.IServiceFactura;
import com.mvc.demo.service.IServicePedido;
import com.mvc.demo.service.IServiceProducto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private IServicePedido pedidoService;
	
	@Autowired
	private IServiceCliente clienteService;
	
	@Autowired
	private IServiceFactura facturaService;
	
	@Autowired
	private IServiceProducto productoService;
	
	@ModelAttribute
	public void datosGlobales(Model model) {
		model.addAttribute("titulo", "Proyecto - Pedidos");
	}
	
	@RequestMapping("/indice")
	public String verIndicePedido() {
		return "pedido/indicePedido";
	}
	
	@RequestMapping("/verCrearPedido")
	public String verCrearPedido(Model model) {
		List<ClienteDTO> clientes = clienteService.listarTodosLosClientes();
		List<FacturaDTO> facturas = facturaService.listarTodasLasFacturas();
		List<ProductoDTO> productos = productoService.listarTodosLosProductos();
		model.addAttribute("clientes", clientes);
		model.addAttribute("facturas", facturas);
		model.addAttribute("productos", productos);
		return "pedido/crearPedido";
	}
	
	@PostMapping("/crearPedido")
    public String crearPedido(HttpServletRequest request) {
		LocalDate fechaPedido = LocalDate.parse(request.getParameter("fechaPedido"));
		LocalDate fechaEntrega = LocalDate.parse(request.getParameter("fechaEntrega"));
		
		String dni = request.getParameter("cliente");
		ClienteDTO cliente = clienteService.obtenerClientePorId(dni);
		
		Integer idFactura = Integer.parseInt(request.getParameter("factura"));
	    FacturaDTO factura = facturaService.obtenerFacturaPorId(idFactura);
        
        String[] productosSeleccionados = request.getParameterValues("productos");
        List<ProductoDTO> productos = new ArrayList<>();

        // Recorre los IDs de los clientes seleccionados y obtén los datos de cada cliente
        if (productosSeleccionados != null) {
            for (String idProductoString : productosSeleccionados) {
            	Integer idProducto = Integer.parseInt(idProductoString);
            	ProductoDTO producto = productoService.obtenerProductoPorId(idProducto);
                if (producto != null) {
                    productos.add(producto);
                }
            }
        }

        // Crear el DTO del pedido con la información recopilada
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setFechaPedido(fechaPedido);
        pedidoDTO.setFechaEntrega(fechaEntrega);
        pedidoDTO.setCliente(cliente);
        pedidoDTO.setFactura(factura);
        pedidoDTO.setProductos(productos);

        // Guardar el pedido con los clientes asociados
        pedidoService.guardarPedido(pedidoDTO);
        
        return "pedido/indicePedido";
    }
	
	@RequestMapping("/listarPedidos")
    public String listarPedidos(Model model) {
		List<PedidoDTO> pedidos = pedidoService.listarTodosLosPedidos();
		model.addAttribute("pedidos", pedidos);
	    return "pedido/listarPedidos";
    }
	
	@RequestMapping("/verEditarPedido/{idPedido}")
	public String verEditarPedido(@PathVariable Integer idPedido, Model model) {
		PedidoDTO pedido = pedidoService.obtenerPedidoPorId(idPedido);
		List<ClienteDTO> clientes = clienteService.listarTodosLosClientes();
		List<FacturaDTO> facturas = facturaService.listarTodasLasFacturas();
		List<ProductoDTO> productos = productoService.listarTodosLosProductos();
		
	    List<ProductoDTO> productosAsociados = pedido.getProductos();
	    for (ProductoDTO producto : productos) {
	        for (ProductoDTO productoAsociado : productosAsociados) {
	            if (producto.getIdProducto().equals(productoAsociado.getIdProducto())) {
	                producto.setEstaEnLaLista(true);
	                break;
	            }
	        }
	    }
	    
	    model.addAttribute("pedido", pedido);
	    model.addAttribute("clientes", clientes);
		model.addAttribute("facturas", facturas);
		model.addAttribute("productos", productos);
	    return "pedido/editarPedido";
	}
	
	@PostMapping("/editarPedido")
	public String editarPedido(HttpServletRequest request) {
		Integer idPedido = Integer.parseInt(request.getParameter("idPedido"));
	    LocalDate fechaPedido = LocalDate.parse(request.getParameter("fechaPedido"));
	    LocalDate fechaEntrega = LocalDate.parse(request.getParameter("fechaEntrega"));
	    
	    String dni = request.getParameter("cliente");
	    ClienteDTO cliente = clienteService.obtenerClientePorId(dni);
	    
	    Integer idFactura = Integer.parseInt(request.getParameter("factura"));
	    FacturaDTO factura = facturaService.obtenerFacturaPorId(idFactura);

	    String[] productosSeleccionados = request.getParameterValues("productos");
	    List<ProductoDTO> productos = new ArrayList<>();

	    if (productosSeleccionados != null) {
	        for (String idProductoString : productosSeleccionados) {
	            Integer idProducto = Integer.parseInt(idProductoString);
	            ProductoDTO producto = productoService.obtenerProductoPorId(idProducto);
	            if (producto != null) {
	                productos.add(producto);
	            }
	        }
	    }

	    PedidoDTO pedidoActualizado = new PedidoDTO();
	    pedidoActualizado.setIdPedido(idPedido);
	    pedidoActualizado.setFechaPedido(fechaPedido);
	    pedidoActualizado.setFechaEntrega(fechaEntrega);
	    pedidoActualizado.setCliente(cliente);
	    pedidoActualizado.setFactura(factura);
	    pedidoActualizado.setProductos(productos);

	    pedidoService.actualizarPedido(pedidoActualizado);
		
	    return "redirect:/pedido/listarPedidos";
	}
	
	@RequestMapping("/eliminarPedido/{idPedido}")
	public String eliminarPedido(@PathVariable Integer idPedido) {
		pedidoService.eliminarPedido(idPedido);
	    return "redirect:/pedido/listarPedidos";
	}
	
}
