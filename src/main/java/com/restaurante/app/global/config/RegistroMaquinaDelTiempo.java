package com.restaurante.app.global.config;

/**
 * Clase que determina los registros de la maquina del tiempo en los cuales se determina
 * qué tipo de agente se va a pintar, por cuánto tiempo y el estado del agente.
 * 
 * @author Cesar
 *
 */
public class RegistroMaquinaDelTiempo {
	
	//servira para saber identificar el tipo de agente
	private Identificadores identificador;
	//establece cuando se empezara a pintar el agente
	private float instanteTiempoInicio; 
	//establece cuando se dejara de pintar el agente
	private float instanteTiempoFin;
	//establece el estado del agente, esto para saber como debe ser pintado si es que tiene que ser pintado
	private Object estadoDelAgente;
	
	public RegistroMaquinaDelTiempo(Identificadores identificador, float instanteTiempoInicio,
			float duracion, Object estadoDelAgente) {
		this.identificador = identificador;
		this.instanteTiempoInicio = instanteTiempoInicio;
		this.instanteTiempoFin = this.instanteTiempoInicio + duracion;
		this.estadoDelAgente = estadoDelAgente;
	}
	
	public Identificadores getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Identificadores identificador) {
		this.identificador = identificador;
	}
	public float getInstanteTiempoInicio() {
		return instanteTiempoInicio;
	}
	public void setInstanteTiempoInicio(float instanteTiempoInicio) {
		this.instanteTiempoInicio = instanteTiempoInicio;
	}
	public float getInstanteTiempoFin() {
		return instanteTiempoFin;
	}
	public void setInstanteTiempoFin(float instanteTiempoFin) {
		this.instanteTiempoFin = instanteTiempoFin;
	}

	public Object getEstadoDelAgente() {
		return estadoDelAgente;
	}

	public void setEstadoDelAgente(Object estadoDelAgente) {
		this.estadoDelAgente = estadoDelAgente;
	}
}