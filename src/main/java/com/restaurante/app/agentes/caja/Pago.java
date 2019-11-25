/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.app.agentes.caja;

import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.global.entities.EstrategiaPago;

/**
 *
 * @author Lenovo
 */
public class Pago {

	private static int CONT_PAGO = 1;
	private int id;
	private Cliente cliente;
	private EstrategiaPago estrategiaPago;
	private double totalOrden;
	private TipoPago tipoPago;

	public Pago(Cliente cliente, double orderTotal, EstrategiaPago estrategiaPago) {
		this.id = CONT_PAGO++;
		this.cliente = cliente;
		this.totalOrden = orderTotal;
		this.estrategiaPago = estrategiaPago;
		this.tipoPago = (Math.random() > 0.5) ? TipoPago.EFECTIVO : TipoPago.TARJETA_CREDITO;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotalOrden() {
		return totalOrden;
	}

	public void setTotalOrden(double orderTotal) {
		this.totalOrden = orderTotal;
	}

	public EstrategiaPago getEstrategiaPago() {
		return estrategiaPago;
	}

	public void setEstrategiaPago(EstrategiaPago estrategiaPago) {
		this.estrategiaPago = estrategiaPago;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}
}