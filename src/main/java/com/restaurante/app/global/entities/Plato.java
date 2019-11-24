package com.restaurante.app.global.entities;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public class Plato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private TipoPlato tipoPlato;
	private double precio;
	private int tiempoCoccion; // In minutes
	

	public Plato(String name, TipoPlato tipoPlato, double price, int cookingTime) {
		this.nombre = name;
		this.tipoPlato = tipoPlato;
		this.precio = price;
		this.tiempoCoccion = cookingTime;
	}
	
	public Plato() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public TipoPlato getTipoPlato() {
		return tipoPlato;
	}

	public void setTipoPlato(TipoPlato tipoPlato) {
		this.tipoPlato = tipoPlato;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double price) {
		this.precio = price;
	}

	public int getTiempoCoccion() {
		return tiempoCoccion;
	}

	public void setTiempoCoccion(int cookingTime) {
		this.tiempoCoccion = cookingTime;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nombre: ").append(nombre).append(System.getProperty("line.separator")).append("Tipo plato: ")
				.append(tipoPlato).append(System.getProperty("line.separator")).append("Tiempo coccion: ")
				.append(tiempoCoccion).append(System.getProperty("line.separator")).append("Precio: ").append(precio)
				.append(System.getProperty("line.separator"));

		return builder.toString();
	}

}
