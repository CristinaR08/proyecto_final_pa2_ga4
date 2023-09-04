package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.repository.modelo.Reserva;

public interface IReservaService {

	public void guardar(Reserva reserva);

	public void actualizar(Reserva reserva);

	public Reserva buscar(Integer id);

	public void borrar(Integer id);

	public Boolean verificar(LocalDate inicio, LocalDate FIN, String placa);

	public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin);

	public List<BigDecimal> calcularValorTotal(LocalDate inicio, LocalDate fin, String placa);

	public String reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta);

	public void aplicar(String reserva);

	public String getReserva(String codigo);
	
	//[
		//3 buscar Clientes VIP
			public List<Reserva> buscarClientesVip();
		//]

}
