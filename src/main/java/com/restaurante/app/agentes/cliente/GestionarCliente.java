package com.restaurante.app.agentes.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.restaurante.app.agentes.mesa.model.Mesa;
import com.restaurante.app.agentes.mesero.Mesero;
import com.restaurante.app.global.entities.Orden;
import com.restaurante.app.global.entities.OrdenPersonal;

/**
 * Clase que se encarga de gestionar el cliente con los metodos de realizar orden
 * @author pedro
 *
 */
public class GestionarCliente {
	
	/**
	 * Tiempo de llegada al restaurante despues de que llego la primer persona 
	 * o el primer grupo de personas
	 */
	private int tiempoLlegada; 
	/**
	 * Atributo qe permite conocer la cantidad de clientes
	 */
	private int numeroClientes;
	/**
	 * Atributo que representa la mesa asignada
	 */
	private Mesa mesa;
	
	public GestionarCliente() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo encargado de retornar la lista de clientes con el tiempo de llemgada de dicho grupos
	 * @return
	 */
	public List<Cliente> tiempoLlegada(){
		this.tiempoLlegada += (int) (Math.random()*10+5);
		return llegadaClientes(this.tiempoLlegada);
	}
	
	/**
     * Clients by themselves are not considered as single arrival units given an
     * arrival rate. Instead, they are grouped and then these are considered as
     * single arrival units
     *Se pasa por parametro el tiempo de llegada de cada cliente
     * @param arrivalTime
     * @return A list of clients (diners) ranging between 1 to 5 members
     */
    public List<Cliente> llegadaClientes(int arrivalTime) {
        return IntStream.rangeClosed(1, new Random().nextInt(Mesa.CAPACIDAD_MAXIMA) + 1)
                .mapToObj(x -> new Cliente(arrivalTime))
                .collect(Collectors.toList());
    }
    
    /**
     * Metodo encargado de generar la orden y retornarla
     * @return
     */
    public Orden generarOrden() {
		Orden orden = new Orden();
		orden.setPersonalOrders(crearOrden(tiempoLlegada()));
		return orden;
    	
    }
	

    /**
     * Metodo encargado de crear la orden personal
     * @param clientes
     * @return
     */
	public ArrayList<OrdenPersonal> crearOrden(List<Cliente> clientes) {
		this.numeroClientes = clientes.size();
		ArrayList<OrdenPersonal> ordenesPersonales = new ArrayList<>();
		
        ordenesPersonales.addAll(clientes.stream()
                .map(c -> new OrdenPersonal(c, c.seleccionarPlatosPorCliente()))
                .collect(Collectors.toList())
        );
        //Let every client to choose the dishes he/she wants to order
        return ordenesPersonales;
    }
	
	/**
	 * Metodo encargado de calificar los platos despues que realiza el consumo
	 * @param orden preparada servicio que entra al cliente
	 */
	public Orden calificarOrdenesPersonales(Orden orden) {
		orden.getPersonalOrders().forEach(OrdenPersonal::calificarPlatos);
		return orden;
	}
	
	/**
	 * Metodo encargado de retornar el numero de clientes por mesa
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
	 *  Metodo encargado de calificar el mesero 
	 * @param mesero
	 * @return
	 */
//	public Mesero calificarMesero(Mesero mesero) {
//		int calificacion = (int) (Math.random()*5);
//		return mesero.calificar(calificacion);
//	}
	
}
