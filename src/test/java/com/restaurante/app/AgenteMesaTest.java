package com.restaurante.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.restaurante.app.agentes.mesa.controller.Controller;
import com.restaurante.app.agentes.mesa.model.Mesa;

@SpringBootTest
public class AgenteMesaTest {

	private Controller controllerMesa =  new Controller();
	
	/**
	 * Metodo que valida la mesa libre con el numero de clientes indicado
	 */
	@Test
	public void obtenerMesaLibre() {
		controllerMesa.generarMesas();
		int numClientes = 5;
		Mesa mesa = controllerMesa.obtenerMesaLibre(numClientes);
		//Validacion
		assertThat(mesa.getCapacidad() == numClientes);
	}
	
	/*
	 *Metodo que valida el proceso de desocupar la mesa
	 */
	@Test
	public void desocuparMesa() {
		controllerMesa.generarMesas();
		//ocupar mesa
		int numClientes = 5;
		Mesa mesa = controllerMesa.obtenerMesaLibre(numClientes);
		
		//validaciòn de la mesa desocupada
		controllerMesa.liberarMesa(mesa.getId()); 
		//Validacion
		assertThat(mesa.isDisponible()).isEqualTo(true);
	}
	
	/**
	 * Metodo que valida el proceso de generacion y obtenciòn de mesas,
	 *  en este caso la simulaciòn se realizo con 14 mesas
	 */
	@Test
	public void obtenerMesas() {
		controllerMesa.generarMesas();
		ArrayList<Mesa> listaMesas = controllerMesa.obtenerMesas();
		//Validacion
		assertThat(listaMesas.size() == 14);
	}
}
