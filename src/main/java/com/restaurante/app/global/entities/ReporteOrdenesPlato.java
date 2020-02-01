package com.restaurante.app.global.entities;

public class ReporteOrdenesPlato {

	private Plato plato;
	private Long numeroVecesOrdenado;
	
	public ReporteOrdenesPlato() {}
	
	public ReporteOrdenesPlato(Plato plato, Long numeroVecesOrdenado) {
		this.plato = plato;
		this.numeroVecesOrdenado = numeroVecesOrdenado;
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
	public Long getNumeroVecesOrdenado() {
		return numeroVecesOrdenado;
	}

	/**
	 * @param numeroVecesOrdenado the numeroVecesOrdenado to set
	 */
	public void setNumeroVecesOrdenado(Long numeroVecesOrdenado) {
		this.numeroVecesOrdenado = numeroVecesOrdenado;
	}
	
}
