package logica.objetos;

import java.io.Serializable;

import logica.colecciones.Excursiones;

public class Bus implements Serializable {
	private static final long serialVersionUID = 1L;
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
	public Bus() {
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
	
	public void sacarExcursion(String sacar){
		this.excuBus.remove(sacar);
	}
	
	public boolean entraAsientosEnBus(int cantAsientos){
		boolean entra = false;
		if(this.capPasajeros >= cantAsientos){
			entra = true;
		}
		return entra;
	}
	
	
	@Override
	public String toString() {
		return "Bus [matricula=" + matricula + ", marca=" + marca + ", capPasajeros=" + capPasajeros + ", " + excuBus.toString()+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = true;
		if((this == null && obj != null) || (this != null && obj == null)){
			iguales = false;
		}
		else{
			if(this.capPasajeros != ((Bus) obj).getCapPasajeros()){
				iguales = false;
			}
			if(!this.marca.equals(((Bus) obj).getMarca())){
				iguales = false;
			}
			if(!this.matricula.equals(((Bus) obj).getMatricula())){
				iguales = false;
			}
			if(!this.excuBus.equals(((Bus) obj).getExcuBus())){
				iguales = false;
			}
		}
		return iguales;
	}
}
	
	