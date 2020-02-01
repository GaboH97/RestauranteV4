package com.restaurante.app.agentes.caja;

import java.util.ArrayList;
import java.util.Random;
import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.global.entities.EstrategiaPago;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;

public class Caja {

	public Orden orden;
	public ArrayList<Pago> listaDePagos;
 
	public Caja() {
		listaDePagos = new ArrayList<>();
	}

	private static Caja caja;

	// metodo que permite obtener una instancia de la clase
	public static Caja getInstance() {
		if (caja == null) {
			caja = new Caja();
		}
		return caja;
	}

	/**
	 * total a pagar por pedido o mesa
	 */
	public double TotalAPagarPorMesa() {
		return orden.obtenerTotal();
	}

	/**
	 *
	 * @param cliente
	 * @param orderTotal
	 * @param estrategiaPago
	 * @return
	 */
	public Pago generarPago(Cliente cliente, double orderTotal, EstrategiaPago estrategiaPago) {
		return new Pago(cliente, orderTotal, estrategiaPago);
	}

	/**
	 * // * Method that simulates an order payment according to an specific strategy
	 * // * // * AMERICANO -> American Way TODOS_POR_TODO - All-for-Everything
	 * UNO_POR_TODOS - One-for-everything // * // * @param workDay // * @param cash
	 * //
	 */
	public void pagar(Orden orden) {
		switch (orden.getEstrategiaPago()) {
		case AMERICANO:
			orden.getOrdenesPersonales().stream().map((OrdenPersonal po) -> generarPago(po.getClient(),
					po.obtenerPrecioTotal(), orden.getEstrategiaPago())).forEach(pago -> agregarPago(pago));
			break;
		case TODOS_POR_TODO:
			double pagoPorCliente = orden.obtenerPromedioPagar();
			orden.getOrdenesPersonales().stream()
					.map((OrdenPersonal po) -> generarPago(po.getClient(), pagoPorCliente, orden.getEstrategiaPago()))
					.forEach(pa -> agregarPago(pa));
			break;
		case UNO_POR_TODOS:
			Cliente clienteAPagar = obtenerClienteAPagar(orden);
			Pago pago = generarPago(clienteAPagar, orden.obtenerTotal(), orden.getEstrategiaPago());
			agregarPago(pago);
			break;
		}
	}

	public void agregarPago(Pago pago) {
		listaDePagos.add(pago);
	}

	public ArrayList<Pago> listaPagos() {
		return listaDePagos;
	}

	/**
	 *
	 * @return
	 */
	public Cliente obtenerClienteAPagar(Orden orden) {
		return orden.getOrdenesPersonales().get(new Random().nextInt(orden.getOrdenesPersonales().size())).getClient();
	}
}
