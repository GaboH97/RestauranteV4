package com.restaurante.app.agentes.cliente;

import java.util.ArrayList;
import java.util.Random;

import com.restaurante.app.global.entities.Carta;
import com.restaurante.app.global.entities.Plato;
import com.restaurante.app.global.entities.TipoPlato;

/**
 *
 * @author Lenovo
 */
public class Cliente {

    private static int ID_CLIENTE = 1;
    private int id;
    private String nombre;
    private int tiempoLLegada;
    /**
     * Atributo que representa el tiempo de consumo del cliente.
     */
    private int tiempoConsumo;


    public Cliente() {
    }

    public Cliente(int arrivalTime) {
        this.id = ID_CLIENTE++;
        this.nombre = "Cliente " + id;
        this.tiempoConsumo = (int) (Math.random()*5+1); 
        this.tiempoLLegada = arrivalTime;
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
    * @return a list of dishes which represent a personal order following a set
    * of rules The customer always consumes a main course The client can
    * request a maximum of one entry and / or two desserts in their respective
    * order.
    */
   public ArrayList<Plato> seleccionarPlatosPorCliente() {

       ArrayList<Plato> platoPersonalSeleccionado = new ArrayList<>();

       //Get a main Plato (Mandatory)
       ArrayList<Plato> mainDishes = Carta.getDishesByType(TipoPlato.PLATO_FUERTE);
       Plato mainDish = mainDishes.get(new Random().nextInt(mainDishes.size()));
       platoPersonalSeleccionado.add(mainDish);

       boolean entreeAndDessert = Math.random() < 0.5;

       ArrayList<Plato> entreeDishes = Carta.getDishesByType(TipoPlato.ENTRADA);
       ArrayList<Plato> dessertDishes = Carta.getDishesByType(TipoPlato.POSTRE);

       /**
        * Complete menu with one of these two options
        *
        * 1. One entreé and up to two desserts 
        * 2. One entreé or up to two
        * desserts
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
