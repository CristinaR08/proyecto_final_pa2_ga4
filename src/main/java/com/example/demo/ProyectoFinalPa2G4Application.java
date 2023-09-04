package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Empleado;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IEmpleadoService;

@SpringBootApplication
public class ProyectoFinalPa2G4Application implements CommandLineRunner{

	@Autowired
	private IEmpleadoService empleadoService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2G4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Empleado empl = new Empleado();
		empl.setNombre("Jessica");
		empl.setApellido("Sánchez");
		empl.setCedula("148965320");
		empl.setContrasenia("123");
		empl.setGenero("Femenino");
		empl.setFechaNacimiento(LocalDateTime.of(1964, 5, 15, 12, 35));
		this.empleadoService.ingresar(empl);
		
	}

}
