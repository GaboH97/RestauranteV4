package com.restaurante.app.global.entities;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class PlatoOrdenado {

	private Plato plato;
	private Integer calificacion;
	private boolean estaPreparado;
	private int tiempoPreparacionReal; // en minutos

	public PlatoOrdenado(Plato plato) {
		this.plato = plato;
		this.calificacion = 0;
		this.estaPreparado = false;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer rating) {
		this.calificacion = rating;
	}

	public boolean isEstaPreparado() {
		return estaPreparado;
	}

	public void setEstaPreparado(boolean estaPreparado) {
		this.estaPreparado = estaPreparado;
	}

	public int getTiempoPreparacionReal() {
		return tiempoPreparacionReal;
	}

	public void setTiempoPreparacionReal(int tiempoPreparacionReal) {
		this.tiempoPreparacionReal = tiempoPreparacionReal;
	}
}
