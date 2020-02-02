package com.restaurante.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.restaurante.app.agentes.cliente.GestionarCliente;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.global.config.Sequences;
import com.restaurante.app.global.entities.Orden;

@SpringBootTest
public class AgenteClienteTest {

	private GestionarCliente gestionarCliente = new GestionarCliente();

	
	/**
	 * Metodo que valida las mesas libres
	 */
	@Test
	public void obtenerMesaLibre() {
		Mesa mesa = new Mesa(Sequences.MESA_ID.getAndIncrement(),4, 6, 3);
		gestionarCliente.setMesa(mesa);
		// Validacion
		assertThat(gestionarCliente.getMesa()).isEqualTo(mesa);
	}

	
	/**
	 * Metodo encargado de calificarOrden
	 */
	@Test
	public void recibirYcalificarPedidos() {
		Orden orden = gestionarCliente.generarOrden();
		
		//calificacion orden
		orden = gestionarCliente.calificarOrdenesPersonales(orden);
		
		// Validacion
		assertThat(orden.getOrdenesPersonales().get(0).getPlatosOrdenados().get(0).getCalificacion() != null);
	}
	
	/**
	 * Metodo encargado de recibir orden 
	 */

	public void recibirOrden() {
		
	}
}
