package com.restaurante.app.agentes.mesero;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public enum EstadoMesero {
    
	ATENDIENDO, // Waiter is taking the order
    EN_COCINA, // Waiter is either leaving an order or picking up the dishes
    LIMPIANDO_MESA, // Waiter is cleaning a table
    DISPONIBLE, //Waiter is available to do any action
    DESCANSANDO; // Waiter is taking a rest (as specified in the docs)
}
