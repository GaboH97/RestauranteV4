package com.restaurante.app.global.entities;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurante.app.global.config.Identificadores;
import com.restaurante.app.global.config.MaquinaDelTiempo;

/**
 *
 * @author Lenovo
 */
public class Cliente {

	private int id;
	private String nombre;
	private int tiempoLLegada;
	/**
	 * Atributo que representa el tiempo de consumo del cliente.
	 */
	private int tiempoConsumo;

	public Cliente() {
	}

	public Cliente(int id, int arrivalTime) {
		this.id = id;
		this.nombre = "Cliente " + id;
		this.tiempoLLegada = arrivalTime;
		this.tiempoConsumo = (int) (Math.random() * 11) + 20;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	/**
	 *
	 * @return a list of dishes which represent a personal order following a set of
	 *         rules The customer always consumes a main course The client can
	 *         request a maximum of one entry and / or two desserts in their
	 *         respective order.
	 */
	public ArrayList<Plato> seleccionarPlatosPorCliente() {

		ArrayList<Plato> platoPersonalSeleccionado = new ArrayList<>();

		// Get a main Plato (Mandatory)
		ArrayList<Plato> mainDishes = Carta.getDishesByType(TipoPlato.PLATO_FUERTE);
		Plato mainDish = mainDishes.get(new Random().nextInt(mainDishes.size()));
		platoPersonalSeleccionado.add(mainDish);

		boolean entreeAndDessert = Math.random() < 0.5;

		ArrayList<Plato> entreeDishes = Carta.getDishesByType(TipoPlato.ENTRADA);
		ArrayList<Plato> dessertDishes = Carta.getDishesByType(TipoPlato.POSTRE);

		/**
		 * Complete menu with one of these two options
		 *
		 * 1. One entreé and up to two desserts 2. One entreé or up to two desserts
		 */
		if (entreeAndDessert) {

			Plato entreeDish = entreeDishes.get(new Random().nextInt(entreeDishes.size()));
			platoPersonalSeleccionado.add(entreeDish);

			for (int i = 0; i < new Random().nextInt(2) + 1; i++) {
				Plato desserDish = dessertDishes.get(new Random().nextInt(dessertDishes.size()));
				platoPersonalSeleccionado.add(desserDish);
			}

		} else {
			boolean ordersEntree = Math.random() < 0.5;
			if (ordersEntree) {
				Plato PlatoEntrada = entreeDishes.get(new Random().nextInt(entreeDishes.size()));
				platoPersonalSeleccionado.add(PlatoEntrada);
			} else {
				for (int i = 0; i < new Random().nextInt(2) + 1; i++) {
					Plato desserDish = dessertDishes.get(new Random().nextInt(dessertDishes.size()));
					platoPersonalSeleccionado.add(desserDish);
				}
			}
		}
		return platoPersonalSeleccionado;
	}

//    public int getAttentionTime() {
//        return attentionTime;
//    }
//
//    public void setAttentionTime(int attentionTime) {
//        this.attentionTime = attentionTime;
//    }
//
//    public int getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public void setArrivalTime(int arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//    
//    public int getStartAttentionTime() {
//        return startAttentionTime;
//    }
//
//    public void setStartAttentionTime(int startAttentionTime) {
//        this.startAttentionTime = startAttentionTime;
//    }    

	public int getTiempoLLegada() {
		return tiempoLLegada;
	}

	public int getTiempoConsumo() {
		return tiempoConsumo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
