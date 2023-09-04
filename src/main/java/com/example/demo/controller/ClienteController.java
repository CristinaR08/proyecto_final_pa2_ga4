package com.example.demo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final Logger LOG = Logger.getLogger(ClienteController.class);
	
	@Autowired
	private IVehiculoService iVehiculoService;
	@Autowired
	private IReservaService iReservaService;
	@Autowired
	private IClienteService iClienteService;

	// http://localhost:8080/rentaCar/clientes/inicio
	@GetMapping("/inicio")
	public String mostrarClientes() {
		LOG.info("Entrando a la página de inicio");
		return "vistaPrincipalClientes";
	}

	@GetMapping("/regresar")
	public String regresar() {
		return "redirect:/clientes/inicio";
	}

	// Funcionalidad 1a
	// http://localhost:8080/rentaCar/clientes/vehiculos_marca_modelo
	@GetMapping("/vehiculos_marca_modelo")
	public String vehiculosMM(Vehiculo vehiculo, Model modelo) {
		List<Vehiculo> lista = this.iVehiculoService.buscarVehiculosDisponibles(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("lista", lista);
		return "vistaVehiculosMarcaModelo";
	}

	// http://localhost:8080/rentaCar/clientes/buscar_marca_modelo
	@GetMapping("/buscar_marca_modelo")
	public String buscarMM(Vehiculo vehiculo) {
		return "vistaVehiculoBuscar";
	}

	// http://localhost:8080/rentaCar/clientes/todos
	@GetMapping("/todos")
	public String vehiculosAll(Model modelo) {
		List<Vehiculo> lista = this.iVehiculoService.buscarTodosDisponibles();
		modelo.addAttribute("lista", lista);
		return "vistaVehiculosTodos";
	}

	// Funcionalidad 1b

	// http://localhost:8080/rentaCar/clientes/reservar
	@GetMapping("/reservar")
	public String reservar(ReservaDTO reservaDTO) {
		return "vistaReserva";
	}

	@GetMapping("/confirmacion_reserva")
	public String confirmacionReserva(ReservaDTO reservaDTO, Model model, HttpSession session) {
		if (this.iReservaService.verificar(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(),
				reservaDTO.getPlaca())) {
			LOG.error("Error al reservar");
			return "vistaError";
		} else {
			var total = this.iReservaService
					.calcularValorTotal(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(), reservaDTO.getPlaca())
					.get(2);
			model.addAttribute("total", total);
			session.setAttribute("reserva", reservaDTO);
			return "vistaConfirmacionReserva";
		}

	}

	@PostMapping("/reservado")
	public String reservado(HttpSession session, ReservaDTO reserva, Model model) {
		ReservaDTO reservaDTO = (ReservaDTO) session.getAttribute("reserva");
		String codigo = this.iReservaService.reservar(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(),
				reservaDTO.getPlaca(), reservaDTO.getCedula(), reserva.getNumeroTarjeta());
		model.addAttribute("codigo", codigo);
		LOG.info("Reservando");
		return "vistaReservado";
	}

	// Funcionalidad 1c
	@GetMapping("/lista_clientes")
	public String buscarEstudiantes(Model modelo) {
		List<Cliente> listaClientes = this.iClienteService.verClientes();
		modelo.addAttribute("clientes", listaClientes);
		return "vistaListaClientes";
	}

	@PostMapping("/guardar")
	public String registrar(Cliente cliente) {
		this.iClienteService.crear(cliente);
		LOG.info("Reserva realizada con éxito");
		return "vistaExitoso";
	}

	@GetMapping("/nuevo")
	public String nuevoEstudiante(Cliente cliente) {
		return "vistaNuevoCliente";
	}

}
