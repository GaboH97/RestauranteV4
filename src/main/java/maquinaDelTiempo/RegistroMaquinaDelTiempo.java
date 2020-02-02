package maquinaDelTiempo;

public class RegistroMaquinaDelTiempo {
	
	private Identificadores identificador;
	private float instanteTiempoInicio;
	private float instanteTiempoFin;
	private long  idMesa;
	
	public RegistroMaquinaDelTiempo(Identificadores identificador, float instanteTiempoInicio,
			float duracion) {
		super();
		this.identificador = identificador;
		this.instanteTiempoInicio = instanteTiempoInicio;
		this.instanteTiempoFin = this.instanteTiempoInicio + duracion;
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
}
