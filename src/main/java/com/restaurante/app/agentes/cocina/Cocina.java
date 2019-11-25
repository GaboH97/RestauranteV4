package com.restaurante.app.agentes.cocina;

import java.util.ArrayList;

import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;
import com.restaurante.app.global.entities.PlatoOrdenado;
import com.restaurante.app.global.entities.TipoPlato;

public class Cocina {
	private ArrayList<Orden> listaOrdenesAPreparar;
	private ArrayList<Orden> listaOrdenesPreparadas;
	private ArrayList<Cocinero> listaCocineros;
	public static Congelador congelador;

	public Cocina() {
		this.listaCocineros = new ArrayList<Cocinero>();
		this.listaOrdenesPreparadas = new ArrayList<Orden>();
		this.listaOrdenesAPreparar = new ArrayList<Orden>();
		this.listaCocineros = new ArrayList<Cocinero>();
		Cocina.congelador = new Congelador();
		this.crearCocineros();
	}

	public void crearCocineros() {
		this.listaCocineros.add(new Cocinero(1, 10, true));
		this.listaCocineros.add(new Cocinero(2, 10, false));
	}

	public void agregarOrdenALaCola(Orden nuevaOrden) {
		this.listaOrdenesAPreparar.add(nuevaOrden);
	}

	public ArrayList<Orden> cocinar() {
		for (Orden orden : listaOrdenesAPreparar) {
			for (OrdenPersonal ordenPersonal : orden.getPersonalOrders()) {
				Cocinero cocineroDisponible;
				if (laOrdenContienePostres(ordenPersonal)) {
					cocineroDisponible = listaCocineros.get(0);
					listaCocineros.get(0).setEstaDisponible(false);
				} else {
					cocineroDisponible = buscarCocineroDisponible();
				}
				cocineroDisponible.cocinarOrdenPersonal(ordenPersonal);
			}
			listaOrdenesPreparadas.add(orden);
		}
		return listaOrdenesPreparadas;
	}

	private Cocinero buscarCocineroDisponible() {
		for (Cocinero cocinero : listaCocineros) {
			if (cocinero.isEstaDisponible()) {
				cocinero.setEstaDisponible(false);
				listaCocineros.get(cocinero.getId() == 0 ? 1 : 0).setEstaDisponible(true);
				return cocinero;
			}
		}
		return null;
	}

	private boolean laOrdenContienePostres(OrdenPersonal ordenPersonal) {
		for (PlatoOrdenado plato : ordenPersonal.getPlatosOrdenados()) {
			if (plato.getPlato().getTipoPlato().equals(TipoPlato.POSTRE)) {
				return true;
			}
		}
		return false;
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
