package logica.colecciones;

import java.util.Iterator;
import java.util.TreeMap;
import logica.objetos.Excursion;
import java.io.Serializable;

public class Excursiones implements Diccionario, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2831930180831747756L;
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
	
	public boolean member(String codigo){
		return this.diccionario.containsKey(codigo);
	}
	
	public Iterator<Excursion> listarOrden(){
		Iterator<Excursion> devolver;
		devolver = diccionario.values().iterator();
		return devolver;
	}
	
	public boolean boletoMismoTipo(String tipoBoleto){
		boolean hayBoletoConTipo = false;
		Iterator<Excursion> ite;
		ite = this.listarOrden();
		while(ite.hasNext() && !hayBoletoConTipo){
			Excursion aux = ite.next();
			hayBoletoConTipo = aux.getBoletos().hayBoletoConTipo(tipoBoleto);
		}
		return hayBoletoConTipo;
	}
	
	public boolean excursionMismoDestino(String destino){
		boolean hayExcursionConDestino = false;
		Iterator<Excursion> ite;
		ite = this.listarOrden();
		while(ite.hasNext() && !hayExcursionConDestino){
			Excursion aux = ite.next();
			if(aux.getDestino() == destino){
				hayExcursionConDestino = true;
			}
		}
		return hayExcursionConDestino;
	}
	
	public boolean excursionEnRango(double precioMin, double precioMax){
		boolean hayExcursionEnRango = false;
		Iterator<Excursion> ite;
		ite = this.listarOrden();
		while(ite.hasNext() && !hayExcursionEnRango){
			Excursion aux = ite.next();
			if((precioMin <= aux.getPrecioBase()) && (aux.getPrecioBase()<=precioMax)){
				hayExcursionEnRango = true;
			}
		}
		return hayExcursionEnRango;
	}
	
	
	/*Metodos de la interface Diccionario sobreescritos*/
	@Override
	public boolean empty(){
		return this.diccionario.isEmpty();
	}

	@Override
	public void insert(Object insertar) {
		this.diccionario.put(((Excursion) insertar).getCodigo(), (Excursion) insertar);
	}
	
	@Override
	public int length(){
		return this.diccionario.size();
	}

	@Override
	public Iterator iterator() {
		return diccionario.values().iterator();
	}
	
	@Override
	public Excursion find(String codigo){
		return this.diccionario.get(codigo);
	}

	@Override
	public boolean exists(String clave) {
		return this.diccionario.containsKey(clave);
	}	
}
