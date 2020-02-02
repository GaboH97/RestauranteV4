package com.restaurante.app.global.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Orden implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Mesa mesa;
	private ArrayList<OrdenPersonal> ordenesPersonales;
	private EstrategiaPago estrategiaPago;
	private boolean estaPreparado;
	
	public Orden() {}
	
	public Orden(int id) {
		this.id = id;
		this.ordenesPersonales = new ArrayList<>();
		this.estrategiaPago = escogerEstrategiaPago();
	}

	/**
	 *
	 * @return Total cost of the order which is equal to the sum of each personal
	 *         order
	 */
	public double obtenerTotal() {
		return ordenesPersonales.stream().mapToDouble(po -> po.obtenerPrecioTotal()).sum();
	}

	/**
	 *
	 * @return Value of the tip equal to 10% of the order's total
	 */
	public double calcularPropinaMesero() {
		return obtenerTotal() * 0.1;
	}

	/**
	 *
	 * @return A randomly chosen Pago Strategy
	 */
	public EstrategiaPago escogerEstrategiaPago() {
		int num = new Random().nextInt(3);
		return num == 0 ? EstrategiaPago.AMERICANO
				: num == 1 ? EstrategiaPago.UNO_POR_TODOS : EstrategiaPago.TODOS_POR_TODO;
	}

	/**
	 *
	 * @return Amount to be paid by every client when using a All-for-everything
	 *         payment strategy
	 */
	public double obtenerPromedioPagar() {
		return obtenerTotal() / ordenesPersonales.size();
	}

	/**
	 *
	 * @return
	 */
	public Cliente obtenerClienteAzarPagar() {
		return ordenesPersonales.get(new Random().nextInt(ordenesPersonales.size())).getClient();
	}

	public boolean isEstaPreparado() {
		estaPreparado = ordenesPersonales.stream().allMatch(OrdenPersonal::isEstaPreparado);
		return estaPreparado;
	}

	// ============ GETTERS & SETTERS ================

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public ArrayList<OrdenPersonal> getOrdenesPersonales() {
		return ordenesPersonales;
	}

	public void setPersonalOrders(ArrayList<OrdenPersonal> ordenPersonals) {
		this.ordenesPersonales = ordenPersonals;
	}

	public EstrategiaPago getEstrategiaPago() {
		return estrategiaPago;
	}

	public void setEstrategiaPago(EstrategiaPago estrategiaPago) {
		this.estrategiaPago = estrategiaPago;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order ID: ").append(id).append(System.getProperty("line.separator"));
		builder.append("Mesa: ").append(mesa).append(System.getProperty("line.separator")).append("Personal Orders: ")
				.append(System.getProperty("line.separator"));

		ordenesPersonales
				.forEach(
						po -> builder.append("\t").append(po.toString()).append(System.getProperty("line.separator"))
						);
		builder.append("Estrategia Pago: ").append(estrategiaPago).append(System.getProperty("line.separator"));
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estaPreparado ? 1231 : 1237);
		result = prime * result + ((estrategiaPago == null) ? 0 : estrategiaPago.hashCode());
		result = prime * result + id;
		result = prime * result + ((mesa == null) ? 0 : mesa.hashCode());
		result = prime * result + ((ordenesPersonales == null) ? 0 : ordenesPersonales.hashCode());
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
		Orden other = (Orden) obj;
		if (estaPreparado != other.estaPreparado)
			return false;
		if (estrategiaPago != other.estrategiaPago)
			return false;
		if (id != other.id)
			return false;
		if (mesa == null) {
			if (other.mesa != null)
				return false;
		} else if (!mesa.equals(other.mesa))
			return false;
		if (ordenesPersonales == null) {
			if (other.ordenesPersonales != null)
				return false;
		} else if (!ordenesPersonales.equals(other.ordenesPersonales))
			return false;
		return true;
	}
	
	

}
