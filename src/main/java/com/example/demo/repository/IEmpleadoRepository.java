package com.example.demo.repository;

import com.example.demo.repository.modelo.Empleado;

public interface IEmpleadoRepository {

	// CRUD

	public void ingresar(Empleado empleado);

	public Empleado buscar(Integer id);

	// BUSCAR POR USERNAME
	public Empleado buscarUser(String user);

}
