package com.restaurante.app.agentes.caja.restController;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.app.agentes.caja.Caja;
import com.restaurante.app.agentes.caja.Pago;
import com.restaurante.app.agentes.cliente.GestionarCliente;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.global.entities.Orden;

@CrossOrigin(origins = { "http://localhost:4200" }) // anotacion necesaria
@RestController
public class CajaRestController {

	@GetMapping(value = "/totalPagoPorMesa")
	public double totalPagoPorMesa() {
		return Caja.getInstance().TotalAPagarPorMesa();
	}

	@GetMapping(value = "/Pagar")
	public void Pagar(@Valid @RequestBody Orden orden) {
		Caja.getInstance().tipoDePago(orden);
	}
	
	@GetMapping(value = "/AgregarPago")
	public void Pagar(@Valid @RequestBody Pago pago) {
		Caja.getInstance().agregarPago(pago);
	}
	
}
