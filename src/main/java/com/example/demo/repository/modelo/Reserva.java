package com.example.demo.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva") // dennisse
public class Reserva {

	@Id
	@SequenceGenerator(name = "seq_reserva", sequenceName = "seq_reserva", allocationSize = 1)
	@GeneratedValue(generator = "seq_reserva", strategy = GenerationType.SEQUENCE)
	@Column(name = "rese_id")
	private Integer id;

	@Column(name = "rese_codigo")
	private String codigo;

	@Column(name = "rese_fecha_inicio")
	private LocalDate fechaInicio;

	@Column(name = "rese_fecha_fin")
	private LocalDate fechaFin;

	@Column(name = "rese_subtotal")
	private BigDecimal subtotal;

	@Column(name = "rese_total")
	private BigDecimal total;

	@Column(name = "rese_ice")
	private BigDecimal ice;

	@Column(name = "rese_num_tarjeta")
	private String numTarjeta;

	@Column(name = "rese_estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "rese_id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "rese_id_vehiculo")
	private Vehiculo vehiculo;

	@OneToOne
	@JoinColumn(name = "rese_id_factura")
	private Factura factura;

//getters setters toString
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", subtotal="
				+ subtotal + ", total=" + total + ", ice=" + ice + ", numTarjeta=" + numTarjeta + ", estado=" + estado
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getIce() {
		return ice;
	}

	public void setIce(BigDecimal ice) {
		this.ice = ice;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
