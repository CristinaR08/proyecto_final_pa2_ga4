package com.example.demo.repository.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado") //yo
public class Empleado {
	
	@Id
	@SequenceGenerator(name = "seq_empleado",sequenceName = "seq_empleado",allocationSize = 1)
	@GeneratedValue(generator = "seq_empleado",strategy = GenerationType.SEQUENCE)
	@Column(name = "empl_id")
	private Integer id;
	
	@Column(name = "empl_nombre")
	private String nombre;
	
	@Column(name = "empl_apellido")
	private String apellido;
	
	@Column(name = "empl_cedula")
	private String cedula;
	
	@Column(name = "empl_fechaNacimiento")
	private LocalDateTime fechaNacimiento;
	
	@Column(name = "empl_genero")
	private String genero;
	
	@Column(name = "empl_contrasenia")
	private String contrasenia;

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	

}
