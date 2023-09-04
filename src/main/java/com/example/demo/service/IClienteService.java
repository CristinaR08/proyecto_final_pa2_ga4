package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Cliente;

public interface IClienteService {
	public void crear(Cliente cliente);	
	public void actualizar(Cliente cliente);
	public void borrar(Integer id);
	public Cliente buscarPorCedula(String cedula);
	public Cliente buscarPorId(Integer id);
	public List<Cliente> verClientes();

}
