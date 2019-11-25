package com.restaurante.app.agentes.caja;

/**
 * 
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public enum TipoPago {

	TARJETA_CREDITO("Tarjeta de Crédito"), EFECTIVO("Efectivo");

	private String nombre;

	TipoPago(String name) {
		this.nombre = name;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}
}
