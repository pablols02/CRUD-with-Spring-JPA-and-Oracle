package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAOCliente;
import com.mvc.demo.dao.IDAOFactura;
import com.mvc.demo.dao.IDAOPedido;
import com.mvc.demo.dao.IDAOProducto;
import com.mvc.demo.domain.dto.ClienteDTO;
import com.mvc.demo.domain.dto.FacturaDTO;
import com.mvc.demo.domain.dto.PedidoDTO;
import com.mvc.demo.domain.dto.ProductoDTO;
import com.mvc.demo.domain.entity.ClienteEntity;
import com.mvc.demo.domain.entity.FacturaEntity;
import com.mvc.demo.domain.entity.PedidoEntity;
import com.mvc.demo.domain.entity.ProductoEntity;
import com.mvc.demo.service.IServicePedido;

@Service
public class PedidoServiceImpl implements IServicePedido {

	@Autowired
	private IDAOPedido pedidoDAO;
	
	@Autowired
	private IDAOCliente clienteDAO;
	
	@Autowired
	private IDAOFactura facturaDAO;
	
	@Autowired
	private IDAOProducto productoDAO;
	
	@Override
    @Transactional
    public void guardarPedido(PedidoDTO pedidoDTO) {
        try {
            PedidoEntity pedidoEntity = new PedidoEntity();
            pedidoEntity.setFechaPedido(pedidoDTO.getFechaPedido());
            pedidoEntity.setFechaEntrega(pedidoDTO.getFechaEntrega());
            
            ClienteEntity clienteEntity = clienteDAO.findById(pedidoDTO.getCliente().getDni());
            pedidoEntity.setCliente(clienteEntity);
            
            FacturaEntity facturaEntity = facturaDAO.findById(pedidoDTO.getFactura().getIdFactura());
            pedidoEntity.setFactura(facturaEntity);
            
            List<ProductoEntity> productosAsociados = new ArrayList<>();
            if (pedidoDTO.getProductos() != null) {
                for (ProductoDTO productoDTO : pedidoDTO.getProductos()) {
                    ProductoEntity productoEntity = productoDAO.findById(productoDTO.getIdProducto());
                    if (productoEntity != null) {
                    	productosAsociados.add(productoEntity);
                    }
                }
            }
            pedidoEntity.setLstProductos(productosAsociados);

            pedidoDAO.save(pedidoEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	@Override
	@Transactional
	public void actualizarPedido(PedidoDTO pedidoDTO) {
		try {
	        PedidoEntity pedidoEntity = pedidoDAO.findById(pedidoDTO.getIdPedido());
	        	pedidoEntity.setIdPedido(pedidoDTO.getIdPedido());
	            pedidoEntity.setFechaPedido(pedidoDTO.getFechaPedido());
	            pedidoEntity.setFechaEntrega(pedidoDTO.getFechaEntrega());
	            
	            ClienteEntity clienteEntity = clienteDAO.findById(pedidoDTO.getCliente().getDni());
	            pedidoEntity.setCliente(clienteEntity);
	            
	            FacturaEntity facturaEntity = facturaDAO.findById(pedidoDTO.getFactura().getIdFactura());
	            pedidoEntity.setFactura(facturaEntity);
	            
	            List<ProductoEntity> productosAsociados = new ArrayList<>();
	            if (pedidoDTO.getProductos() != null) {
	                for (ProductoDTO productoDTO : pedidoDTO.getProductos()) {
	                    ProductoEntity productoEntity = productoDAO.findById(productoDTO.getIdProducto());
	                    if (productoEntity != null) {
	                        productosAsociados.add(productoEntity);
	                    }
	                }
	            }
	            pedidoEntity.setLstProductos(productosAsociados);

	            pedidoDAO.merge(pedidoEntity);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	@Transactional
	public void eliminarPedido(Integer idPedido) {
		System.out.println(idPedido);
	    try {
	        PedidoEntity pedido = pedidoDAO.findById(idPedido);
	        if (pedido != null) {
	            pedidoDAO.remove(pedido);
	        }
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public PedidoDTO obtenerPedidoPorId(Integer idPedido) {
	    PedidoDTO pedidoDTO = null;
	    try {
	        PedidoEntity pedidoEntity = pedidoDAO.findById(idPedido);
	            pedidoDTO = new PedidoDTO();
	            pedidoDTO.setIdPedido(pedidoEntity.getIdPedido());
	            pedidoDTO.setFechaPedido(pedidoEntity.getFechaPedido());
	            pedidoDTO.setFechaEntrega(pedidoEntity.getFechaEntrega());

	            // Obtener el ClienteDTO correspondiente al ClienteEntity asociado
	            ClienteEntity clienteEntity = pedidoEntity.getCliente();
	            ClienteDTO clienteDTO = new ClienteDTO();
	            clienteDTO.setDni(clienteEntity.getDni());
	            clienteDTO.setNombre(clienteEntity.getNombre());
	            clienteDTO.setDireccion(clienteEntity.getDireccion());
	            pedidoDTO.setCliente(clienteDTO);

	            // Obtener el FacturaDTO correspondiente al FacturaEntity asociado
	            FacturaEntity facturaEntity = pedidoEntity.getFactura();
	            FacturaDTO facturaDTO = new FacturaDTO();
	            facturaDTO.setIdFactura(facturaEntity.getIdFactura());
	            facturaDTO.setFechaEmision(facturaEntity.getFechaEmision());
	            facturaDTO.setPrecioTotal(facturaEntity.getPrecioTotal());
	            pedidoDTO.setFactura(facturaDTO);

	            // Obtener la lista de ProductoDTO asociados al PedidoEntity
	            List<ProductoDTO> productosDTO = new ArrayList<>();
	            List<ProductoEntity> productosEntity = pedidoEntity.getLstProductos();
	            for (ProductoEntity productoEntity : productosEntity) {
	                ProductoDTO productoDTO = new ProductoDTO();
	                productoDTO.setIdProducto(productoEntity.getIdProducto());
	                productoDTO.setDescripcion(productoEntity.getDescripcion());
	                productoDTO.setExistencias(productoEntity.getExistencias());
	                productoDTO.setPrecio(productoEntity.getPrecio());
	                productosDTO.add(productoDTO);
	            }
	            pedidoDTO.setProductos(productosDTO);
	            
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return pedidoDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PedidoDTO> listarTodosLosPedidos() {
	    List<PedidoDTO> pedidosDTO = new ArrayList<>();
	    try {
	        List<PedidoEntity> pedidosEntity = pedidoDAO.findAll(); // Obtener todos los pedidos de la base de datos

	        for (PedidoEntity pedidoEntity : pedidosEntity) {
	            PedidoDTO pedidoDTO = new PedidoDTO();
	            pedidoDTO.setIdPedido(pedidoEntity.getIdPedido());
	            pedidoDTO.setFechaPedido(pedidoEntity.getFechaPedido());
	            pedidoDTO.setFechaEntrega(pedidoEntity.getFechaEntrega());

	            // Obtener y asignar el ClienteDTO correspondiente al ClienteEntity asociado
	            ClienteEntity clienteEntity = pedidoEntity.getCliente();
	            ClienteDTO clienteDTO = new ClienteDTO();
	            clienteDTO.setDni(clienteEntity.getDni());
	            clienteDTO.setNombre(clienteEntity.getNombre());
	            clienteDTO.setDireccion(clienteEntity.getDireccion());
	            pedidoDTO.setCliente(clienteDTO);

	            // Obtener y asignar el FacturaDTO correspondiente al FacturaEntity asociado
	            FacturaEntity facturaEntity = pedidoEntity.getFactura();
	            FacturaDTO facturaDTO = new FacturaDTO();
	            facturaDTO.setIdFactura(facturaEntity.getIdFactura());
	            facturaDTO.setFechaEmision(facturaEntity.getFechaEmision());
	            facturaDTO.setPrecioTotal(facturaEntity.getPrecioTotal());
	            pedidoDTO.setFactura(facturaDTO);

	            // Obtener y asignar la lista de ProductoDTO asociados al PedidoEntity
	            List<ProductoDTO> productosDTO = new ArrayList<>();
	            List<ProductoEntity> productosEntity = pedidoEntity.getLstProductos();
	            for (ProductoEntity productoEntity : productosEntity) {
	                ProductoDTO productoDTO = new ProductoDTO();
	                productoDTO.setIdProducto(productoEntity.getIdProducto());
	                productoDTO.setDescripcion(productoEntity.getDescripcion());
	                productoDTO.setExistencias(productoEntity.getExistencias());
	                productoDTO.setPrecio(productoEntity.getPrecio());
	                productosDTO.add(productoDTO);
	            }
	            pedidoDTO.setProductos(productosDTO);

	            pedidosDTO.add(pedidoDTO);
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return pedidosDTO;
	}
	
}
