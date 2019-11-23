package com.restaurante.app.agentes.cocina;

import java.util.ArrayList;

import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;

public class Cocina {
	private ArrayList<Orden> listaOrdenesAPreparar;
	private ArrayList<Orden> listaOrdenesPreparadas;
	private ArrayList<Cocinero> listaCocineros;
	public static Congelador congelador;

	public Cocina() {
		this.listaCocineros = new ArrayList<Cocinero>();
		this.listaOrdenesPreparadas = new ArrayList<Orden>();
		this.listaCocineros = new ArrayList<Cocinero>();
		Cocina.congelador = new Congelador();
		this.crearCocineros();
	}

	public void crearCocineros() {
		this.listaCocineros.add(new Cocinero(1, 10));
		this.listaCocineros.add(new Cocinero(2, 10));
	}

	public ArrayList<Orden> cocinar(ArrayList<Orden> listaOrdenesAPreparar) {
		for (Orden orden : listaOrdenesAPreparar) {
			for (OrdenPersonal ordenPersonal : orden.getPersonalOrders()) {
				Cocinero cocineroDisponible = buscarCocineroDisponible();
				cocineroDisponible.cocinarOrdenPersonal(ordenPersonal);
			}
		}
		return listaOrdenesPreparadas;
	}

	private Cocinero buscarCocineroDisponible() {
		for (Cocinero cocinero : listaCocineros) {
			if (cocinero.isEstaDisponible()) {
				return cocinero;
			}
		}
		return null;
	}

	public ArrayList<Orden> getListaOrdenesAPreparar() {
		return listaOrdenesAPreparar;
	}

	public void setListaOrdenesAPreparar(ArrayList<Orden> listaOrdenesAPreparar) {
		this.listaOrdenesAPreparar = listaOrdenesAPreparar;
	}

	public ArrayList<Orden> getListaOrdenesPreparadas() {
		return listaOrdenesPreparadas;
	}

	public void setListaOrdenesPreparadas(ArrayList<Orden> listaOrdenesPreparadas) {
		this.listaOrdenesPreparadas = listaOrdenesPreparadas;
	}

	public ArrayList<Cocinero> getListaCocineros() {
		return listaCocineros;
	}

	public void setListaCocineros(ArrayList<Cocinero> listaCocineros) {
		this.listaCocineros = listaCocineros;
	}
}
