package com.restaurante.app.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
public class RestauranteRestController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("simular")
	public void simular() {
		
		for (int i = 0; i < 20; i++) {
			Mesero mesero = obtenerMeseroDisponible();
			Orden orden = generarOrden(mesero);
			Mesa mesa = obtenerMesaDisponible(orden.getOrdenesPersonales().size());
			orden.setMesa(mesa);
			orden.setMesero(mesero);

			Mesero meseroAux = agregarOrden(mesero.getId(), orden);

			// Aquí se manda la orden a la cocina
			
			mandarOrdenesACocina(meseroAux.getOrdenes());
			
			// comen (pasa el tiempo de consumo)
			// aqui se libera la mesa
			desocuparMesa(mesa);
			// aqui se paga
			
			agregarPago(orden);
			
			//Agrega orden a historial de ordenes
			agregarOrdenAHistorial(orden);
		}
		
		ManagerRestaurante.getInstance().getHistorialOrdenes().forEach(System.out::println);
	}
	
	/**
	 * Método que se encargar de agregar una orden a un mesero
	 * 
	 * @param idMesero
	 * @param orden
	 * @return Mesero que ha tomado esa orden
	 */
	private Mesero agregarOrden(int idMesero, Orden orden) {
		String url = "http://localhost:8080/api/meseros/{idMesero}/agregarOrden";
		return restTemplate.postForObject(url, orden, Mesero.class, idMesero);
	}
	
	/**
	 * Método que consume el servicio expuesto por el agente Mesa para desocupar una mesa con un ID
	 * específico
	 * 
	 * @param mesa
	 */
	private void desocuparMesa(Mesa mesa) {
		String url = "http://localhost:8080/liberarMesa/{idMesa}";
		restTemplate.getForObject(url, Mesero.class, mesa.getId());
	}
	
	/**
	 * Método que envía las órdenes al agente cocina para que sean preparadas por los chefs
	 * @param ordenes
	 */
	private void mandarOrdenesACocina(List<Orden> ordenes) {
		String url = "http://localhost:8080/api/cocina/agregarListaOrdenesSinPreparar";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(ordenes,headers);
		ResponseEntity<List<Orden>> rateResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Orden>>() {});
		
	}

	/**
	 * Método que consume el servicio expuesto por el agente Mesero para obtener un
	 * mesero libre para que atienda una mesa
	 * 
	 * @return Mesero disponible para atender
	 */
	private Mesero obtenerMeseroDisponible() {
		String url = "http://localhost:8080/api/meseros/obtenerMeseroLibre";
		return restTemplate.getForObject(url, Mesero.class);
	}

	/**
	 * Método que consume el servicio expuesto por el agente Mesa para obtener una
	 * mesa libre
	 * 
	 * @return
	 */
	private Mesa obtenerMesaDisponible(int numeroClientes) {
		String url = "http://localhost:8080/obtenerMesaLibre/{numeroClientes}";
		return restTemplate.getForObject(url, Mesa.class, numeroClientes);
	}

	/**
	 * Método que consume el servicio expuesto por el agente Clientes, el cual internamente simula el arribo
	 * de clientes, genera las órdenes personales y las agrupa en una orden grupal
	 * 
	 * @return
	 */
	private Orden generarOrden(Mesero mesero) {
		String url = "http://localhost:8080/solicitarPedido";
		return restTemplate.postForObject(url, mesero, Orden.class);
	}
	
	/**
	 * 
	 * @param idMesa
	 */
	private void liberarMesa(Integer idMesa) {
	String url = "http://localhost:8080/liberarMesa/{idMesa}";
	restTemplate.getForObject(url, Orden.class, idMesa);

	}
	/**
	 * 
	 * @param orden
	 */
	private void agregarPago(Orden orden) {
		String url = "http://localhost:8080/AgregarPago";
		restTemplate.postForObject(url, orden, Orden.class);
	}
	
	/**
	 * 
	 * @param orden
	 */
	private void agregarOrdenAHistorial(Orden orden) {
	System.out.println(orden);
		ManagerRestaurante.getInstance().agregarOrdenAHistorial(orden);
	}
}
