package com.restaurante.app.agentes.mesa.restController;

import java.util.Calendar;
/*
 * Este es la clase que importa el hilo para llevar el control del tiempo
 */
public class TimeContoller extends MyThread{
	/*
	 * Atributo contador de minutos
	 */
	private int minutes;
	/*
	 * Atributo de tipo tiempo en la cual se almacenan las horas y minutos que van transcuerriendo una 
	 * vez se ejecuta el hilo 
	 */
	private Calendar calendar;
	
	public TimeContoller() {
		minutes = 0;
		calendar = Calendar.getInstance();
	}
	public int getSeconds() {
		return minutes;
	}
	
	public void setSeconds(int seconds) {
		this.minutes = seconds;
	}
	/*
	 * Este metodo es que permite establecer el delta delta de 
	 * tiempo necesario para incrementar el valor de los minutos
	 * ademas asigna el valor de minutos y horas de la varable 
	 * de tiempo definida 
	 */
	@Override
	void executeTask() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		minutes++;
		calendar.set(Calendar.HOUR,minutes/60);
		calendar.set(Calendar.MINUTE,minutes%60);
		System.out.println("Tiempo->" + calendar.get(Calendar.HOUR)+":"+
				calendar.get(Calendar.MINUTE));
	}
	
	public Calendar getCalendar() {
		return calendar;
	}
	
	public static void main(String[] args) {
		TimeContoller timeContoller = new TimeContoller();
		timeContoller.start();		
	}
	
}
