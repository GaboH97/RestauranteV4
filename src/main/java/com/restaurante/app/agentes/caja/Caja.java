package com.restaurante.app.agentes.caja;

import java.util.ArrayList;
import java.util.Random;
import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.global.entities.EstrategiaPago;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;

public class Caja {

	
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
	 * Método que genera los pagos de acuerdo a la orden que entra por parámetro y en específico a
	 * su estrategia de pago, así:
	 * 
	 * En modo AMERICANO, se genera un pago por cada orden personal de cada cliente por el valor que cada cual haya consumido
	 * En modo TODOS POR TODO. se calcula el promedio a pagar entre todos los comensales y se agregan los respectivios pagos a la caja
	 * En modo UNO POR TODOS, se elige al azar un cliente que paga por el valor acumulado de todas las órdenes personales de los
	 * comensales y se registra un único pago 
	 * 
	 * @param orden
	 */
	public void pagar(Orden orden) {
		switch (orden.getEstrategiaPago()) {
		case AMERICANO:
			
			orden.getOrdenesPersonales()
			.stream()
			.map((OrdenPersonal po) -> generarPago(po.getClient(),
					po.obtenerPrecioTotal(), orden.getEstrategiaPago()))
			.forEach(pago -> agregarPago(pago));
			
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

	/**
	 * Método que agrega un pago a la lista de pagos
	 * @param pago
	 */
	public void agregarPago(Pago pago) {
		listaDePagos.add(pago);
	}
	
	public ArrayList<Pago> listaPagos() {
		return listaDePagos;
	}

	/**
	 * Método que selecciona al azar un cliente que va a pagar una orden (cuando la estrategia de pago sea UNO POR TODOS)
	 * @return
	 */
	public Cliente obtenerClienteAPagar(Orden orden) {
		return orden.getOrdenesPersonales().get(new Random().nextInt(orden.getOrdenesPersonales().size())).getClient();
	}
}
