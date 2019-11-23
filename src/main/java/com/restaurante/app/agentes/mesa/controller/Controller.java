package controller;

import model.DaoMesa;
import model.Mesa;

public class Controller {
	private DaoMesa daoMesa;
	
	public Controller() {
		daoMesa = new DaoMesa();
	}
	
	public Mesa obtenerMesaLibre(int numeroClientes) {
		return daoMesa.getMesaLibre(numeroClientes);
	}
	
	public void liberarMesa(int idMesa) {
		daoMesa.liberarMesa(idMesa);
	}
}
