package com.restaurante.app.agentes.mesa.model;

/**
 *
 * @author Lenovo
 */
public class Mesa {

    public static final int CAPACIDAD_MAXIMA = 5;

    private int id;
    private boolean disponible;
    private int indicadorComodidad;
    private int indicadorCalidad;
    private int capacidad;

    public Mesa(int id,int indicadorCalidad, int indicadorComodidad, int capacidad) {
        this.id = id;
        this.disponible = true;
        this.indicadorComodidad = indicadorComodidad;
        this.indicadorCalidad = indicadorCalidad;
        this.capacidad = capacidad;
    }
    
    public Mesa() {
	}

    public void ocupar() {
        this.disponible = false;
    }

    public void desocupar() {
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCapacidad() {
		return capacidad;
	}
    
    public int getIndicadorCalidad() {
		return indicadorCalidad;
	}
    
    public int getIndicadorComodidad() {
		return indicadorComodidad;
	}

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean available) {
        this.disponible = available;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MesaID: ").append(id).append(System.getProperty("line.separator"))
                .append("Disponible?: ").append(disponible ? "Yes" : "No").append(System.getProperty("line.separator"));
        return builder.toString();
    }
    
    

}
