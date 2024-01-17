package com.mvc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.demo.dao.IDAOFactura;
import com.mvc.demo.domain.dto.FacturaDTO;
import com.mvc.demo.domain.entity.FacturaEntity;
import com.mvc.demo.service.IServiceFactura;

@Service
public class FacturaServiceImpl implements IServiceFactura {

	@Autowired
	private IDAOFactura facturaDAO;
	
	@Override
	@Transactional
	public void guardarFactura(FacturaDTO facturaDTO) {
		FacturaEntity facturaEntity = new FacturaEntity();
	    try {
	    	facturaEntity.setFechaEmision(facturaDTO.getFechaEmision());
	    	facturaEntity.setPrecioTotal(facturaDTO.getPrecioTotal());
	        facturaDAO.save(facturaEntity);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	@Transactional
	public void actualizarFactura(FacturaDTO facturaDTO) {
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setIdFactura(facturaDTO.getIdFactura());
		facturaEntity.setFechaEmision(facturaDTO.getFechaEmision());
		facturaEntity.setPrecioTotal(facturaDTO.getPrecioTotal());
		
	    try {
	    	facturaDAO.merge(facturaEntity);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	@Transactional
	public void eliminarFactura(Integer idFactura) {
		FacturaEntity factura = facturaDAO.findById(idFactura);
	    if (factura != null) {
	    	facturaDAO.remove(factura);
	    }
		
	}

	@Override
	@Transactional(readOnly = true)
	public FacturaDTO obtenerFacturaPorId(Integer idFactura) {
		FacturaEntity facturaEntity = facturaDAO.findById(idFactura);
		FacturaDTO facturaDTO = null;
	    	try {
	    		facturaDTO = new FacturaDTO();
	    		facturaDTO.setIdFactura(facturaEntity.getIdFactura());
	    		facturaDTO.setFechaEmision(facturaEntity.getFechaEmision());
	    		facturaDTO.setPrecioTotal(facturaEntity.getPrecioTotal());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

	    return facturaDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FacturaDTO> listarTodasLasFacturas() {
		List<FacturaEntity> facturasEntities = facturaDAO.findAll();
	    List<FacturaDTO> facturasDTO = new ArrayList<>();
	    for (FacturaEntity facturaEntity : facturasEntities) {
	    	FacturaDTO facturaDTO = new FacturaDTO();
	    	facturaDTO.setIdFactura(facturaEntity.getIdFactura());
	    	facturaDTO.setFechaEmision(facturaEntity.getFechaEmision());
	    	facturaDTO.setPrecioTotal(facturaEntity.getPrecioTotal());
	    	facturasDTO.add(facturaDTO);
	    }
	    return facturasDTO;
	}

}
