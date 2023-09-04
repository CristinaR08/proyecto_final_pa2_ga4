package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
	
	private static final Logger LOG = Logger.getLogger(ReporteController.class);

	@Autowired
	private IVehiculoService iVehiculoService;
	
	@Autowired
	private IReservaService iReservaService;
	
	// reporte Vehiculos VIP
	@GetMapping("/vehiculosVIP")
	public String reporteVehiculosVIP() {
		return "vReporteVehiculosVIP";
	}

	@PostMapping("/listaVehiculosVIP")
	public String listaReporteVehiculosVIP(LocalDate fecha, Model modelo) {
		
		System.out.println("fecha:" + fecha);
		List<Vehiculo> lista = this.iVehiculoService.vehiculosVIP(fecha);
		lista.forEach(System.out::println);
		modelo.addAttribute("lista", lista);
		LOG.info("Generando reporte con éxito");
		return "vistaListaVehiculosVIP";
	}
	
	//reporte Reservas
	@GetMapping("/reservas")
	public String reporteReservas(Reserva Reserva) {
		return "vReporteReserva";
	}

	@PostMapping("/listaReservas")
	public String listaReporteReservas(LocalDate fechaInicio, LocalDate fechaFinal, Model model) {
		System.out.println("fechas: " + fechaFinal + " " + fechaInicio);
		List<Reserva> lista = this.iReservaService.reporteReserva(fechaInicio, fechaFinal);
		model.addAttribute("reservas", lista);
		LOG.info("Generando reporte con éxito");
		return "vListaReporteReserva";
	}
	
	//[
	
	//Reporte Cliente VIP2
		@PostMapping("/buscarClientesVip")
		public String buscarReservas(Model model) {
			List<Reserva> reservas = this.iReservaService.buscarClientesVip();
			model.addAttribute("reservas",reservas);
			LOG.info("Generando reporte con éxito");
			return "vistaReporteClientesVip";

		}
	
		//7
		// reporte Vehiculos VIP
		@GetMapping("/clientesVIP")
		public String reporteClientesVIP() {
			return "vReporteClientesVIP";
		}
	
	
	//]
	
}
