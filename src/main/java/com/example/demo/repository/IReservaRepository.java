package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.repository.modelo.Reserva;

public interface IReservaRepository {
	
	public void insertar(Reserva reserva);	
	public void actualizar(Reserva reserva);	
	public Reserva seleccionar(Integer id);	
	public void eliminar(Integer id);	
	public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin ); 
	public Reserva buscarCodigo(String codigo);
	
	//[
	//5
		public List<Reserva> buscarClientesVip();
	//]

}
