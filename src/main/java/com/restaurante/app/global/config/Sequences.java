package com.restaurante.app.global.config;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequences {
	
    public static final AtomicInteger ORDEN_ID = new AtomicInteger(1);
    public static final AtomicInteger CLIENTE_ID = new AtomicInteger(1);
    public static final AtomicInteger PAGO_ID = new AtomicInteger(1);
    public static final AtomicInteger MESA_ID = new AtomicInteger(1);
    public static final AtomicInteger DIA_TRABAJO_ID = new AtomicInteger(1);

}
