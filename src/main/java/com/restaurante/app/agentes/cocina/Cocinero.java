package com.restaurante.app.agentes.cocina;

import com.restaurante.app.global.entities.OrdenPersonal;
import com.restaurante.app.global.entities.Plato;
import com.restaurante.app.global.entities.PlatoOrdenado;
import com.restaurante.app.global.entities.TipoPlato;

public class Cocinero {

	private int id;
	private int tiempoDescanso;
	private boolean estaDisponible;
	private int tiempoTrabajando; // minutos
	private boolean preparaPostres;

	public Cocinero(int id, int tiempoDescanso, boolean preparaPostres) {
		this.id = id;
		this.tiempoDescanso = tiempoDescanso;
		this.estaDisponible = true;
		this.tiempoTrabajando = 0;
		this.preparaPostres = preparaPostres;
	}

	public OrdenPersonal cocinarOrdenPersonal(OrdenPersonal ordenPersonal) {
		for (PlatoOrdenado plato : ordenPersonal.getPlatosOrdenados()) {
			if (!Cocina.congelador.estaOcupado()) {
				Cocina.congelador.ocuparCongelador();
				int tiempoPreparacion = generarTiempoPorPlato(plato.getPlato());
				if (this.tiempoTrabajando >= 120) {
					this.tiempoTrabajando = 0;
					tiempoPreparacion += 10;
				}
				plato.setTiempoPreparacionReal(tiempoPreparacion);
				plato.setEstaPreparado(true);
				this.tiempoTrabajando += tiempoPreparacion;
				Cocina.congelador.desocuparCongelador();
			}
		}
		this.estaDisponible = true;
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

	public int getTiempoTrabajando() {
		return tiempoTrabajando;
	}

	public void setTiempoTrabajando(int tiempoTrabajando) {
		this.tiempoTrabajando = tiempoTrabajando;
	}

	public boolean isPreparaPostres() {
		return preparaPostres;
	}

	public void setPreparaPostres(boolean preparaPostres) {
		this.preparaPostres = preparaPostres;
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
		Cocinero cocinero = new Cocinero(1, 10, false);
	}
}
