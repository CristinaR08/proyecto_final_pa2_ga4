package com.example.demo.repository;

import com.example.demo.repository.modelo.Factura;

public interface IFacturaRepository {
	public void insertar(Factura factura);
    public void actualizar(Factura factura); 
    public void eliminar(Integer id);
    public Factura seleccionar(Integer id);
}
