package com.example.demo.repository.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente") //jonathan
public class Cliente {
	
	@Id
	@SequenceGenerator(name = "seq_cliente",sequenceName = "seq_cliente",allocationSize = 1)
	@GeneratedValue(generator = "seq_cliente",strategy = GenerationType.SEQUENCE)
	@Column(name = "clte_id")
	private Integer id;
	
	@Column(name = "clte_nombre")
	private String nombre;
	
	@Column(name = "clte_apellido")
	private String apellido;
	
	@Column(name = "clte_cedula")
	private String cedula;
	
	@Column(name = "clte_fechaNacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "clte_genero")
	private String genero;
	
	@Column(name = "clte_registro")
	private String registro;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Reserva> reservas;

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", registro=" + registro + "]";
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
