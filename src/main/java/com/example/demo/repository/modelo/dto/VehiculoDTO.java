package com.example.demo.repository.modelo.dto;

import java.math.BigDecimal;

public class VehiculoDTO {

	private String placa;
	private String modelo;
	private String marca;
	private String anio;
	private String estado;
	private BigDecimal valor;

	public VehiculoDTO() {
	}

	public VehiculoDTO(String placa, String modelo, String marca, String anio, String estado, BigDecimal valor) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anio = anio;
		this.estado = estado;
		this.valor = valor;

	}

	// To String
	@Override
	public String toString() {
		return "VehiculoDTO [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", anio=" + anio
				+ ", estado=" + estado + ", valor=" + valor + "]";
	}

	// SET Y GET

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}