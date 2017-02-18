package logica.colecciones;

import java.util.Date;
import java.util.TreeMap;

import logica.objetos.Bus;

import java.util.Iterator;
import java.io.Serializable;

public class Buses implements Serializable{
	/*Atributos*/
	private TreeMap<String, Bus> diccionario;
	
	/*Constructores*/
	public Buses() {
		super();
		diccionario = new TreeMap<String, Bus>();
	}
	
	/*Getters y Setters*/
	public TreeMap<String, Bus> getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(TreeMap<String, Bus> diccionario) {
		this.diccionario = diccionario;
	}
	
	
	/*Funciones propias*/
	public void insert(Bus insertar){
		this.diccionario.put(insertar.getMatricula(), (Bus)insertar);
	}
	
	public boolean existeBus(String matricula){
		return this.diccionario.containsKey(matricula);
	}
	
	public boolean esVacio(){
		return this.diccionario.isEmpty();
	}
	
	public Bus find(String matricula){
		return this.diccionario.get(matricula);
	}
	
	public boolean hayBusLibre(Date hpartida, Date hregreso){
		/*TODO terminar funcion, no compara las fechas, porque falta implementar alguna funcion de Excursiones.*/
		boolean busLibre = false;
		Iterator<Bus> recorrida = this.diccionario.values().iterator();
		while(!busLibre && (recorrida.hasNext())){
			Bus busAux =recorrida.next();
			//Aca va la comparacion que le quiero hacer a cada bus
			System.out.println("Pasada\n"+ busAux.toString());
			
		}
		return busLibre;
	}
	
	public void imprimir(){
		System.out.println(this.diccionario.toString());
	}
	
}
