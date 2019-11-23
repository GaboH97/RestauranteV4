package com.restaurante.app.agentes.cliente.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.app.agentes.cliente.GestionarCliente;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.entities.Orden;
/**
 * Clase encargada de la gestion de los servicios de cliente
 * @author pedro
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" }) //anotacion necesaria
@RestController
public class ClienteRestController {
	
	/**
	 * Atributo que perite la gestion de los clientes
	 */
	@Autowired
	private GestionarCliente gestionarCliente;
	

	/**
	 * Cuando llega el cliente o los clientes estos seleccionan la mesa
	 * @param mesa
	 */
	@GetMapping(value = "/obtenerMesaLibre/{numeroClientes}")
	public void seleccionarMesa(Mesa mesa) {
		gestionarCliente.setMesa(mesa);
	}
	
	/**
	 *  Los clientes de la mesa asignada solicitan el pedido
	 * @param mesero
	 * @return
	 */
	@PostMapping("/solicitarPedido")
	public Orden solicitarPedido(@Valid @RequestBody Mesero mesero) {
		Orden orden = gestionarCliente.generarOrden();
		orden.setTable(gestionarCliente.getMesa());
		return  orden;
	}
	
	/**
	 * Metodo encargado de que el cliente califique la orden
	 * @param orden
	 */
	@PostMapping("/recibirOrden")
	public Orden recibirPedido(@Valid @RequestBody Orden orden) {
		return gestionarCliente.calificarOrdenesPersonales(orden);
	}
	
	/**
	 * Metodo encargado de recibir el mesero y agrgar la calificacion
	 * @param mesero
	 * @return
	 */
//	@PostMapping("/calificarMesero")
//	public Mesero calificarMesero(@Valid @RequestBody Mesero mesero) {
//		return gestionarCliente.calificarMesero(mesero);
//	}
}