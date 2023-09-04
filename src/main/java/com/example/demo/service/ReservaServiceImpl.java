package com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.IFacturaRepository;
import com.example.demo.repository.IReservaRepository;
import com.example.demo.repository.IVehiculoRepository;
import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Factura;
import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository iReservaRepository;

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Autowired
	private IClienteRepository iClienteRepository;

	@Autowired
	private IFacturaRepository iFacturaRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void guardar(Reserva reserva) {
		this.iReservaRepository.insertar(reserva);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Reserva reserva) {
		this.iReservaRepository.actualizar(reserva);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.iReservaRepository.seleccionar(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void borrar(Integer id) {
		this.iReservaRepository.eliminar(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Boolean verificar(LocalDate inicio, LocalDate fin, String placa) {
		Vehiculo v = this.iVehiculoRepository.buscarPlaca(placa);

		List<Reserva> reservas = this.iReservaRepository.reporteReserva(inicio, fin);
		boolean estado = reservas.parallelStream().anyMatch(x -> x.getVehiculo().getId() == v.getId());
		return estado;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> reporteReserva(LocalDate inicio, LocalDate fin) {
		return this.iReservaRepository.reporteReserva(inicio, fin);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<BigDecimal> calcularValorTotal(LocalDate inicio, LocalDate fin, String placa) {
		long dias = ChronoUnit.DAYS.between(inicio, fin);
		Vehiculo vehiculo = this.iVehiculoRepository.buscarPlaca(placa);
		BigDecimal subtotal = vehiculo.getValorDia().multiply(new BigDecimal(dias)).setScale(2, RoundingMode.UP);
		BigDecimal ice = subtotal.multiply(new BigDecimal(0.15)).setScale(2, RoundingMode.UP);
		BigDecimal total = subtotal.add(ice).setScale(2, RoundingMode.UP);
		List<BigDecimal> calculos = new ArrayList<>();
		calculos.add(subtotal);
		calculos.add(ice);
		calculos.add(total);
		return calculos;

	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String reservar(LocalDate inicio, LocalDate fin, String placa, String cedula, String tarjeta) {
		Vehiculo vehiculo = this.iVehiculoRepository.buscarPlaca(placa);
		Cliente cliente = this.iClienteRepository.buscarPorCedula(cedula);

		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		Random random = new Random();
		reserva.setCodigo("R" + random.nextInt(100));
		reserva.setEstado("G");
		var calculos = this.calcularValorTotal(inicio, fin, placa);
		reserva.setFechaInicio(inicio);
		reserva.setFechaFin(fin);
		reserva.setIce(calculos.get(1));
		reserva.setNumTarjeta(tarjeta);
		reserva.setSubtotal(calculos.get(0));
		reserva.setTotal(calculos.get(2));
		reserva.setVehiculo(vehiculo);
		Factura factura = new Factura();
		factura.setIva(calculos.get(1));
		factura.setNumTarjeta(tarjeta);
		factura.setSubtotal(calculos.get(0));
		factura.setTotal(calculos.get(2));
		factura.setReserva(reserva);
		reserva.setFactura(factura);
		this.iFacturaRepository.insertar(factura);
		this.guardar(reserva);
		return reserva.getCodigo();
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void aplicar(String codigo) {
		// TODO Auto-generated method stub
		Reserva reserva = this.iReservaRepository.buscarCodigo(codigo);
		reserva.setEstado("E");

		Vehiculo vehiculo = reserva.getVehiculo();
		vehiculo.setEstado("Indisponible");

		this.iVehiculoRepository.actualizar(vehiculo);
		this.iReservaRepository.actualizar(reserva);

	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public String getReserva(String codigo) {
		// TODO Auto-generated method stub
		Reserva reserva = this.iReservaRepository.buscarCodigo(codigo);

		return String.format("Placa: %s - Modelo: %s – Estado: %s – Fecha: %s-%s – Reservado por: %s",
				reserva.getVehiculo().getPlaca(), reserva.getVehiculo().getModelo(),
				reserva.getVehiculo().getEstado().equals("D") ? "Disponible" : "Indisponible", reserva.getFechaInicio(),
				reserva.getFechaFin(), reserva.getCliente().getCedula());
	}
	
	//[
	//4
		@Override
		@Transactional(value = TxType.NOT_SUPPORTED)
		public List<Reserva> buscarClientesVip() {
			// TODO Auto-generated method stub
			return this.iReservaRepository.buscarClientesVip();
		}
	//]

}
