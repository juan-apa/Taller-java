package logica.colecciones;

import java.io.Serializable;

import logica.colecciones.Buses;
import logica.colecciones.Excursiones;

public class Datos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Buses _buses;
	private Excursiones _excursiones;
	
	public Datos(){
		_buses = new Buses();
		_excursiones = new Excursiones();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Buses get_buses() {
		return _buses;
	}

	public Excursiones get_excursiones() {
		return _excursiones;
	}

	public Buses buses() {
		return _buses;
	}

	public void setBuses(Buses buses) {
		this._buses = buses;
	}

	public Excursiones excursiones() {
		return _excursiones;
	}

	public void setExcursiones(Excursiones excursiones) {
		this._excursiones = excursiones;
	}
	
	
}
