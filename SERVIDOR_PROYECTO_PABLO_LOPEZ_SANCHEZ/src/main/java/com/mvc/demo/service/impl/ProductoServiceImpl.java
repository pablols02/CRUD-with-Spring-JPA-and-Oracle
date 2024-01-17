package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAOFabricante;
import com.mvc.demo.dao.IDAOProducto;
import com.mvc.demo.domain.dto.FabricanteDTO;
import com.mvc.demo.domain.dto.ProductoDTO;
import com.mvc.demo.domain.entity.FabricanteEntity;
import com.mvc.demo.domain.entity.ProductoEntity;
import com.mvc.demo.service.IServiceProducto;

@Service
public class ProductoServiceImpl implements IServiceProducto{

	@Autowired
	private IDAOProducto productoDAO;
	
	@Autowired
	private IDAOFabricante fabricanteDAO;
	
	@Override
	@Transactional
	public void guardarProducto(ProductoDTO productoDTO) {
		ProductoEntity productoEntity = new ProductoEntity();
		try {
			productoEntity.setDescripcion(productoDTO.getDescripcion());
			productoEntity.setExistencias(productoDTO.getExistencias());
			productoEntity.setPrecio(productoDTO.getPrecio());
			FabricanteEntity fabricanteEntity = fabricanteDAO.findById(productoDTO.getFabricante().getCif());
			if(fabricanteEntity != null) {
				productoEntity.setFabricante(fabricanteEntity);
			}
			productoDAO.save(productoEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void actualizarProducto(ProductoDTO productoDTO) {
		ProductoEntity productoEntity = productoDAO.findById(productoDTO.getIdProducto());
		
		if(productoEntity != null) {
			productoEntity.setDescripcion(productoDTO.getDescripcion());
			productoEntity.setExistencias(productoDTO.getExistencias());
			productoEntity.setPrecio(productoDTO.getPrecio());
			
			FabricanteDTO fabricanteDTO = productoDTO.getFabricante();
			if(fabricanteDTO != null) {
				FabricanteEntity fabricanteEntity = new FabricanteEntity();
				fabricanteEntity.setCif(fabricanteDTO.getCif());
				fabricanteEntity.setNombre(fabricanteDTO.getNombre());
				fabricanteEntity.setClasificacion(fabricanteDTO.getClasificacion());
				
				productoEntity.setFabricante(fabricanteEntity);
			} else {
				productoEntity.setFabricante(null);
			}
			
			try {
				productoDAO.merge(productoEntity);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	@Override
	@Transactional
	public void eliminarProducto(Integer idProducto) {
		ProductoEntity producto = productoDAO.findById(idProducto);
		if(producto != null) {
			productoDAO.remove(producto);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoDTO obtenerProductoPorId(Integer idProducto) {
		ProductoEntity productoEntity = productoDAO.findById(idProducto);
		ProductoDTO productoDTO = new ProductoDTO();
		
		productoDTO.setIdProducto(productoEntity.getIdProducto());
		productoDTO.setDescripcion(productoEntity.getDescripcion());
		productoDTO.setExistencias(productoEntity.getExistencias());
		productoDTO.setPrecio(productoEntity.getPrecio());
		
		FabricanteEntity fabricanteEntity = productoEntity.getFabricante();
		FabricanteDTO fabricanteDTO = new FabricanteDTO();
		fabricanteDTO.setCif(fabricanteEntity.getCif());
		fabricanteDTO.setNombre(fabricanteEntity.getNombre());
		fabricanteDTO.setClasificacion(fabricanteEntity.getClasificacion());
		productoDTO.setFabricante(fabricanteDTO);
		
		return productoDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoDTO> listarTodosLosProductos() {
		List<ProductoEntity> productosEntities = productoDAO.findAll();
		List<ProductoDTO> productosDTO = new ArrayList<>();
		
		for (ProductoEntity productoEntity : productosEntities) {
			ProductoDTO productoDTO = new ProductoDTO();
			productoDTO.setIdProducto(productoEntity.getIdProducto());
			productoDTO.setDescripcion(productoEntity.getDescripcion());
			productoDTO.setExistencias(productoEntity.getExistencias());
			productoDTO.setPrecio(productoEntity.getPrecio());
			
			FabricanteEntity fabricanteEntity = productoEntity.getFabricante();
			FabricanteDTO fabricanteDTO = new FabricanteDTO();
			fabricanteDTO.setCif(fabricanteEntity.getCif());
			fabricanteDTO.setNombre(fabricanteEntity.getNombre());
			fabricanteDTO.setClasificacion(fabricanteEntity.getClasificacion());
			productoDTO.setFabricante(fabricanteDTO);
			
			productosDTO.add(productoDTO);
		}
		return productosDTO;
	}

}
