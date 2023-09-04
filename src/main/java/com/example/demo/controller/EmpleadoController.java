package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Cliente;
import com.example.demo.repository.modelo.Empleado;
import com.example.demo.repository.modelo.Reserva;
import com.example.demo.repository.modelo.Vehiculo;
import com.example.demo.repository.modelo.dto.ReservaDTO;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IEmpleadoService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IReservaService iReservaService;
	
	private static final Logger LOG = Logger.getLogger(EmpleadoController.class);
	
	@Autowired
	private IEmpleadoService iEmpleadoService;

	// http://localhost:8080/rentaCar/empleados/inicio
	@GetMapping("/inicio")
	public String inicioEmpleados() {
		LOG.info("Dirigiendo a vista Principal de empleado");
		return "vistaPrincipalEmpleados";
	}

	@GetMapping("/regresar")
	public String regresar() {
		return "redirect:/empleados/inicio";
	}
	
	// Funcionalidad 2a
	@GetMapping("/lista_clientes")
	public String buscarEstudiantes(Model modelo) {
		List<Cliente> listaClientes = this.iClienteService.verClientes();
		modelo.addAttribute("clientes", listaClientes);
		LOG.info("Buscando cliente");
		return "vistaListaClientesEmpleado";
	}

	@PostMapping("/guardar")
	public String registrar(Cliente cliente) {
		this.iClienteService.crear(cliente);
		LOG.info("Guardando cliente");
		return "redirect:/empleados/lista_clientes";
	}

	@GetMapping("/nuevoCliente")
	public String nuevoCliente(Cliente cliente) {
		return "vistaNuevoClienteEmpleado";

	}

	// Funcionalidad 2b
	@GetMapping("/buscar_cliente")
	public String buscarCliente(Cliente cliente) {
		return "vistaClienteBuscar";
	}

	@GetMapping("/cliente_cedula")
	public String cedulaCliente(Cliente cliente, Model modelo) {
		List<Cliente> lista = new ArrayList<>();
		lista.add(this.iClienteService.buscarPorCedula(cliente.getCedula()));
		modelo.addAttribute("lista", lista);
		return "vistaCedulaCliente";
	}

	// Funcionalidad 2c
	@GetMapping("/nuevo_vehiculo")
	public String nuevoVehiculo(Vehiculo vehiculo) {
		return "vistaNuevoVehiculo";

	}

	@PostMapping("/guardarVehiculo")
	public String registrarVehiculo(Vehiculo vehiculo) {
		this.iVehiculoService.guardar(vehiculo);
		LOG.info("Registrando vehículo");
		return "redirect:/empleados/lista_vehiculos";
	}

	@GetMapping("/lista_vehiculos")
	public String listaVehiculos(Model modelo) {
		List<Vehiculo> listaVehiculos = this.iVehiculoService.buscarTodosDisponibles();
		modelo.addAttribute("vehiculos", listaVehiculos);
		LOG.info("Reporte vehículos");
		return "vistaListaVehiculos";
	}

	// Funcionalidad 2d
	// http://localhost:8080/rentaCar/empleados/vehiculos_placa
	@GetMapping("/vehiculos_placa")
	public String vehiculosP(Vehiculo vehiculo, Model modelo) {
		List<Vehiculo> lista = new ArrayList<>();
		lista.add(this.iVehiculoService.buscarPlaca(vehiculo.getPlaca()));
		modelo.addAttribute("lista", lista);
		LOG.info("Buscando Placa");
		return "vistaVehiculosPlaca";
	}

	// http://localhost:8080/rentaCar/empleados/buscar_placa
	@GetMapping("/buscar_placa")
	public String buscarP(Vehiculo vehiculo) {
		return "vistaVehiculoBuscarPlaca";
	}

	// Funcionalidad 2f
	@GetMapping("/retirar_vehiculo")
	public String retirarSinReserva(Model modelo) {
		List<Vehiculo> lista = this.iVehiculoService.buscarTodosDisponibles();
		modelo.addAttribute("lista", lista);
		return "vistaRetirarSinReservar";
	}

	// reservar
	// http://localhost:8080/rentaCar/empleados/reservar
	@GetMapping("/reservar")
	public String reservar(ReservaDTO reservaDTO) {
		return "vistaReserva";
	}

	@GetMapping("/confirmacion_reserva")
	public String confirmacionReserva(ReservaDTO reservaDTO, Model model, HttpSession session) {
		if (this.iReservaService.verificar(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(),
				reservaDTO.getPlaca())) {
			LOG.warn("Error al confirmar su reserva");
			return "vistaError";
		} else {
			var total = this.iReservaService
					.calcularValorTotal(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(), reservaDTO.getPlaca())
					.get(2);
			model.addAttribute("total", total);
			session.setAttribute("reserva", reservaDTO);
			LOG.info("Reserva confirmada");
			return "vistaConfirmacionReserva";
		}

	}

	@PostMapping("/reservado")
	public String reservado(HttpSession session, ReservaDTO reserva, Model model) {
		ReservaDTO reservaDTO = (ReservaDTO) session.getAttribute("reserva");
		String codigo = this.iReservaService.reservar(reservaDTO.getFechaInicio(), reservaDTO.getFechaFin(),
				reservaDTO.getPlaca(), reservaDTO.getCedula(), reserva.getNumeroTarjeta());
		model.addAttribute("codigo", codigo);
		return "vistaRetirarReserva";
	}

	// retirar

	@GetMapping("/retirar_reservado")
	public String ingresarCodigo(Reserva reserva) {
		return "vistaRetirarReserva";
	}

	@GetMapping("/retirar")
	public String retirar(Reserva reserva, Model model, HttpSession session) {
		// metodo que regrese el string
		String str = this.iReservaService.getReserva(reserva.getCodigo());
		session.setAttribute("codigo", reserva.getCodigo());
		model.addAttribute("reserva", str);
		return "vistaRetirar";
	}

	@GetMapping("/aplicar_retiro")
	public String aplicar(Model model, HttpSession session) {
		// metodo que aplique la reserva en el service
		String codigo = (String) session.getAttribute("codigo");
		System.out.println("Codigo " + codigo);
		this.iReservaService.aplicar(codigo);
		return "vistaNotificacionReserva";
	}

}
