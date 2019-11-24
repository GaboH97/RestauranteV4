package com.restaurante.app.agentes.mesero;

import java.util.ArrayList;

import com.restaurante.app.global.entities.Orden;

/**
 * 
 * @author Milton Quintero
 *
 */
public class Mesero {

	public static final int TIEMPO_DESCANSO = 10; // In minutes
	public static int MESERO_COUNT = 1;

	private int id;
	private String name;
	private EstadoMesero estado;
	private ArrayList<Orden> ordenes;

	public Mesero(String name) {
		this.id = MESERO_COUNT++;
		this.name = name;
		//this.serviceRatesPerWorkDay = new HashMap<>();
		this.ordenes = new ArrayList<>();
		this.estado = EstadoMesero.DISPONIBLE;
	}
	
	public Mesero() {
	}

	public void attendTables() {
		// Cambia el estado del mesero a atentiendo
		attend();
	}

	/**
	 *
	 * @return true if the waiter has only taken 3 orders, otherwise false
	 */
	public boolean puedeTomarOrden() {
		return ordenes.size() < 3;
	}
	
	public void atender() {
		estado = EstadoMesero.ATENDIENDO;
	}
	
	public void descansar() {
		estado = EstadoMesero.DESCANSANDO;
	}

	/**
	 *
	 * @param orden
	 */
	public void tomarOrden(Orden orden) {
		ordenes.add(orden);
	}

//	public void addServiceRate(WorkDay workday, Integer serviceRate) {
//		if (serviceRatesPerWorkDay.containsKey(workday)) {
//			serviceRatesPerWorkDay.get(workday).add(serviceRate);
//		} else {
//			serviceRatesPerWorkDay.put(workday, new ArrayList<>());
//		}
//	}
//
//	public Map<WorkDay, Double> getTotalIncomeWorkDay() {
//		Map<WorkDay, Double> totalIncomeWorkDay = new TreeMap<>((o1, o2) -> o1.getId() > o2.getId() ? 1 : -1);
//		serviceRatesPerWorkDay.entrySet()
//				.forEach(e -> totalIncomeWorkDay.put(e.getKey(), getAverageRateOfWorkDay(e.getKey())));
//		return totalIncomeWorkDay;
//	}
//
//	/**
//	 *
//	 * @param workDay
//	 * @return Average service rate of this waiter
//	 */
//	public Double getAverageRateOfWorkDay(WorkDay workDay) {
//		return serviceRatesPerWorkDay.get(workDay).stream().mapToInt(r -> r).summaryStatistics().getAverage();
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Map<WorkDay, ArrayList<Integer>> getServiceRatesPerWorkDay() {
//		return serviceRatesPerWorkDay;
//	}
//
//	public void setServiceRatesPerWorkDay(Map<WorkDay, ArrayList<Integer>> serviceRatesPerWorkDay) {
//		this.serviceRatesPerWorkDay = serviceRatesPerWorkDay;
//	}

	public ArrayList<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(ArrayList<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public EstadoMesero getEstado() {
		return estado;
	}

	public void setEstado(EstadoMesero estado) {
		this.estado = estado;
	}

	public void attend() {
		setEstado(EstadoMesero.ATENDIENDO);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Taken Orders").append(System.getProperty("line.separator"));
		ordenes
				.forEach(o -> builder.append("\t").append(o.toString()).append(System.getProperty("line.separator")));
		return builder.toString();
	}

}
