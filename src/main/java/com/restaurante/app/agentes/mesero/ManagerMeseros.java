package com.restaurante.app.agentes.mesero;

import java.util.ArrayList;

public class ManagerMeseros {

	private ArrayList<Mesero> meseros;

	public ManagerMeseros() {
		meseros = new ArrayList<>();
	}

	public Mesero obtenerMeseroDisponible() {
		return meseros.stream().filter(m -> m.getEstado().equals(EstadoMesero.DISPONIBLE)).findAny().orElse(null);
	}

	public ArrayList<Mesero> getMeseros() {
		return meseros;
	}

	public void setMeseros(ArrayList<Mesero> meseros) {
		this.meseros = meseros;
	}
}
