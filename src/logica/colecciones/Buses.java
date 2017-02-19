package logica.colecciones;

import java.util.Date;
import java.util.TreeMap;

import logica.objetos.Bus;
import logica.objetos.Excursion;

import java.util.Iterator;
import java.io.Serializable;

public class Buses implements Diccionario, Serializable{
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
	
	public boolean existeBus(String matricula){
		return this.diccionario.containsKey(matricula);
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
	
	/*Metodos de la interface Diccionario sobreescritos*/
	@Override
	public boolean empty() {
		return this.diccionario.isEmpty();
	}

	@Override
	public void insert(Object insertar) {
		this.diccionario.put(((Bus)insertar).getMatricula(), (Bus)insertar);
	}

	@Override
	public int length() {
		return this.diccionario.size();
	}

	@Override
	public Iterator iterator() {
		return (this.diccionario.values().iterator());
	}
	
	@Override
	public Object find(String codigo){
		return this.diccionario.get(codigo);
	}	
	
}
