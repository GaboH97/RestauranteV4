package com.restaurante.app.agentes.mesa.model;

import java.util.ArrayList;

public class DaoMesa {
	private ArrayList<Mesa> listaMesa;
	
	public DaoMesa() {
		listaMesa = new ArrayList<Mesa>();
	}
	
	public void addMesa(Mesa mesa) {
		listaMesa.add(mesa);
	}
	
	public Mesa getMesaLibre(int numeroClientes) {
		for (Mesa mesa : listaMesa) {
			if(mesa.getCapacidad() < numeroClientes&&
					mesa.isDisponible()) {
				mesa.ocupar();
				return mesa;
			}
		}
		return null;
	}
	
	public void liberarMesa(int idMesa) {
		for (Mesa mesa : listaMesa) {
			if(mesa.getId() == idMesa) {
				mesa.desocupar();
			}
		}
	}
	
	public ArrayList<Mesa> obtenerMesas() {
		return listaMesa;
	}
}
