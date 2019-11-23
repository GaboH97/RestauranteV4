package com.restaurante.app.global.entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import com.restaurante.app.agentes.cliente.Cliente;

/**
 *
 * @author Lenovo
 */
public class OrdenPersonal {

    private Cliente cliente;
    private ArrayList<PlatoOrdenado> platosOrdenados;
    private boolean estaPreparado;

    /**
     *
     * @param cliente Cliente who has placed its order
     * @param selectedDishes Selected dishes
     */
    public OrdenPersonal(Cliente cliente, ArrayList<Plato> selectedDishes) {
        this.cliente = cliente;
        this.platosOrdenados = new ArrayList<>();
        this.estaPreparado = false;
        setSelectedDishes(selectedDishes);
    }

    public OrdenPersonal(Cliente cliente) {
        this.cliente = cliente;
        this.platosOrdenados = new ArrayList<>();
    }

    public void calificarPlatos() {
        Random r = new Random();
        platosOrdenados.forEach(o -> o.setCalificacion(r.nextInt(6)));
    }
    
    
      

//    /**
//     * Check if this personal order is ready to be served
//     *
//     * @return true if this personal order is ready, otherwise, false
//     */
//    public boolean isReady() {
//        return platosOrdenados.stream()
//                .allMatch(o -> o.getOrderedDishState().equals(OrderedDishState.PREPARED));
//    }
//
//    /**
//     * Set all ordered dishes' state to eating
//     */
//    public void eat() {
//        platosOrdenados.forEach(od -> od.setOrderedDishState(OrderedDishState.EATING));
//    }
//
//    /**
//     * Set all ordered dishes' state to consumed
//     */
//    public void finish() {
//        platosOrdenados.forEach(od -> od.setOrderedDishState(OrderedDishState.CONSUMED));
//    }
//
//    public void consumeOrder() {
//        eat();
//        finish();
//    }

    /**
     *
     * @return Total for ordered dishes
     */
    public double obtenerPrecioTotal() {
        return platosOrdenados
                .stream()
                .mapToDouble(po -> po.getPlato().getPrecio())
                .sum();
    }

    public void setSelectedDishes(ArrayList<Plato> selectedDishes) {
        platosOrdenados.addAll(selectedDishes.stream()
                .map(sd -> new PlatoOrdenado(sd)).collect(Collectors.toList()));
    }

//    public void calculateAttentionTime() {
//        this.cliente.setAttentionTime(
//                platosOrdenados.stream().mapToInt(od -> od.getDish().getTiempoCoccion()).sum()
//        );
//    }

    public Cliente getClient() {
        return cliente;
    }

    public void setClient(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<PlatoOrdenado> getPlatosOrdenados() {
        return platosOrdenados;
    }

    public void setPlatosOrdenados(ArrayList<PlatoOrdenado> orderedDishes) {
        this.platosOrdenados = orderedDishes;
    }
   
    public boolean isEstaPreparado() {
		estaPreparado = platosOrdenados.stream().allMatch(PlatoOrdenado::isEstaPreparado);
		return estaPreparado;
    }
    
    public void setEstaPreparado(boolean estaPreparado) {
		this.estaPreparado = estaPreparado;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cliente: ").append(cliente).append(System.getProperty("line.separator"))
                .append("\t").append("Platos: ").append(System.getProperty("line.separator"));
        platosOrdenados
                .forEach(od -> builder.append("\tPlato: ").append(od.getPlato().getNombre())
                .append(" Tipo Plato: ").append(od.getPlato().getTipoPlato())
                .append(System.getProperty("line.separator")));

        return builder.toString();
    }

}
