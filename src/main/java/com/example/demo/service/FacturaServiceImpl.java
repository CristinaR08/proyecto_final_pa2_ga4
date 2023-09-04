package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IFacturaRepository;
import com.example.demo.repository.modelo.Factura;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
@Service
public class FacturaServiceImpl implements IFacturaService{
	@Autowired
	private IFacturaRepository facturaRepository;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardar(Factura factura) {
		this.facturaRepository.insertar(factura);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Factura factura) {
		this.facturaRepository.actualizar(factura);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrar(Integer id) {
		this.facturaRepository.eliminar(id);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Factura buscar(Integer id) {
		
		return this.facturaRepository.seleccionar(id);
	}

}
