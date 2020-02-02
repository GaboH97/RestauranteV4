package com.restaurante.app.global.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.restaurante.app.agentes.caja.Pago;

/**
 * 
 * @author Gabriel Huertas
 *
 */
public class DiaTrabajo {

	// ======================== ATTRIBUTES =================================

	private int id;
	private ArrayList<Orden> ordenes;
	
	private ArrayList<Pago> pagos;

	private List<ReporteOrdenesPlato> platosConNumeroVecesOrdenado;
	private List<ReporteCalificacionPlato> platosMejorCalificadosPorTipoPlato;
	private List<ReporteTipoPlatoOrdenado> numeroOrdenesPorEstrategiaPago;

	public DiaTrabajo(int id) {
		this.id = id;
		this.ordenes = new ArrayList<>();
		this.pagos = new ArrayList<>();
		platosConNumeroVecesOrdenado = new ArrayList<>();
		platosMejorCalificadosPorTipoPlato = new ArrayList<>();
		numeroOrdenesPorEstrategiaPago =  new ArrayList<>();
	}
	
	public void archivarOrden(Orden orden) {
		this.ordenes.add(orden);
	}
	
	public void archivarPago(Pago pago) {
		this.pagos.add(pago);
	}
	
	/**
	 * Método que retorna todos los platos ordenados en un día laboral.
	 * 
	 * @return
	 */
	private List<Plato> obtenerTodosPlatos() {
		List<Plato> todosPlatos = new ArrayList<>();
		this.ordenes.forEach((orden) -> {
			orden.getOrdenesPersonales().forEach((ordenPersonal) -> {
				todosPlatos.addAll(ordenPersonal.getPlatosOrdenados().stream().map(od -> od.getPlato())
						.collect(Collectors.toList()));
			});
		});
		return todosPlatos;
	}

	/**
	 * Método que genera las estadística con el plato y el número de veces que este
	 * fue ordenado
	 * 
	 * @return
	 */
	public Map<Plato, Long> obtenerPlatosConNumeroVecesOrdenado() {
		return obtenerTodosPlatos().stream().collect(Collectors.groupingBy(Function.identity(), // Agrupa por el plato
																								// ordenado
				Collectors.counting() // Cuenta la cantidad de veces que el plato está en el array
		));
	}

	/**
	 * 
	 * @return
	 */
	public Map<Plato, Long> obtenerPlatoMasVendidoPorTipoPlato() {

		Map<Plato, Long> platoMasVendidoPorTipoPlato = new HashMap<>();
		Map<Plato, Long> platosVendidos = obtenerPlatosConNumeroVecesOrdenado();

		// Obtiene los tipos de plato como arraylist

		Arrays.asList(TipoPlato.values()).forEach((TipoPlato tipoPlato) -> {

			// Filtra las entradas de los platos vendidos por el tipo de plato
			Map<Plato, Long> dishesPerType = platosVendidos.entrySet().stream()
					.filter(e -> e.getKey().getTipoPlato().equals(tipoPlato))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			// Obtiene la entrada con el registro del plato más vendido
			Map.Entry<Plato, Long> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

			platoMasVendidoPorTipoPlato.put(max.getKey(), max.getValue());

		});

		return platoMasVendidoPorTipoPlato;
	}

	/**
	 * 
	 * @return
	 */
	public Map<Plato, Double> obtenerPlatoMejorCalificadoPorTipoPlato() {
		Map<Plato, Double> bestSellingDishesPerDishType = new HashMap<>();
		Map<Plato, Double> dishesSold = obtenerPlatosConCalificacionPromedio();

		// Obtiene los tipos de plato como arraylist
		List<TipoPlato> dishTypes = Arrays.asList(TipoPlato.values());

		dishTypes.forEach((TipoPlato tipoPlato) -> {

			// Filtra las entradas de los platos vendidos por el tipo de plato
			Map<Plato, Double> dishesPerType = dishesSold.entrySet().stream()
					.filter((Map.Entry<Plato, Double> e) -> e.getKey().getTipoPlato().equals(tipoPlato))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			// Get best selling dish
			Map.Entry<Plato, Double> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

			bestSellingDishesPerDishType.put(max.getKey(), max.getValue());

		});

		return bestSellingDishesPerDishType;

	}

	/**
	 * 
	 * @return Un mapa con entradas por cada plato y su correspondiente calificación
	 *         promedio
	 */
	private Map<Plato, Double> obtenerPlatosConCalificacionPromedio() {
		List<PlatoOrdenado> allOrderedDishes = obtenerTodosPlatosOrdenados();
		Map<Plato, Double> platosConCalificacionPromedio = allOrderedDishes.stream().collect(Collectors.groupingBy(
				(PlatoOrdenado p) -> p.getPlato(), Collectors.averagingDouble(PlatoOrdenado::getCalificacion)));
		return platosConCalificacionPromedio;
	}

	/**
	 * 
	 * @return Una lista con todos los platos ordenados durante el día de trabajo
	 */
	private List<PlatoOrdenado> obtenerTodosPlatosOrdenados() {
		List<PlatoOrdenado> todosPlatosOrdenados = new ArrayList<>(1);
		ordenes.forEach((orden) -> {
			orden.getOrdenesPersonales().forEach((ordenPersonal) -> {
				todosPlatosOrdenados.addAll(ordenPersonal.getPlatosOrdenados());
			});
		});
		return todosPlatosOrdenados;
	}

