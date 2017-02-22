package logica.colecciones;

import logica.colecciones.Buses;
import logica.colecciones.Excursiones;

public class Datos {
	private Buses _buses;
	private Excursiones _excursiones;
	
	public Datos(){
		_buses = new Buses();
		_excursiones = new Excursiones();
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
