package com.restaurante.app.agentes.cocina;

import com.restaurante.app.global.entities.OrdenPersonal;
import com.restaurante.app.global.entities.Plato;
import com.restaurante.app.global.entities.PlatoOrdenado;
import com.restaurante.app.global.entities.TipoPlato;

public class Cocinero {

	private int id;
	private int tiempoDescanso;
	private boolean estaDisponible;
	private int tiempoTrabajando;

	public Cocinero(int id, int tiempoDescanso) {
		this.id = id;
		this.tiempoDescanso = tiempoDescanso;
		this.estaDisponible = false;
		this.tiempoTrabajando = 0;
	}

	public OrdenPersonal cocinarOrdenPersonal(OrdenPersonal ordenPersonal) {
		for (PlatoOrdenado plato : ordenPersonal.getPlatosOrdenados()) {
			if (!Cocina.congelador.estaOcupado()) {
				int tiempoPreparacion = generarTiempoPorPlato(plato.getPlato());
				plato.setTiempoPreparacionReal(tiempoPreparacion);
				this.tiempoTrabajando += tiempoPreparacion;
			}
		}
		return ordenPersonal;
	}

	public int generarTiempoPorPlato(Plato plato) {
		int porcentajeRango = (plato.getTiempoCoccion() * 20) / 100;
		return (int) Math.floor(Math.random()
				* ((plato.getTiempoCoccion() - porcentajeRango) - (plato.getTiempoCoccion() + porcentajeRango) + 1)
				+ (plato.getTiempoCoccion() + porcentajeRango));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTiempoDescanso() {
		return tiempoDescanso;
	}

	public void setTiempoDescanso(int tiempoDescanso) {
		this.tiempoDescanso = tiempoDescanso;
	}

	public boolean isEstaDisponible() {
		return estaDisponible;
	}

	public void setEstaDisponible(boolean estaDisponible) {
		this.estaDisponible = estaDisponible;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cocinero [id=");
		builder.append(id);
		builder.append(", tiempoDescanso=");
		builder.append(tiempoDescanso);
		builder.append(", estaDisponible=");
		builder.append(estaDisponible);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Cocinero cocinero = new Cocinero(1, 10);
		System.out.println(cocinero.generarTiempoPorPlato(new Plato("Este", TipoPlato.ENTRADA, 10, 10)));
	}
}
