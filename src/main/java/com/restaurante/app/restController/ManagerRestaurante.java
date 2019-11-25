package com.restaurante.app.restController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.Plato;
import com.restaurante.app.global.entities.PlatoOrdenado;
import com.restaurante.app.global.entities.TipoPlato;

public class ManagerRestaurante {
	
	private ArrayList<Orden> historialOrdenes;
	
	private static ManagerRestaurante instance;
	
	private ManagerRestaurante() {
		historialOrdenes = new ArrayList<>();
	}
	
	public static ManagerRestaurante getInstance() {
		if(instance!=null) {
			instance = new ManagerRestaurante();
		}
		return instance;
	}
	
	public void agregarOrdenAHistorial(Orden orden) {
		this.historialOrdenes.add(orden);
	}

	
	public ArrayList<Orden> getHistorialOrdenes() {
		return historialOrdenes;
	}

	public void setHistorialOrdenes(ArrayList<Orden> historialOrdenes) {
		this.historialOrdenes = historialOrdenes;
	}
	
	public void generarEstadisticas() {
		
	}
	
	/**
    *
    * @return
    */
   public Map<Plato, Double> obtenerPlatosMejorCalificadosPorTipoPlato() {
       Map<Plato, Double> platosMejorCalificadosPorTipoPlato = new HashMap<>();
       Map<Plato, Double> platosOrdenados = obtenerPlatosConCalificacionPromedio();

       //Get Dish Type values
       List<TipoPlato> dishTypes = Arrays.asList(TipoPlato.values());

       dishTypes.forEach((TipoPlato tipoPlato) -> {

           //Filter Map entries by dish type
           Map<Plato, Double> dishesPerType = platosOrdenados.entrySet()
                   .stream()
                   .filter((Map.Entry<Plato, Double> e) -> e.getKey().getTipoPlato().equals(tipoPlato))
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

           //Get best selling dish
           Map.Entry<Plato, Double> max = Collections.max(dishesPerType.entrySet(), Map.Entry.comparingByValue());

           platosMejorCalificadosPorTipoPlato.put(max.getKey(), max.getValue());

       });

       return platosMejorCalificadosPorTipoPlato;

   }
   
   /**
    * Método que obtiene los platos con su calificación promedio
    * @return
    */
   private Map<Plato, Double> obtenerPlatosConCalificacionPromedio() {
       List<PlatoOrdenado> todosPlatosOrdenados = getAllOrderedDishes();
       Map<Plato, Double> dishesWithAverageRate = todosPlatosOrdenados
               .stream()
               .collect(
                       Collectors.groupingBy(
                               PlatoOrdenado::getPlato,
                               Collectors.averagingDouble(PlatoOrdenado::getCalificacion)));
       return dishesWithAverageRate;
   }
   
   /**
    * Métodos que retorna una lista con todos los platos ordenados desde el inicio de la simulación
    * @return 
    */
   private List<PlatoOrdenado> getAllOrderedDishes() {
       List<PlatoOrdenado> platosOrdenados = new ArrayList();
       historialOrdenes.forEach((orden) -> {
           orden.getOrdenesPersonales().forEach((ordenPersonal) -> {
        	   platosOrdenados.addAll(ordenPersonal.getPlatosOrdenados());
           });
       });
       return platosOrdenados;
   }

	
}
