package com.restaurante.app.agentes.cocina;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.restaurante.app.agentes.cliente.Cliente;
import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.global.config.OrderSequence;
import com.restaurante.app.global.entities.Carta;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;
import com.restaurante.app.global.entities.Plato;
import com.restaurante.app.global.entities.TipoPlato;

public class GestionarCocina {

	private Cocina cocina;
	private int tiempoLlegada;
	private static GestionarCocina instance;

	// metodo que permite obtener una instancia de la clase
	public static GestionarCocina getInstance() {
		if (instance == null) {
			instance = new GestionarCocina();
		}
		return instance;
	}

	public GestionarCocina() {
		this.cocina = new Cocina();
		this.tiempoLlegada = 0;
	}

	public ArrayList<Orden> obtenerOrenesCocinadas() {
		// generarOrdenesPseudoaleatorias();
		this.cocina.cocinar();
		return this.cocina.getListaOrdenesPreparadas();
	}

	public ArrayList<Orden> agregarOrdenesACocinar(ArrayList<Orden> ordenesACocinar) {
		this.cocina.setListaOrdenesAPreparar(ordenesACocinar);
		return cocina.getListaOrdenesAPreparar();
	}

	public ArrayList<Orden> agregarOrdenAPreparar(Orden nuevaOrden) {
		this.cocina.agregarOrdenALaCola(nuevaOrden);
		return this.cocina.getListaOrdenesAPreparar();
	}

	public void generarOrdenesPseudoaleatorias() {
		this.cocina.agregarOrdenALaCola(generarOrden());
		this.cocina.agregarOrdenALaCola(generarOrden());
		this.cocina.agregarOrdenALaCola(generarOrden());
		this.cocina.cocinar();
	}

	/**
	 * Metodo encargado de generar la orden y retornarla
	 * 
	 * @return
	 */
	public Orden generarOrden() {
		Orden orden = new Orden(OrderSequence.ID_GENERATOR.getAndIncrement());
		orden.setPersonalOrders(crearOrden(tiempoLlegada()));
		return orden;
	}

	public ArrayList<Plato> seleccionarPlatosPorCliente() {
		ArrayList<Plato> platoPersonalSeleccionado = new ArrayList<>();
		ArrayList<Plato> mainDishes = Carta.getDishesByType(TipoPlato.PLATO_FUERTE);
		Plato mainDish = mainDishes.get(new Random().nextInt(mainDishes.size()));
		platoPersonalSeleccionado.add(mainDish);
		boolean entreeAndDessert = Math.random() < 0.5;
		ArrayList<Plato> entreeDishes = Carta.getDishesByType(TipoPlato.ENTRADA);
		ArrayList<Plato> dessertDishes = Carta.getDishesByType(TipoPlato.POSTRE);
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

	public ArrayList<OrdenPersonal> crearOrden(List<Cliente> clientes) {
		ArrayList<OrdenPersonal> ordenesPersonales = new ArrayList<>();
		ordenesPersonales.addAll(clientes.stream().map(c -> new OrdenPersonal(c, c.seleccionarPlatosPorCliente()))
				.collect(Collectors.toList()));
		return ordenesPersonales;
	}

	public List<Cliente> tiempoLlegada() {
		tiempoLlegada += (int) (Math.random() * 10 + 5);
		return llegadaClientes(this.tiempoLlegada);
	}

	public List<Cliente> llegadaClientes(int arrivalTime) {
		return IntStream.rangeClosed(1, new Random().nextInt(Mesa.CAPACIDAD_MAXIMA) + 1)
				.mapToObj(x -> new Cliente(arrivalTime)).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		GestionarCocina gestionarCocina = new GestionarCocina();
		//gestionarCocina.generarOrdenesPseudoaleatorias();
	}
}
