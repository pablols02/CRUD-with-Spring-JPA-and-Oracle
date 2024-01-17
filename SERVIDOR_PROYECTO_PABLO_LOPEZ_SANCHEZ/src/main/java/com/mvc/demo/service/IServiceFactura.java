package com.mvc.demo.service;

import java.util.List;

import com.mvc.demo.domain.dto.FacturaDTO;

public interface IServiceFactura {
	public void guardarFactura(FacturaDTO facturaDTO);
	public void actualizarFactura(FacturaDTO facturaDTO);
	public void eliminarFactura(Integer idFactura);
	public FacturaDTO obtenerFacturaPorId(Integer idFactura);
	public List<FacturaDTO> listarTodasLasFacturas();
}
