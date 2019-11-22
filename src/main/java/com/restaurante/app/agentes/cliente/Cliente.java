package com.restaurante.app.agentes.cliente;

/**
 *
 * @author Lenovo
 */
public class Cliente {

    private static int ID_CLIENTe = 1;
    private int id;
    private String nombre;



    public Cliente() {
        this.id = ID_CLIENTe++;
        this.nombre = "Cliente " + id;
    }

    public Cliente(int arrivalTime) {
        this.id = ID_CLIENTe++;
        this.nombre = "Cliente " + id;

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

    @Override
    public String toString() {
        return this.nombre;
    }

}
