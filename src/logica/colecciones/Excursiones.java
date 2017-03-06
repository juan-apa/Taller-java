package logica.colecciones;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeMap;

import logica.objetos.Excursion;

public class Excursiones implements Diccionario, Serializable{

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
	
	public boolean entraExcursion(Excursion insertar){
		/*TODO Agregar en excursion un metodo para mostrar solo la hora y no la date entera.*/
		boolean entra = true;
		Iterator<Excursion> iteExc = this.iterator();
		while(iteExc.hasNext()){
			Excursion excAux = iteExc.next();
			if(!((insertar.getHllegada().before(excAux.getHpartida()))||(insertar.getHllegada().equals(excAux.getHpartida()))  
					|| ((insertar.getHpartida().after(excAux.getHllegada()))||(insertar.getHpartida().equals(excAux.getHllegada()))) ))
			{
				entra = false;
			}
		}
		return entra;
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
	public Iterator<Excursion> iterator() {
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

	@Override
	public String toString() {
		return "Excursiones [diccionario=" + diccionario.toString() + "]";
	}

	@Override
	public void remove(String clave) {
		this.diccionario.remove(clave);
	}	
	
	@Override
	public boolean equals(Object otro){
		boolean iguales = true;
		if((this == null && otro != null) || (this != null && otro == null)){
			iguales = false;
		}
		else{
			Iterator<Excursion> iteOtro= ((Excursiones) otro).iterator();
			Iterator<Excursion> iteThis = this.iterator();
			
			while(iguales && iteOtro.hasNext() && iteThis.hasNext()){
				Excursion excOtro = iteOtro.next();
				Excursion excThis = iteThis.next();
				if(!excOtro.equals(excThis)){
					iguales = false;
				}
			}
			
			if((iteThis.hasNext() && !iteOtro.hasNext()) || (!iteThis.hasNext() && iteOtro.hasNext())){
				iguales = false;
			}
		}
		
		return iguales;
	}
}
