package com.restaurante.app.agentes.mesa.restController;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * Esta clase abtracta permute implementar los metodos de un hilo 
 * de programacion con la ayuda de Thread y Runnable 
 */
public abstract class  MyThread implements Runnable {

	/*
	 * Atributo Thread propio de java
	 */
    private final Thread thread;
    /*
     * Areibuto que permite conocer si el hilo se esta ejecunado
     */
    private boolean isRunning;
    /*
     * Atributo que da a conocer si el hilo se ha pausado 
     */
    private boolean isPaused;

    public MyThread() {
        thread = new Thread(this);
    }
 
    /*
     * Metodo que inicia la ejecucion del hilo
     */
    public void start() {
        thread.start();
        isRunning = true;
    }

    /*
     * Metodo que detiene la ejecion del hilo por completo
     */
    public synchronized void stop() {
        isRunning = false;
        isPaused = false;
        thread.stop();
    }

    /*
     * Metodo que permita pausar el hilo
     */
    public synchronized void pause() {
        isPaused = true;
    }

    /*
     * Metodo que retoma la ejecucion del hilo despues de pausarlo 
     */
    public synchronized void summarize() {
        isPaused = false;
        notify();
    }

    /*
     * Implementacion del metodo run propio de la Interfaz Runnable
     */
    @Override
    public void run() {
        while (isRunning) {
            executeTask();
            synchronized (this) {
                while (isPaused) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!isRunning) {
                    break;
                }
            }
        }
    }
    /*
     * Metodoq implementado en la clase que hereda la presente 
     */
    abstract void executeTask();
}
