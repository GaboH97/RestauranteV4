package com.restaurante.app.global.entities;

/**
 *
 * @author Gabriel Huertas
 */
public enum EstrategiaPago {
    
    /**
     * Each client pays for what he/she has consumed
     */
    AMERICANO("Americano"),
    /**
     * One client pays for everyone
     */
    UNO_POR_TODOS("Uno por todos"),
    /**
     * All clients payments are equally distributed
     */
    TODOS_POR_TODO("Todos por todo");
    
    private String nombre;

    EstrategiaPago(String name) {
        this.nombre = name;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
