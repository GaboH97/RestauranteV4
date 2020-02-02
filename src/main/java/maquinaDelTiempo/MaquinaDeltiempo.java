package maquinaDelTiempo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * clase master de los registros de instantes de tiempo en donde se consignaran cuando ocurren los cambios de estados de todos 
 * los agentes 
 * @author Cesar
 *
 */
public class MaquinaDeltiempo {

//	private float relojGlobalAux = 0; //este se usara para ir creando los registros de la maquina del tiempo
	private long presente;//este servira para ir iterando los instantes de tiempo
	private ArrayList<RegistroMaquinaDelTiempo> registros; //todos los registros
	public static final Long TAMANO_INTERVALO = 1L; //el intervalo en el cual se graficaran los agentes
	
	
	/**
	 * metodo que ordena los registros de instantes de tiempo segun el orden de aparicion
	 */
	public void ordenarMaquinaDelTiempo() {
		//se ordena toda la maquina del tiempo deacuerdo a los instantes de tiempo en los que ocurrieron las cosas 
		Collections.sort(registros, Comparator.comparing(RegistroMaquinaDelTiempo::getInstanteTiempoInicio));
	}
	
	/**
	 * metodo que agrega un registro de instante de tiempo 
	 */
	public void agregarRegistro(Identificadores identificador, float instanteTiempoInicio,
			float duracion, Object estadoDelAgente) {
		this.registros.add(new RegistroMaquinaDelTiempo(identificador, instanteTiempoInicio, duracion, estadoDelAgente));
	}
	
	/**
	 * Cada vez que se llame este metodo se obtendran los datos que hay que pintar y se avanzara el reloj
	 * al siguiente intervalo de tiempo para cuando se vuelva a llamar...
	 * @return
	 */
	public ArrayList<RegistroMaquinaDelTiempo> avanzarIntervaloDeTiempo() {
		ArrayList<RegistroMaquinaDelTiempo> registrosEnElIntervalo = new ArrayList<>();
		for (RegistroMaquinaDelTiempo registro : registros) {
			if (registro.getInstanteTiempoInicio() <= presente && presente >= registro.getInstanteTiempoFin()) {
				registrosEnElIntervalo.add(registro);
			}
		}
		presente+=TAMANO_INTERVALO;
		return registrosEnElIntervalo;
	}
}