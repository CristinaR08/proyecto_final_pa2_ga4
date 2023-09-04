package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardar(Vehiculo vehiculo) {
		this.vehiculoRepository.insertar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo encontrar(Integer id) {
		return this.vehiculoRepository.buscar(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Vehiculo vehiculo) {
		this.vehiculoRepository.actualizar(vehiculo);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrar(Integer id) {
		this.vehiculoRepository.eliminar(id);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPlaca(String placa) {
		return this.vehiculoRepository.buscarPlaca(placa);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> vehiculosVIP(LocalDate fecha) {
		List<Vehiculo> vehiculos = this.vehiculoRepository.reporteVehiculo(fecha);
		List<Vehiculo> vehiculosVIP = vehiculos.stream()
				.sorted(Comparator.comparingDouble(
						a -> a.getReservas().stream().map(b -> b.getTotal().doubleValue()).reduce(0.0, Double::sum)))
				.collect(Collectors.toList());
		vehiculosVIP.parallelStream().forEach(a -> {
			a.setValorDia(new BigDecimal(this.calcularSubtotal(a)));
			a.setPaisFabricacion(this.calcularTotal(a).toString());
		});
		Collections.reverse(vehiculosVIP);
		return vehiculosVIP;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarTodosDisponibles() {
		return this.vehiculoRepository.buscarTodosDisponibles();
	}

	@Override
	public Double calcularSubtotal(Vehiculo vehiculo) {
		List<Reserva> lista = vehiculo.getReservas();
		Double subTotal = lista.parallelStream().map(b -> b.getSubtotal().doubleValue()).reduce(0.0, Double::sum);
		return subTotal * 0.15;
	}

	@Override
	public Double calcularTotal(Vehiculo vehiculo) {
		List<Reserva> lista = vehiculo.getReservas();
		Double total = lista.parallelStream().map(b -> b.getTotal().doubleValue()).reduce(0.0, Double::sum);
		return total;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrarDisponible(Integer id, Boolean D) {
		if (D) {
			borrar(id);
		} else {
			System.out.println("Vehículo no disponible, no es posible eliminar.");
		}
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo) {
		return this.vehiculoRepository.buscar(marca, modelo);
	}

	@Override
	public List<Vehiculo> encontrarTodos() {
		return null;
	}

}