	/**
	 *
	 * @return Numero de ordenes agrupadas por estrategia de pago
	 */
	public Map<EstrategiaPago, Long> obtenerNumeroOrdenesPorEstrategiaPago() {
		return ordenes.stream()
				.collect(Collectors.groupingBy((Orden o) -> o.getEstrategiaPago(), Collectors.counting()));
	}

	/**
	 * Método que genera las estadisticas solicitadas para cada día de trabajo
	 */
	public void obtenerEstadisticas() {
		this.platosMejorCalificadosPorTipoPlato = convertirAReporteCalificacionPlato(obtenerPlatoMejorCalificadoPorTipoPlato());
		this.platosConNumeroVecesOrdenado = convertirAReporteOrdenesPlato(obtenerPlatoMasVendidoPorTipoPlato());
		this.numeroOrdenesPorEstrategiaPago = convertirAReporteOrdenesPorEstrategiaPago(obtenerNumeroOrdenesPorEstrategiaPago());
	}

	/**
	 * Método que genera una lista de reportes de ordenes de plato
	 * 
	 * @param platosConNumeroVecesOrdenadoMap
	 * @return
	 */
	private List<ReporteOrdenesPlato> convertirAReporteOrdenesPlato(Map<Plato, Long> platosConNumeroVecesOrdenadoMap) {
		return platosConNumeroVecesOrdenadoMap.entrySet().stream()
				.map(e -> new ReporteOrdenesPlato(e.getKey(), e.getValue())).collect(Collectors.toList());
	}
	
	/**
	 * Método que genera una lista de reportes de ordenes de plato
	 * 
	 * @param platosConNumeroVecesOrdenadoMap
	 * @return
	 */
	private List<ReporteTipoPlatoOrdenado> convertirAReporteOrdenesPorEstrategiaPago(Map<EstrategiaPago, Long> ordenesPorEstrategiaPagoMap) {
		return ordenesPorEstrategiaPagoMap.entrySet().stream()
				.map(e -> new ReporteTipoPlatoOrdenado(e.getKey(), e.getValue())).collect(Collectors.toList());
	}
	
	/**
	 * Método que genera una lista de reportes de ordenes de plato
	 * 
	 * @param platosConNumeroVecesOrdenadoMap
	 * @return
	 */
	private List<ReporteCalificacionPlato> convertirAReporteCalificacionPlato(Map<Plato, Double> platosConNumeroVecesOrdenadoMap) {
		return platosConNumeroVecesOrdenadoMap.entrySet().stream()
				.map(e -> new ReporteCalificacionPlato(e.getKey(), e.getValue())).collect(Collectors.toList());
	}

	public Double getTotalGanancias() {
		return pagos.stream().collect(Collectors.summingDouble(Pago::getTotalOrden));
	}
	
	
	// ======================== GETTERS & SETTERS ===============================

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the ordenes
	 */
	public ArrayList<Orden> getOrdenes() {
		return ordenes;
	}

	/**
	 * @param ordenes the ordenes to set
	 */
	public void setOrdenes(ArrayList<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	/**
	 * @return the platosConNumeroVecesOrdenado
	 */
	public List<ReporteOrdenesPlato> getPlatosConNumeroVecesOrdenado() {
		return platosConNumeroVecesOrdenado;
	}

	/**
	 * @param platosConNumeroVecesOrdenado the platosConNumeroVecesOrdenado to set
	 */
	public void setPlatosConNumeroVecesOrdenado(List<ReporteOrdenesPlato> platosConNumeroVecesOrdenado) {
		this.platosConNumeroVecesOrdenado = platosConNumeroVecesOrdenado;
	}

	/**
	 * @return the platosMejorCalificadosPorTipoPlato
	 */
	public List<ReporteCalificacionPlato> getPlatosMejorCalificadosPorTipoPlato() {
		return platosMejorCalificadosPorTipoPlato;
	}

	/**
	 * @param platosMejorCalificadosPorTipoPlato the
	 *                                           platosMejorCalificadosPorTipoPlato
	 *                                           to set
	 */
	public void setPlatosMejorCalificadosPorTipoPlato(List<ReporteCalificacionPlato> platosMejorCalificadosPorTipoPlato) {
		this.platosMejorCalificadosPorTipoPlato = platosMejorCalificadosPorTipoPlato;
	}

	/**
	 * @return the numeroOrdenesPorEstrategiaPago
	 */
	public List<ReporteTipoPlatoOrdenado> getNumeroOrdenesPorEstrategiaPago() {
		return numeroOrdenesPorEstrategiaPago;
	}

	/**
	 * @param numeroOrdenesPorEstrategiaPago the numeroOrdenesPorEstrategiaPago to set
	 */
	public void setNumeroOrdenesPorEstrategiaPago(List<ReporteTipoPlatoOrdenado> numeroOrdenesPorEstrategiaPago) {
		this.numeroOrdenesPorEstrategiaPago = numeroOrdenesPorEstrategiaPago;
	}

	/**
	 * @return the pagos
	 */
	public ArrayList<Pago> getPagos() {
		return pagos;
	}

	/**
	 * @param pagos the pagos to set
	 */
	public void setPagos(ArrayList<Pago> pagos) {
		this.pagos = pagos;
	}
	
	
	
}
