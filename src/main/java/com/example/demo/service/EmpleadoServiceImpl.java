package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEmpleadoRepository;
import com.example.demo.repository.modelo.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoRepository empleadoRepository;


	// CRUD
	@Override
	public void ingresar(Empleado empleado) {
		this.empleadoRepository.ingresar(empleado);
	}

	@Override
	public Empleado buscarPorId(Integer id) {
		return this.buscarPorId(id);
	}

	public String siguienteVista(String user, String pass) {
		/*if (this.autenticar(user, pass)) {
			return "vInicioE";
		} else if (this.clienteService.autenticar(user, pass)) {
			return "vInicioC";
		} else {*/
			return "redirect:/inicio";
		}
	

}
