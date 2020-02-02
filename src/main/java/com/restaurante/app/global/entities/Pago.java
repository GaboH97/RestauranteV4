/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.app.global.entities;

/**
 *
 * @author Lenovo
 */
public class Pago {

	private int id;
	private Cliente cliente;
	private EstrategiaPago estrategiaPago;
	private double totalOrden;
	private TipoPago tipoPago;

	public Pago(int id,Cliente cliente, double orderTotal, EstrategiaPago estrategiaPago) {
		this.id = id;
		this.cliente = cliente;
		this.totalOrden = orderTotal;
		this.estrategiaPago = estrategiaPago;
		this.tipoPago = (Math.random() > 0.5) ? TipoPago.EFECTIVO : TipoPago.TARJETA_CREDITO;
	}
	
	public Pago() {
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
