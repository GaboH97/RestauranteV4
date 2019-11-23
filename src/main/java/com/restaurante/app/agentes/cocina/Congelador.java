package com.restaurante.app.agentes.cocina;

public class Congelador {

	private boolean estaOcupado;

	public Congelador() {
		this.estaOcupado = false;
	}

	public void ocuparCongelador() {
		this.estaOcupado = true;
	}

	public void desocuparCongelador() {
		this.estaOcupado = false;
	}

	public boolean estaOcupado() {
		return estaOcupado;
	}

	public void setEstaOcupado(boolean estaOcupado) {
		this.estaOcupado = estaOcupado;
	}
}
