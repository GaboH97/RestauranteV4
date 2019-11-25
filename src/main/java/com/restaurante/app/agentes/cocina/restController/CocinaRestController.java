package com.restaurante.app.agentes.cocina.restController;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.app.agentes.cocina.GestionarCocina;
import com.restaurante.app.global.entities.Orden;

@CrossOrigin(origins = { "http://localhost:4200" }) // anotacion necesaria
@RestController
@RequestMapping("/api/cocina")
public class CocinaRestController {

	@PostMapping("/agregarListaOrdenesSinPreparar")
	public ArrayList<Orden> agregarOrdenesAPreparar(@Valid @RequestBody ArrayList<Orden> ordenes) {
		return GestionarCocina.getInstance().agregarOrdenesACocinar(ordenes);
	}

	@PostMapping("/agregarOrdenSinPreparar")
	public ArrayList<Orden> agregarOrdenAPreparar(@Valid @RequestBody Orden orden) {
		return GestionarCocina.getInstance().agregarOrdenAPreparar(orden);
	}

	@GetMapping(value = "/obtenerListaOrdenesPreparadas")
	public ArrayList<Orden> obtenerOrdenesPreparadas() {
		return GestionarCocina.getInstance().obtenerOrenesCocinadas();
	}
}
