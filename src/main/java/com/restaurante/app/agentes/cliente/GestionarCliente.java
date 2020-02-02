package com.restaurante.app.agentes.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurante.app.agentes.mesa.controller.Controller;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.config.Identificadores;
import com.restaurante.app.global.config.MaquinaDelTiempo;
import com.restaurante.app.global.config.Sequences;
import com.restaurante.app.global.entities.Cliente;
import com.restaurante.app.global.entities.Mesa;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;

/**
 * Clase que se encarga de gestionar el cliente con los metodos de realizar
 * orden
 * 
 * @author pedro
 *
 */
public class GestionarCliente {

	private int tiempoLlegada;

	private int numeroClientes;
	
	private Mesa mesa;

	private List<Cliente> listClientes;
	
	@Autowired
	private MaquinaDelTiempo maquinaDelTiempo;

	public GestionarCliente() {
		this.listClientes = new ArrayList<>();
	}

	private static GestionarCliente instance;

	// metodo que permite obtener una instancia de la clase
	public static GestionarCliente getInstance() {
		if (instance == null) {
			instance = new GestionarCliente();
		}
		return instance;
	}

	/**
	 * Metodo encargado de retornar la lista de clientes con el tiempo de llemgada
	 * de dicho grupos
	 * 
	 * @param maquinaDelTiempo
	 * @return
	 */
	public List<Cliente> tiempoLlegada() {
		this.tiempoLlegada += (int) (Math.random() * 10 + 5);
		return llegadaClientes(this.tiempoLlegada);
	}

	/**
	 * Clients by themselves are not considered as single arrival units given an
	 * arrival rate. Instead, they are grouped and then these are considered as
	 * single arrival units Se pasa por parametro el tiempo de llegada de cada
	 * cliente
	 * 
	 * @param arrivalTime
	 * @param maquinaDelTiempo
	 * @return A list of clients (diners) ranging between 1 to 5 members
	 */
	public List<Cliente> llegadaClientes(int arrivalTime) {
		return IntStream.rangeClosed(1, new Random().nextInt(Mesa.CAPACIDAD_MAXIMA) + 1).mapToObj(x -> {
			
			/*
			 * Apenas se sepan los tiempos de llegada y salida del cliente, se agrega un registro a la máquina del tiempo
			 * con dicho suceso
			 */
			Cliente c = new Cliente(Sequences.CLIENTE_ID.getAndIncrement(), arrivalTime);
			maquinaDelTiempo.agregarRegistro(Identificadores.CLIENTE, c.getTiempoLLegada(), c.getTiempoConsumo(), c);
			return c;
		}).collect(Collectors.toList());
	}

	/**
	 * Metodo encargado de generar la orden y retornarla
	 * 
	 * @param maquinaDelTiempo
	 * @return
	 */
	public Orden generarOrden() {
		Orden orden = new Orden(Sequences.ORDEN_ID.getAndIncrement());
		orden.setPersonalOrders(crearOrden(tiempoLlegada()));
		return orden;
	}

	/**
	 * Metodo encargado de crear la orden personal
	 * 
	 * @param clientes
	 * @return
	 */
	public ArrayList<OrdenPersonal> crearOrden(List<Cliente> clientes) {
		this.listClientes.addAll(clientes);
		this.numeroClientes = clientes.size();
		ArrayList<OrdenPersonal> ordenesPersonales = new ArrayList<>();

		ordenesPersonales.addAll(clientes.stream().map(c -> new OrdenPersonal(c, c.seleccionarPlatosPorCliente()))
				.collect(Collectors.toList()));
		// Let every client to choose the dishes he/she wants to order
		return ordenesPersonales;
	}

	/**
	 * Metodo encargado de calificar los platos despues que realiza el consumo
	 * 
	 * @param orden preparada servicio que entra al cliente
	 */
	public Orden calificarOrdenesPersonales(Orden orden) {
		orden.getOrdenesPersonales().forEach(OrdenPersonal::calificarPlatos);
		return orden;
	}

	/**
	 * Metodo encargado de retornar el numero de clientes por mesa
	 * 
	 * @return
	 */
	public int getNumeroClientes() {
		return numeroClientes;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Mesa getMesa() {
		return mesa;
	}

	/**
	 * Metodo enacargado de retornar los clientes en el restaurante
	 * 
	 * @return lista de clientes
	 */
	public List<Cliente> obtenerClientes() {
		return listClientes;
	}

	/**
	 * Metodo encargado de calificar el mesero
	 * 
	 * @param mesero
	 * @return
	 */
//	public Mesero calificarMesero(Mesero mesero) {
//		int calificacion = (int) (Math.random()*5);
//		return mesero.calificar(calificacion);
//	}

}
