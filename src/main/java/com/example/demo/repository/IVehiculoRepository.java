package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.dto.VehiculoDTO;


public interface IVehiculoRepository {

	public void insertar(Vehiculo vehiculo);
	public Vehiculo buscar(Integer id);
	public void actualizar(Vehiculo vehiculo);
	public void eliminar(Integer id);
	public Vehiculo buscarPlaca(String placa);
	public Integer actualizarEstado(String placa);
	public VehiculoDTO buscarPorPlaca(String placa);
	
	//Buscar vehiculos disponibles
	public List<Vehiculo> buscar(String marca, String modelo);

	public List<Vehiculo> reporteVehiculo(LocalDate fecha);
	public List<Vehiculo> buscarTodosDisponibles();

}
