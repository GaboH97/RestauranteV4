package com.restaurante.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.agentes.mesero.ManagerMeseros;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.entities.Carta;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;
import com.restaurante.app.global.entities.Plato;

@SpringBootTest
public class AgenteMeseroTest {

	/**
	 * Instancia de la clase que gestiona el mesero
	 */
	private ManagerMeseros managerMesero;

	/**
	 * Metodo que valida el recibir orden de un cliente
	 */
	@Test
	public void recibirOrdenClienteTest() {
		// se conforma la orden por parte del cliente
		ArrayList<Plato> listaPlatos = new ArrayList<Plato>();
		listaPlatos.add(Carta.MENU.get(0)); // Seleccion del primer plato de la carta para conformar orden personal
		OrdenPersonal ordenPersonalCliente1 = new OrdenPersonal(new Cliente(), listaPlatos);

		// se arma la lista de ordenes a preparar
		ArrayList<Orden> listaOrdenApreparar = new ArrayList<>();
//		Orden orden = new Orden(new Mesero());

//		Mesero mesero1 = new Mesero("Nicolas");
//		managerMesero.agregarOrden(orden, mesero1.getId());

	}

	/*
	 * TODO
	 */
	@Test
	public void recibirOrdenPreparada() {
		// se conforma la orden por parte del cliente
		ArrayList<Plato> listaPlatos = new ArrayList<Plato>();
		listaPlatos.add(Carta.MENU.get(0)); // Seleccion del primer plato de la carta para conformar orden personal
		OrdenPersonal ordenPersonalCliente1 = new OrdenPersonal(new Cliente(), listaPlatos);

		// se arma la lista de ordenes a preparar
		ArrayList<Orden> listaOrdenApreparar = new ArrayList<>();
//		Orden orden = new Orden(new Mesero("Juan Perez"));

		Mesero mesero1 = new Mesero("Nicolas");
//		managerMesero.agregarOrden(orden, mesero1.getId());
	
		
		// Validacion
		assertThat(managerMesero.obtenerOrdenes()).isEqualTo(1);
	}
	
	@Test
	public void limpiarMesa() {
		
	}
}