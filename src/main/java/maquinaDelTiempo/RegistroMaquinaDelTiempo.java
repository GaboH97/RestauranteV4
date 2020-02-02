package maquinaDelTiempo;

/**
 * Clase que determina los registros de la maquina del tiempo en los cuales se determina
 * que tipo de agente se va a pintar, por cuanto tiempo y el estado del agente.
 * @author Cesar
 *
 */
public class RegistroMaquinaDelTiempo {
	
	private Identificadores identificador; //servira para saber identificar el tipo de agente 
	private float instanteTiempoInicio; //establece cuando se empezara a pintar el agente
	private float instanteTiempoFin;//establece cuando se dejara de pintar el agente
	private Object estadoDelAgente;//establece el estado del agente, esto para saber como debe ser pintado si es que tiene que ser pintado
	
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