package com.restaurante.app.global.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Component;

/**
 * Clase master donde se tiene registro de los sucesos de todos los agentes del sistema y es la que permitirá
 * realizar la animación ya que obtendrá los agentes
 * 
 * @author Cesar
 *
 */
@Component
public class MaquinaDelTiempo {

	// Este servira para ir iterando los instantes de tiempo
	private long presente;
	// todos los registros
	private ArrayList<RegistroMaquinaDelTiempo> registros; 
	// el intervalo en el cual se graficaran los agentes
	public static final Long DELTA_TIEMPO = 10L; 
	
	public MaquinaDelTiempo() {
		presente = 0L;
		registros = new ArrayList<>();
	}

	/**
	 * metodo que ordena los registros de instantes de tiempo segun el orden de
	 * aparicion
	 */
	public void ordenarMaquinaDelTiempo() {
		// se ordena toda la maquina del tiempo deacuerdo a los instantes de tiempo en
		// los que ocurrieron las cosas
		Collections.sort(registros, Comparator.comparing(RegistroMaquinaDelTiempo::getInstanteTiempoInicio));
	}

	/**
	 * metodo que agrega un registro de instante de tiempo
	 */
	public void agregarRegistro(Identificadores identificador, float instanteTiempoInicio, float duracion,
			Object estadoDelAgente) {
		this.registros
				.add(new RegistroMaquinaDelTiempo(identificador, instanteTiempoInicio, duracion, estadoDelAgente));
	}

	/**
	 * Cada vez que se llame este metodo se obtendran los datos que hay que pintar y
	 * se avanzara el reloj al siguiente intervalo de tiempo para cuando se vuelva a
	 * llamar...
	 * 
	 * @return
	 */
	public ArrayList<RegistroMaquinaDelTiempo> avanzarIntervaloDeTiempo() {
		ArrayList<RegistroMaquinaDelTiempo> registrosEnElIntervalo = new ArrayList<>();
		for (RegistroMaquinaDelTiempo registro : registros) {
			if (registro.getInstanteTiempoInicio() <= presente && presente >= registro.getInstanteTiempoFin()) {
				registrosEnElIntervalo.add(registro);
			}
		}
		presente += DELTA_TIEMPO;
		return registrosEnElIntervalo;
	}
}