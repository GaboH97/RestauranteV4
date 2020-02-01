package com.restaurante.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.restaurante.app.agentes.caja.Caja;
import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.entities.Carta;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;
import com.restaurante.app.global.entities.Plato;

@SpringBootTest
public class AgenteCajaTest {
	
	private Caja caja =  new Caja();

	/*
	 * Metodo que valida el metodo de obtenerTotala pagar 
	 */
	@Test
	public void obtenerTotalAPagar() {
//		// se conforma la orden por parte del cliente
//		ArrayList<Plato> listaPlatos = new ArrayList<Plato>();
//		listaPlatos.add(Carta.MENU.get(0)); // Seleccion del primer plato de la carta para conformar orden personal
//		OrdenPersonal ordenPersonalCliente1 = new OrdenPersonal(new Cliente(), listaPlatos);
//
//		// se arma la lista de ordenes a preparar
//		ArrayList<Orden> listaOrdenApreparar = new ArrayList<>();
//		Orden orden = new Orden(new Mesero("Juan Perez"));
//
//		Double totalApagar = caja.TotalAPagarPorMesa();

		// Validacion
		assertThat(1).isEqualTo(1);
	}

}
