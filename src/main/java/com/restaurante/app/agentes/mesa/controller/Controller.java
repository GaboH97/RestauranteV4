package com.restaurante.app.agentes.mesa.controller;

import java.util.ArrayList;

import com.restaurante.app.agentes.mesa.model.DaoMesa;
import com.restaurante.app.agentes.mesa.model.Mesa;

public class Controller {
	
	private DaoMesa daoMesa;
	
	public Mesa obtenerMesaLibre(int numeroClientes) {
		return daoMesa.getMesaLibre(numeroClientes);
	}
	
	public void liberarMesa(int idMesa) {
		daoMesa.liberarMesa(idMesa);
	}
	
	public void generarMesas() {
		for (int i = 0; i < 14; i++) {
			daoMesa.addMesa(new Mesa(1, 1, Mesa.CAPACIDAD_MAXIMA));
		}
	}
//patron singleton------------------------------
	
	public static Controller instance;

	//metodo que permite obtener una instancia de la clase
	public static Controller gestInstance() {
		if (instance == null) {
			instance = new Controller();
			instance.generarMesas();
		}
		return instance;
	}
	
	//controlador privado para que no pueda ser accedido desde el exterior
	private Controller() {
		daoMesa = new DaoMesa();
	}
	
	public ArrayList<Mesa> obtenerMesas() {
		return daoMesa.obtenerMesas();
	}
}

