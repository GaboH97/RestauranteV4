package com.restaurante.app.agentes.mesa.restController;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.app.agentes.mesa.controller.Controller;
import com.restaurante.app.agentes.mesa.model.Mesa;

/**
 * Servicios relacionados con el agente mesa.
 * @author Cesar Cardozo
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" }) //anotacion necesaria
@RestController()//anotacion necesaria para hacer que esta clase se comporte como un controlador de servicios rest
@RequestMapping("/api/mesa")
public class MesaRestController {

	/**
	 * metodo que permite obtener una mesa disponible deacuerdo al numero de clientes
	 * @param numeroClientes: numero de clientes que requieren la mesa
	 * @return primera mesa que encuentre disponible con la capacidad requerida
	 */
	@GetMapping(value = "/obtenerMesaLibre/{numeroClientes}")
	public Mesa obtenerMesaLibre(@PathVariable Integer numeroClientes) {//con la anotacion pathvariable se pueden mandar datos atravez del url
		return Controller.gestInstance().obtenerMesaLibre(numeroClientes);
	}
	
	/**
	 * servicio que permite liberar una mesa deacuerdo a su id
	 * @param idMesa: id de la mesa que se desea liberar
	 */ 
	@GetMapping(value = "/liberarMesa/{idMesa}")
	public void liberarMesa(@PathVariable Integer idMesa) {
		Controller.gestInstance().liberarMesa(idMesa); 
	}
	
	/**
	 * metodo que permite obtener todas las mesas 
	 * @return la lista completa de las mesas 
	 */
	@GetMapping(value = "/obtenerMesas")
	public ArrayList<Mesa> obtenerMesas() {
		return Controller.gestInstance().obtenerMesas();
	}
}