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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tiempoCoccion;
		result = prime * result + ((tipoPlato == null) ? 0 : tipoPlato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plato other = (Plato) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (tiempoCoccion != other.tiempoCoccion)
			return false;
		if (tipoPlato != other.tipoPlato)
			return false;
		return true;
	}
	
	

}
