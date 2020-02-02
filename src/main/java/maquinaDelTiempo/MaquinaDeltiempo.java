package maquinaDelTiempo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MaquinaDeltiempo {

	private float relojGlobalAux = 0; //este se usara para ir creando los registros de la maquina del tiempo
	private long presente;//este servira para ir iterando los instantes de tiempo
	private ArrayList<RegistroMaquinaDelTiempo> registros; //todos los registros
	public static final Long TAMANO_INTERVALO = 1L; //el intervalo en el cual se graficaran los agentes
	
	
	public void ordenarMaquinaDelTiempo() {
		//se ordena toda la maquina del tiempo deacuerdo a los instantes de tiempo en los que ocurrieron las cosas 
		Collections.sort(registros, Comparator.comparing(RegistroMaquinaDelTiempo::getInstanteTiempoInicio));
	}
	
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
