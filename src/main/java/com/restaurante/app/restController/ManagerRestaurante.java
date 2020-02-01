package com.restaurante.app.restController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.restaurante.app.global.entities.DiaTrabajo;
import com.restaurante.app.global.entities.EstrategiaPago;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.Plato;
import com.restaurante.app.global.entities.PlatoOrdenado;
import com.restaurante.app.global.entities.TipoPlato;


/**
 * 
 * @author Gabriel Huertas
 *
 */
public class ManagerRestaurante {

	
	private ArrayList<DiaTrabajo> diasTrabajo;
	
	private ArrayList<Orden> historialOrdenes;

	private Map<Plato, Double> platosMejorCalificadosPorTipoPlato;

	private Map<EstrategiaPago, Long> numeroOrdenesPorEstrategiaPago;

	private static ManagerRestaurante instance;

	private ManagerRestaurante() {
		this.historialOrdenes = new ArrayList<>();
		this.platosMejorCalificadosPorTipoPlato = new HashMap<>();
		this.numeroOrdenesPorEstrategiaPago = new HashMap<>();
	}

	public static ManagerRestaurante getInstance() {
		if (instance == null) {
			instance = new ManagerRestaurante();
		}
		return instance;
	}

	public void agregarOrdenAHistorial(Orden orden) {
		this.historialOrdenes.add(orden);
	}
	
 //======================== STATISTICS ===========================
    
	/**
	 * 
	 */
    public List<Map<Plato, Long>> getBestSellingDishesPerDishType() {
        return diasTrabajo.stream().map(wd -> wd.obtenerPlatoMasVendidoPorTipoPlato()).collect(Collectors.toList());
    }

    /**
     * 
     * @return
     */
    public List<Map<Plato, Double>> getBestRatedDishesPerDishType() {
        return diasTrabajo.stream().map(wd -> wd.obtenerPlatoMejorCalificadoPorTipoPlato()).collect(Collectors.toList());
    }

    /**
     * 
     * @return
     */
    public List<Map<EstrategiaPago, Long>> getCountOfOrdersByPaymentStrategy() {
        return diasTrabajo.stream().map(wd -> wd.obtenerNumeroOrdenesPorEstrategiaPago()).collect(Collectors.toList());
    }
	

	/**
	 * Método que genera las estadísticas solicitadas
	 */
	public void generarEstadisticas() {
//		this.platosMejorCalificadosPorTipoPlato = obtenerPlatosMejorCalificadosPorTipoPlato();
//		this.cantidadVecesPlatoOrdenado = obtenerCantidadVecesPlatoOrdenado();
//		this.numeroOrdenesPorEstrategiaPago = obtenerNumeroOrdenesPorEstrategiaPago();
	}

	/**
	 * Método que obtiene los platos, en promedio, mejor calificados de acuerdo al
	 * tipo de plato
	 * 
	 * @return
	 */
	private Map<Plato, Double> obtenerPlatosMejorCalificadosPorTipoPlato() {
		
		Map<Plato, Double> platosMejorCalificadosPorTipoPlato = new HashMap<>();
		Map<Plato, Double> platosOrdenados = obtenerPlatosConCalificacionPromedio();

		// Get Dish Type values
		List<TipoPlato> dishTypes = Arrays.asList(TipoPlato.values());

		dishTypes.forEach((TipoPlato tipoPlato) -> {

			// Filter Map entries by dish type
			Map<Plato, Double> dishesPerType = platosOrdenados.entrySet().stream()
					.filter((Map.Entry<Plato, Double> e) -> e.getKey().getTipoPlato().equals(tipoPlato))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			
			// Get best selling dish
			Map.Entry<Plato, Double> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

			platosMejorCalificadosPorTipoPlato.put(max.getKey(), max.getValue());

		});

		return platosMejorCalificadosPorTipoPlato;
	}

	/**
	 * Metodo que obtiene los platos que se han pedido con la cantidad de veces que
	 * lo han sido
	 *
	 * @return
	 */
	public Map<String, Long> obtenerCantidadVecesPlatoOrdenado() {
		return obtenerTodosPlatos().stream().collect(Collectors.groupingBy(Plato::getNombre, Collectors.counting()));
	}

	/**
	 * Método que obtiene los platos con su respectiva calificación promedio
	 * 
	 * @return
	 */
	private Map<Plato, Double> obtenerPlatosConCalificacionPromedio() {
		List<PlatoOrdenado> todosPlatosOrdenados = obtenerTodosPlatosOrdenados();
		Map<Plato, Double> dishesWithAverageRate = todosPlatosOrdenados.stream().collect(Collectors
				.groupingBy(PlatoOrdenado::getPlato, Collectors.averagingDouble(PlatoOrdenado::getCalificacion)));
		return dishesWithAverageRate;
	}

	/**
	 * Métodos que retorna una lista con todos los platos ordenados desde el inicio
	 * de la simulación
	 * 
	 * @return
	 */
	private List<PlatoOrdenado> obtenerTodosPlatosOrdenados() {
		List<PlatoOrdenado> platosOrdenados = new ArrayList<PlatoOrdenado>();
		historialOrdenes.forEach((orden) -> {
			orden.getOrdenesPersonales().forEach((ordenPersonal) -> {
				platosOrdenados.addAll(ordenPersonal.getPlatosOrdenados());
			});
		});
		return platosOrdenados;
	}

	/**
	 * Método que retorna todos los platos que han sido ordenados, sin su
	 * calificación
	 * 
	 * @return
	 */
	private List<Plato> obtenerTodosPlatos() {
		return obtenerTodosPlatosOrdenados().stream().map(platoOrdenado -> platoOrdenado.getPlato())
				.collect(Collectors.toList());
	}

	/**
	 *
	 * Método que retorna el numero de ordenes por estrategia de pago
	 * 
	 * @return
	 */
	public Map<EstrategiaPago, Long> obtenerNumeroOrdenesPorEstrategiaPago() {
		return historialOrdenes.stream()
				.collect(Collectors.groupingBy(Orden::getEstrategiaPago, Collectors.counting()));
	}

	// ===================== GETTERS & SETTERS ============================

	public ArrayList<Orden> getHistorialOrdenes() {
		return historialOrdenes;
	}

	public void setHistorialOrdenes(ArrayList<Orden> historialOrdenes) {
		this.historialOrdenes = historialOrdenes;
	}

	public Map<Plato, Double> getPlatosMejorCalificadosPorTipoPlato() {
		return platosMejorCalificadosPorTipoPlato;
	}

	public void setPlatosMejorCalificadosPorTipoPlato(Map<Plato, Double> platosMejorCalificadosPorTipoPlato) {
		this.platosMejorCalificadosPorTipoPlato = platosMejorCalificadosPorTipoPlato;
	}

	public Map<EstrategiaPago, Long> getNumeroOrdenesPorEstrategiaPago() {
		return numeroOrdenesPorEstrategiaPago;
	}

	public void setNumeroOrdenesPorEstrategiaPago(Map<EstrategiaPago, Long> numeroOrdenesPorEstrategiaPago) {
		this.numeroOrdenesPorEstrategiaPago = numeroOrdenesPorEstrategiaPago;
	}
	
	public ArrayList<DiaTrabajo> getDiasTrabajo() {
		return diasTrabajo;
	}
	
	public void setDiasTrabajo(ArrayList<DiaTrabajo> diasTrabajo) {
		this.diasTrabajo = diasTrabajo;
	}
}
