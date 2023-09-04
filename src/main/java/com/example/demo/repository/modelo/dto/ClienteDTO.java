package com.example.demo.repository.modelo.dto;

import java.math.BigDecimal;

public class ClienteDTO {

	private String cedula;
	private String nombre;
	private String apellido;
	private BigDecimal iva;
	private BigDecimal total;

	public ClienteDTO() {

	}

	public ClienteDTO(String cedula, String nombre, String apellido, BigDecimal iva, BigDecimal total) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.iva = iva;
		this.total = total;
	}

	// Set y Get
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	// To String

	@Override
	public String toString() {
		return "ClienteDTO [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", iva=" + iva
				+ ", total=" + total + "]";
	}

}