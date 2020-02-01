package com.restaurante.app.agentes.mesero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.restaurante.app.global.entities.Orden;

@Component("managerMeseros")
public class ManagerMeseros {

	private ArrayList<Mesero> meseros;

	public ManagerMeseros() {
		meseros = new ArrayList<>();
		setUp();
	}
	
	/**
	 * 
	 * @param orden Orden a tomar
	 * @param idMesero ID del mesero
	 * @return Mesero que ha tomado la orden
	 */
	public Mesero agregarOrden(Orden orden, int idMesero) {
		Mesero mesero = buscarMeseroPorId(idMesero);
		if(mesero != null) {
			mesero.tomarOrden(orden);
		}
		return mesero;
	}
	
	
	/**
	 * 
	 * @return Algún mesero que esté disponible para tomar la orden
	 */
	public Mesero obtenerMeseroDisponible() {

		List<Mesero> meserosDisponibles = meseros.stream()
				.filter(m -> m.getEstado().equals(EstadoMesero.DISPONIBLE))
				.collect(Collectors.toList());
		return meserosDisponibles.get(new Random().nextInt(meserosDisponibles.size()));
	}

	/**
	 * Metodo que crea dos instancias de mesero
	 */
	private void setUp() {
		meseros.addAll(Arrays.asList(
				new Mesero("Pedro"),
				new Mesero("Camilo"))
				);
	}

	/**
	 * 
	 * @return Todas las ordenes que posee un mesero
	 */
	public ArrayList<Orden> obtenerOrdenes() {
		ArrayList<Orden> ordenes = new ArrayList<>();
		this.meseros.forEach(m -> ordenes.addAll(m.getOrdenes()));
		return ordenes;
	}

	public Mesero buscarMeseroPorId(int id) {
		return meseros.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Orden> obtenerOrdenesPorMesero(int idMesero) {
		return meseros.stream().filter(m-> m.getId()==idMesero).findFirst().orElse(null).getOrdenes();
	}
	
	

	// ======================= GETTERS & SETTERS ==============================

	public ArrayList<Mesero> getMeseros() {
		return meseros;
	}

	public void setMeseros(ArrayList<Mesero> meseros) {
		this.meseros = meseros;
	}
}
