package logica.objetos;

import java.io.Serializable;

import logica.colecciones.Excursiones;

public class Bus implements Serializable {
	private String matricula;
	private String marca;
	private int capPasajeros;
	private Excursiones excuBus;
	
	public Bus(String matricula, String marca, int capPasajeros) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.capPasajeros = capPasajeros;
		this.excuBus = new Excursiones();
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
	
	public int cantidadExcursionesAsignadas(){
		return this.excuBus.length();
	}
	
	public Excursiones getExcuBus() {
		return excuBus;
	}
	
	public int asientosDisponiblesParaExcursion(String codigoExcursion){
		int cantAsientosDisp = 0;
		cantAsientosDisp = this.getCapPasajeros() - this.getExcuBus().find(codigoExcursion).getBoletos().length();
		return cantAsientosDisp;
	}
	
	public void insertarExcursion(Excursion insertar){
		this.excuBus.insert(insertar);
	}
	
	@Override
	public String toString() {
		return "Bus [matricula=" + matricula + ", marca=" + marca + ", capPasajeros=" + capPasajeros + ", " + excuBus.toString()+ "]";
	}
	
}
