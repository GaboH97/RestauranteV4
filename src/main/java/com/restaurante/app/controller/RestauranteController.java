package com.restaurante.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.entities.Orden;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("simular")
	public void simular() {
		//Obtene el mesero disponible que puede tomar la orden
		Mesero mesero = obtenerMeseroDisponible();
		Orden orden = generarOrden(mesero);
		Mesa mesa = obtenerMesaDisponible(orden.getPersonalOrders().size());
		
		System.out.println("ESTA ES LA MESA"+mesa);
		orden.setMesa(mesa);
		//Aquí se manda la orden a la cocina
	}
	
	private Mesero obtenerMeseroDisponible() {
		String url = "http://localhost:8080/api/meseros/obtenerMeseroLibre";
		return restTemplate.getForObject(url, Mesero.class);
	}

	/**
	 * Método que consume el servicio expuesto por el agente Mesa para obtener una mesa libre
	 * 
	 * @return
	 */
	private Mesa obtenerMesaDisponible(int numeroClientes) {
		String url = "http://localhost:8080/obtenerMesaLibre/{numeroClientes}";
		return  restTemplate.getForObject(url, Mesa.class, numeroClientes);
	}

	public List<Cliente> entradaClientes() {
		String url = "http://localhost:8080/api/meseros/mesa";

		ResponseEntity<List<Cliente>> respuesta = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Cliente>>() {
				});
		return respuesta.getBody();
	}
	
	/**
	 * Método que genera una orden a tomar por un mesero
	 * @return
	 */
	private Orden generarOrden(Mesero mesero) {
		String url = "http://localhost:8080/solicitarPedido";
		return restTemplate.postForObject(url, mesero, Orden.class);
	}
}
