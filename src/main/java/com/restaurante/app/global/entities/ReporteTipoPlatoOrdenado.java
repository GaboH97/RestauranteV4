package com.restaurante.app.global.entities;

public class ReporteTipoPlatoOrdenado {

	private EstrategiaPago estrategiaPago;
	private Long numeroVeces;
	
	public ReporteTipoPlatoOrdenado() {}
	
	public ReporteTipoPlatoOrdenado(EstrategiaPago estrategiaPago, Long numeroVeces) {
		this.estrategiaPago = estrategiaPago;
		this.numeroVeces = numeroVeces;
	}
	
	
	/**
	 * @return the estrategiaPago
	 */
	public EstrategiaPago getEstrategiaPago() {
		return estrategiaPago;
	}
	/**
	 * @param estrategiaPago the estrategiaPago to set
	 */
	public void setEstrategiaPago(EstrategiaPago estrategiaPago) {
		this.estrategiaPago = estrategiaPago;
	}
	/**
	 * @return the numeroVeces
	 */
	public Long getNumeroVeces() {
		return numeroVeces;
	}
	/**
	 * @param numeroVeces the numeroVeces to set
	 */
	public void setNumeroVeces(Long numeroVeces) {
		this.numeroVeces = numeroVeces;
	}
	
	
}
