package com.restaurante.app.agentes.mesa.controller;

import com.restaurante.app.agentes.mesa.model.DaoMesa;
import com.restaurante.app.agentes.mesa.model.Mesa;

public class Controller {
	private DaoMesa daoMesa;
	
	public Controller() {
		daoMesa = new DaoMesa();
	}
	
	public Mesa obtenerMesaLibre(int numeroClientes) {
		return daoMesa.getMesaLibre(numeroClientes);
	}
	
	public void liberarMesa(int idMesa) {
		daoMesa.liberarMesa(idMesa);
	}
}
