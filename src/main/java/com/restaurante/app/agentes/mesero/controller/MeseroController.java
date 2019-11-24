package com.restaurante.app.agentes.mesero.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.ManagerMeseros;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.entities.Orden;

@RestController
@RequestMapping("/api/meseros")
public class MeseroController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ManagerMeseros managerMeseros;
	
	
	/**
	 * Método que agrega una orden proveniente
	 * @param orden
	 */
	@PostMapping("/orden")
    public void agregarOrden(@RequestBody Orden orden) {
        managerMeseros.agregarOrden(orden);
    }
	
	/**
	 *
	 * @param idMesero
	 * @return Lista de ordenes que le fueron asignadas a determinado mesero
	 */
	@GetMapping("ordenes/{idMesero}")
	public List<Orden> obtenerOrdenes(@PathVariable Integer idMesero){
		return managerMeseros.obtenerOrdenesPorMesero(idMesero);
	}
	
	/**
	 * Método que hace llamado a servicio del agente cocina para obtener las órdenes que ya están preparadas
	 * 
	 * @return Ordenes preparadas provenientes de la cocina
	 */
	public List<Orden> obtenerOrdenesPreparadas(){
		//TO-DO
		return null;
	}
	
	/**
	 * MÉTODO PRUEBA PARA OBTENER LA MESA LIMPIA
	 * 
	 * @param mesa
	 * @return
	 */
	@PostMapping(path="mesa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mesa> obtenerMesa(@RequestBody Mesa mesa) {
		return new ResponseEntity<Mesa>(mesa, HttpStatus.CREATED);
	}
	
	/**
	 * Método que obtiene la mesa a limpiar desde el agente mesa
	 * @param mesa
	 */
	@PostMapping("mesaALimpiar")
	public void obtenerMesaALimpiar(@RequestBody Mesa mesa) {
		
	}
	
	public void retornarMesaLimpia(Mesa mesa) {
		
		String url = "http://localhost:8080/api/meseros/mesa";
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<Mesa> entity  = new HttpEntity<Mesa>(mesa, headers);
	    
	    ResponseEntity<Mesa> response = this.restTemplate.postForEntity(url, entity, Mesa.class);
	    
	    if(response.getStatusCode()== HttpStatus.CREATED) {
	    	System.out.println(response.getBody());
	    }

	}
	
	@GetMapping("meseroLibre")
	public Mesero obtenerMeseroDisponible() {
		return managerMeseros.obtenerMeseroDisponible();
	}
	
}
