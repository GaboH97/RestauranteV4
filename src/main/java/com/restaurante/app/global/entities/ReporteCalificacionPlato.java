package com.restaurante.app.global.entities;

public class ReporteCalificacionPlato {

	private Plato plato;
	private Double calificacionPromedio;
	
	public ReporteCalificacionPlato() {}
	
	public ReporteCalificacionPlato(Plato plato, Double calificacionPromedio) {
		this.plato = plato;
		this.calificacionPromedio = calificacionPromedio;
	}

	/**
	 * @return the plato
	 */
	public Plato getPlato() {
		return plato;
	}

	/**
	 * @param plato the plato to set
	 */
	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	/**
	 * @return the numeroVecesOrdenado
	 */
	public Double getCalificacionPromedio() {
		return calificacionPromedio;
	}

	/**
	 * @param numeroVecesOrdenado the numeroVecesOrdenado to set
	 */
	public void setNumeroVecesOrdenado(Double calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}
	
}
