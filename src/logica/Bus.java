package logica;

import java.io.Serializable;

public class Bus implements Serializable {
	private String matricula;
	private String marca;
	private int capPasajeros;
	//private Excursiones excuBus;
	
	public Bus(String matricula, String marca, int capPasajeros) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.capPasajeros = capPasajeros;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getCapPasajeros() {
		return capPasajeros;
	}
	public void setCapPasajeros(int capPasajeros) {
		this.capPasajeros = capPasajeros;
	}
}
