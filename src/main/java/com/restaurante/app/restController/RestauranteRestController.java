package com.restaurante.app.restController;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restaurante.app.agentes.caja.Pago;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.config.NetConstants;
import com.restaurante.app.global.entities.*;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/restaurante")
public class RestauranteRestController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("simular")
	public void simular() {

		for (int i = 0; i < 175; i++) {
			Mesero mesero = obtenerMeseroDisponible();
			Orden orden = generarOrden(mesero);
			Mesa mesa = obtenerMesaDisponible(orden.getOrdenesPersonales().size());
			orden.setMesa(mesa);

			Mesero meseroAux = agregarOrden(mesero.getId(), orden);

			// Aquí se manda la orden a la cocina
			mandarOrdenesACocina(meseroAux.getOrdenes());

			// Aquí se pasa la orden a los clientes
			ArrayList<Orden> ordenes = obtenerOrdenesDeCocina();

			// comen (pasa el tiempo de consumo)
			ArrayList<Orden> ordenesConsumidas = llevarOrdenesAClientes(ordenes);

			// aqui se libera la mesa
			desocuparMesa(mesa);

			// aqui se paga
			agregarPago(orden);

			// Agrega orden a historial de ordenes
			agregarOrdenAHistorial(orden);
		}

		ManagerRestaurante.getInstance().getHistorialOrdenes().forEach(System.out::println);
	}

	/**
	 * Método que envía las órdenes consumidas provenientes del agente cocina
	 * 
	 * @param ordenes
	 * @return
	 */
	private ArrayList<Orden> llevarOrdenesAClientes(ArrayList<Orden> ordenes) {
		ArrayList<Orden> ordenesConsumidas = new ArrayList<>();
		String url = NetConstants.CLIENTE_URL_ENDPOINT + "recibirOrden";

		for (Orden orden : ordenes) {
			Orden ordenConsumida = restTemplate.postForObject(url, orden, Orden.class);
			ordenesConsumidas.add(ordenConsumida);
		}
		return ordenesConsumidas;
	}

	private ArrayList<Orden> obtenerOrdenesDeCocina() {
		String url = "http://localhost:8080//api/cocina/obtenerListaOrdenesPreparadas";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Orden>>() {
		}).getBody();
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
	 * Método que consume el servicio expuesto por el agente Mesa para desocupar una
	 * mesa con un ID específico
	 * 
	 * @param mesa
	 */
	private void desocuparMesa(Mesa mesa) {
		String url = "http://localhost:8080/api/mesa/liberarMesa/{idMesa}";
		restTemplate.getForObject(url, Mesero.class, mesa.getId());
	}

	/**
	 * Método que envía las órdenes al agente cocina para que sean preparadas por
	 * los chefs
	 * 
	 * @param ordenes
	 */
	private void mandarOrdenesACocina(List<Orden> ordenes) {
		String url = "http://localhost:8080/api/cocina/agregarListaOrdenesSinPreparar";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(ordenes, headers);
		restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Orden>>() {
		});

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
		String url = "http://localhost:8080/api/mesa/obtenerMesaLibre/{numeroClientes}";
		return restTemplate.getForObject(url, Mesa.class, numeroClientes);
	}

	/**
	 * Método que consume el servicio expuesto por el agente Clientes, el cual
	 * internamente simula el arribo de clientes, genera las órdenes personales y
	 * las agrupa en una orden grupal
	 * 
	 * @return
	 */
	private Orden generarOrden(Mesero mesero) {
		String url = "http://localhost:8080/api/cliente/solicitarPedido";
		return restTemplate.postForObject(url, mesero, Orden.class);
	}

	/**
	 * 
	 * @param orden
	 */
	private void agregarPago(Orden orden) {
		String url = "http://localhost:8080/api/caja/AgregarPago";
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

	/**
	 * 
	 */
	@GetMapping("GenerarEstadisticas")
	public ResponseEntity<?> generarEstadisticas() {
		ManagerRestaurante.getInstance().generarEstadisticas();
		Map<String, Object> body = new HashMap<String, Object>();

		body.put("cantidadVecesPlatoOrdenado",
				convertirAReportePlatoOrdenado(ManagerRestaurante.getInstance().getCantidadVecesPlatoOrdenado()));

//		Map<String, Double> hashMap = new HashMap<>();
//
//		Map<Plato, Double> c = ManagerRestaurante.getInstance().getPlatosMejorCalificadosPorTipoPlato();
//		for (Entry<Plato, Double> pair : c.entrySet()) {
//			hashMap.put(pair.getKey().getNombre(), pair.getValue());
//		}
//
//		body.put("platosMejorCalificadosPorTipoPlato", hashMap);
//		body.put("numeroOrdenesPorEstrategiaPago",
//				ManagerRestaurante.getInstance().getNumeroOrdenesPorEstrategiaPago());
//		body.put("gananciasTotales", obtenerGananciasTotales(obtenerPagos()));
//		
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
	}

	private List<ReportePlatoOrdenado> convertirAReportePlatoOrdenado(Map<String, Long> cantidadVecesPlatoOrdenado) {
		return cantidadVecesPlatoOrdenado.entrySet().stream()
				.map(e -> new ReportePlatoOrdenado(e.getKey(), e.getValue())).collect(Collectors.toList());
	}

	public ArrayList<Pago> obtenerPagos() {
		String url =  "http://localhost:8080/api/caja/ObtenerPagos";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Pago>>() {
		}).getBody();
	}

	@ResponseBody
	@GetMapping("ObtenerListaPagos")
	public ArrayList<Pago> obtenerListaPagos() {
		return obtenerPagos();
	}

	private Double obtenerGananciasTotales(ArrayList<Pago> pagos) {
		return pagos.stream().mapToDouble(Pago::getTotalOrden).sum();
	}

}
