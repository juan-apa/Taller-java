package logica.ValueObjects;

import java.io.Serializable;


public class VOBusExc implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private int capPasajeros;
	private int excursionesAsignadas;
	
	public VOBusExc(){
		super();
		this.matricula = "";
		this.marca = "";
		this.capPasajeros = 0;
		this.excursionesAsignadas = 0;
	}
	
	public VOBusExc(String matricula, String marca, int capPasajeros, int excursionesAsignadas) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.capPasajeros = capPasajeros;
		this.excursionesAsignadas = excursionesAsignadas;
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

	public int getExcursionesAsignadas() {
		return excursionesAsignadas;
	}

	public void setExcursionesAsignadas(int excursionesAsignadas) {
		this.excursionesAsignadas = excursionesAsignadas;
	}

	@Override
	public String toString() {
		return "VOBusExc [matricula=" + matricula + ", marca=" + marca + ", capPasajeros=" + capPasajeros
				+ ", excursionesAsignadas=" + excursionesAsignadas + "]";
	}
	
}
