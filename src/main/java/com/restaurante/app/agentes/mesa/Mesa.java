package com.restaurante.app.agentes.mesa;

/**
 *
 * @author Lenovo
 */
public class Mesa {

    public static final int CAPACIDAD_MAXIMA = 5;

    private static int TABLE_COUNT = 1;
    private int id;
    private boolean disponible;

    public Mesa() {
        this.id = TABLE_COUNT++;
        this.disponible = true;
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
