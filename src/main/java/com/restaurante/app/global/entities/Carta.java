package com.restaurante.app.global.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Carta {
	
	 public static List<Plato> MENU = Arrays.asList(
	            //--------------------- MAIN DISHES --------------------------
	            new Plato("Trucha", TipoPlato.PLATO_FUERTE, 25, 35),
	            new Plato("Mojarra", TipoPlato.PLATO_FUERTE, 20, 20),
	            new Plato("Hamburguesa", TipoPlato.PLATO_FUERTE, 12, 15),
	            new Plato("Churrasco", TipoPlato.PLATO_FUERTE, 18, 30),
	            new Plato("Ejecutivo", TipoPlato.PLATO_FUERTE, 15, 30),
	            //--------------------- ENTREÉS --------------------------
	            new Plato("Crema de brócoli", TipoPlato.ENTRADA, 5, 10),
	            new Plato("Ensalada cebolla y tomate", TipoPlato.ENTRADA, 5, 5),
	            new Plato("Sopa de pollo", TipoPlato.ENTRADA, 3.5, 5),
	            new Plato("Huevos rellenos", TipoPlato.ENTRADA, 10, 8),
	            new Plato("Ensalada de frutas", TipoPlato.ENTRADA, 8, 10),
	            //--------------------- DESSERTS --------------------------
	            new Plato("Helado con galletas", TipoPlato.POSTRE, 1.5, 5),
	            new Plato("Postre de maracuya", TipoPlato.POSTRE, 2.5, 5),
	            new Plato("Gelatina", TipoPlato.POSTRE, 3, 8),
	            new Plato("Brownie", TipoPlato.POSTRE, 3.5, 10),
	            new Plato("Crema de papaya", TipoPlato.POSTRE, 6, 14),
	            new Plato("Gelato", TipoPlato.POSTRE, 3, 10),
	            new Plato("Buñuelos de viento", TipoPlato.POSTRE, 2, 8),
	            new Plato("Tarta de chocolate", TipoPlato.POSTRE, 4, 10),
	            new Plato("Fresas con chocolate", TipoPlato.POSTRE, 2.5, 15),
	            new Plato("Pudding vainilla", TipoPlato.POSTRE, 5.5, 10)
	    );
	 
	 /**
     *
     * @param tipoPlato Type of dish to filter from menu
     * @return
     */
    public static ArrayList<Plato> getDishesByType(TipoPlato tipoPlato) {
        return (ArrayList<Plato>) MENU.stream()
                .filter(d -> d.getTipoPlato().equals(tipoPlato))
                .collect(Collectors.toList());
    }
}
