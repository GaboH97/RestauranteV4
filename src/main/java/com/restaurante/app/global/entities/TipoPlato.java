package com.restaurante.app.global.entities;

/**
 *
 * @author Lenovo
 */
public enum TipoPlato {
    
    ENTRADA(1,"Entrada"),
    PLATO_FUERTE(2,"Plato Fuerte"),
    POSTRE(3,"Postre");

    private int id;
    private String name;

    private TipoPlato(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
