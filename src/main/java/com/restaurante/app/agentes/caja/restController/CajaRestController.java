package com.restaurante.app.agentes.caja.restController;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.app.agentes.caja.Caja;
import com.restaurante.app.agentes.caja.Pago;
import com.restaurante.app.global.entities.Orden;

@CrossOrigin(origins = { "http://localhost:4200" }) // anotacion necesaria
@RestController()
@RequestMapping("/api/caja")
public class CajaRestController {

	@PostMapping(value = "/AgregarPago")
	public void Pagar(@Valid @RequestBody Orden orden) {
		System.out.println("Aqui entra");
		Caja.getInstance().pagar(orden);
	}
	
	@GetMapping(value = "/ObtenerPagos")
	public ArrayList<Pago> listaPagos() {
		ArrayList<Pago> pagos = new ArrayList<>(Caja.getInstance().listaPagos());
		System.out.println(pagos);
		Caja.getInstance().limpiarHistorialPagos();
		return pagos;
	}
}
