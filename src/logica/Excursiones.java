package logica;

import java.util.Set;

import logica.Excursion;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.io.Serializable;

import com.sun.javafx.collections.MappingChange.Map;

public class Excursiones implements Serializable{
	private TreeMap<String, Excursion> diccionario;
	
	public Excursiones() {
		super();
		diccionario = new TreeMap<String, Excursion>();
	}
	
	public TreeMap<String, Excursion> getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(TreeMap<String, Excursion> diccionario) {
		this.diccionario = diccionario;
	}
	
	/*Funciones propias*/
	public void insert(Excursion insertar){
		this.diccionario.put(insertar.getCodigo(), insertar);
	}
	
	public boolean existeExcursion(String codigo){
		return this.diccionario.containsKey(codigo);
	}
	
	public boolean esVacio(){
		return this.diccionario.isEmpty();
	}
	
	public Excursion find(String codigo){
		return this.diccionario.get(codigo);
	}	
	
}
