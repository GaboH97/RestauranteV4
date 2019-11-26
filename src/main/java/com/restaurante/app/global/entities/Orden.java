package com.restaurante.app.global.entities;

import java.util.ArrayList;
import java.util.Random;

import com.restaurante.app.agentes.cliente.Cliente;

import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.Mesero;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Orden {

	private static int ORDEN_COUNT = 1;
	private int id;
	private Mesa mesa;
	private ArrayList<OrdenPersonal> ordenesPersonales;
	private EstrategiaPago estrategiaPago;
	private boolean estaPreparado;

	public Orden() {
		this.id = ORDEN_COUNT++;
		this.ordenesPersonales = new ArrayList<>();
		this.estrategiaPago = escogerEstrategiaPago();
	}

	public Orden(Mesero mesero) {
		this.id = ORDEN_COUNT++;
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
		builder.append("Mesa: ").append(mesa).append(System.getProperty("line.separator")).append("Personal Orders: ")
				.append(System.getProperty("line.separator"));

		ordenesPersonales
				.forEach(po -> builder.append("\t").append(po.toString()).append(System.getProperty("line.separator")));
		builder.append("Estrategia Pago: ").append(estrategiaPago).append(System.getProperty("line.separator"));
		return builder.toString();
	}

}
