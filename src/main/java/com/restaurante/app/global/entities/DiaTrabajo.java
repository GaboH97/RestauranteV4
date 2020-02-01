package com.restaurante.app.global.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @author Gabriel Huertas
 *
 */
public class DiaTrabajo {

	//======================== ATTRIBUTES =================================
	
	private int id;
	private static int WORKDAY_ID = 1;
	private ArrayList<Orden> ordenes;
	
	private Map<Plato, Long> platosConNumeroVecesOrdenado;
	private Map<Plato, Double> platosMejorCalificadosPorTipoPlato;
	private Map<EstrategiaPago, Long> numeroOrdenesPorEstrategiaPago;
	
	public DiaTrabajo() {
		platosConNumeroVecesOrdenado = new HashMap<Plato,Long>();
		platosMejorCalificadosPorTipoPlato = new HashMap<Plato,Double>();
		numeroOrdenesPorEstrategiaPago = new HashMap<EstrategiaPago,Long>();
	}
	
	 
	    /**
	     * Método que retorna todos los platos ordenados en un día laboral.
	     * @return
	     */
	    private List<Plato> obtenerTodosPlatos() {
	        List<Plato> todosPlatos = new ArrayList<>();
	        this.ordenes.forEach((orden) -> {
	            orden.getOrdenesPersonales().forEach((ordenPersonal) -> {
	            	todosPlatos.addAll(ordenPersonal.getPlatosOrdenados()
	                        .stream()
	                        .map(od -> od.getPlato())
	                        .collect(Collectors.toList()));
	            });
	        });
	        return todosPlatos;
	    }
	    
	    /**
	     * Método que genera las estadística con el plato y el número de veces que este fue ordenado
	     * 
	     * @return 
	     */
	    public Map<Plato, Long> obtenerPlatosConNumeroVecesOrdenado() {
	        return obtenerTodosPlatos().stream()
	                .collect(
	                        Collectors.groupingBy(
	                                Function.identity(), //Agrupa por el plato ordenado
	                                Collectors.counting() //Cuenta la cantidad de veces que el plato está en el array
	                        )
	                );
	    }
	    
	    /**
	     * 
	     * @return
	     */
	    public Map<Plato, Long> obtenerPlatoMasVendidoPorTipoPlato() {

	        Map<Plato, Long> platoMasVendidoPorTipoPlato = new HashMap<>();
	        Map<Plato, Long> platosVendidos = obtenerPlatosConNumeroVecesOrdenado();

	        //Obtiene los tipos de plato como arraylist
	        List<TipoPlato> tiposPlato = Arrays.asList(TipoPlato.values());

	        tiposPlato.forEach((TipoPlato tipoPlato) -> {

	            //Filtra las entradas de los platos vendidos por el tipo de plato
	            Map<Plato, Long> dishesPerType = platosVendidos.entrySet()
	                    .stream()
	                    .filter((Map.Entry<Plato, Long> e) -> e.getKey().getTipoPlato().equals(tipoPlato))
	                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	            //Obtiene la entrada con el registro del plato más vendido
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

	        //Obtiene los tipos de plato como arraylist
	        List<TipoPlato> dishTypes = Arrays.asList(TipoPlato.values());

	        dishTypes.forEach((TipoPlato tipoPlato) -> {

	            //Filtra las entradas de los platos vendidos por el tipo de plato
	            Map<Plato, Double> dishesPerType = dishesSold.entrySet()
	                    .stream()
	                    .filter((Map.Entry<Plato, Double> e) -> e.getKey().getTipoPlato().equals(tipoPlato))
	                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	            //Get best selling dish
	            Map.Entry<Plato, Double> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

	            bestSellingDishesPerDishType.put(max.getKey(), max.getValue());

	        });

	        return bestSellingDishesPerDishType;

	    }
	    
	    /**
	     * 
	     * @return Un mapa con entradas por cada plato y su correspondiente calificación promedio
	     */
	    private Map<Plato, Double> obtenerPlatosConCalificacionPromedio() {
	        List<PlatoOrdenado> allOrderedDishes = obtenerTodosPlatosOrdenados();
	        Map<Plato, Double> platosConCalificacionPromedio = allOrderedDishes
	                .stream()
	                .collect(
	                        Collectors.groupingBy(
	                                (PlatoOrdenado p) -> p.getPlato(),
	                                Collectors.averagingDouble(PlatoOrdenado::getCalificacion)));
	        return platosConCalificacionPromedio;
	    }
	    
	    /**
	     * 
	     * @return Una lista con todos los platos ordenados durante el día de trabajo
	     */
	    private List<PlatoOrdenado> obtenerTodosPlatosOrdenados() {
	        List<PlatoOrdenado> todosPlatosOrdenados = new ArrayList<>(1);
	        ordenes.forEach((orden) -> {
	        	orden.getOrdenesPersonales().forEach(
	        			(ordenPersonal) -> {
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
	        return ordenes.stream().collect(Collectors.groupingBy((Orden o) -> o.getEstrategiaPago(), Collectors.counting()));
	    }
	    
	    /**
	     *	Método que genera las estadisticas solicitadas para cada día de trabajo
	     */
	    public void obtenerEstadisticas() {
	        this.platosMejorCalificadosPorTipoPlato = obtenerPlatoMejorCalificadoPorTipoPlato();
	        this.platosConNumeroVecesOrdenado = obtenerPlatosConNumeroVecesOrdenado();
	        this.numeroOrdenesPorEstrategiaPago = obtenerNumeroOrdenesPorEstrategiaPago();
	    }
}
