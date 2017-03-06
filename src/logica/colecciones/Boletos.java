package logica.colecciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import logica.objetos.Boleto;
import logica.objetos.Especial;

public class Boletos implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boleto arreglo[];
	private int tope;
	
	/*Constructores*/
	public Boletos(int cantidadAsientos) {
		super();
		this.tope = 0;
		this.arreglo = new Boleto[cantidadAsientos];
	}
	
	/*Getters y Setters*/
	public Boleto[] getArreglo() {
		return arreglo;
	}
	
	public void setArreglo(Boleto[] arreglo) {
		this.arreglo = arreglo;
	}
	
	public int getTope() {
		return tope;
	}
	
	public void setTope(int tope) {
		this.tope = tope;
	}
	
	/*Funciones propias*/
	/*Devuelve true si el arreglo se encuentra vacio, false de lo contrario*/
	public boolean empty(){ 
		return (this.tope == 0);
	}
	
	/*Devuelve true si el arreglo esta lleno, false de lo contrario.*/
	public boolean full(){
		return (this.tope == this.arreglo.length);
	}
	
	/*Inserta un boleto al final en la siguiente posicion disponible.*/
	public void insert(Boleto insertar){
		this.arreglo[this.tope] = (Boleto)insertar;
		this.tope = this.tope + 1;
	}
	
	/*Devuelve true si hay al menos un boleto con el tipo tipoBoleto.*/
	/*Precondicion: tipoBoleto tiene que ser "Comun" o "Especial"*/
	public boolean hayBoletoConTipo(String tipoBoleto){
		boolean hayBoletoConTipo = false;
		int i = 0;
		while(!hayBoletoConTipo && i < this.tope){
			if(arreglo[i].getTipo()== tipoBoleto){
				hayBoletoConTipo = true;
			}
			else{
				i++;
			}
		}
		return hayBoletoConTipo;
	}
	
	public Boleto getBoletoNro(int nroBoleto){
		Boleto ret;
		ret = this.arreglo[nroBoleto - 1];
		return ret;
	}
	
	public int length(){
		return this.tope;
	}
	
	public double recaudado(double precioBase){
		double ret = 0.0;
		for(int i = 0; i < this.tope; i++){
			if(arreglo[i].getTipo().equals("Especial")){
				ret = ret + (precioBase*(((Especial) arreglo[i]).getDtoAdicional()));
			}
			else
				ret = ret + precioBase;
		}
		return ret;
	}
	
	public Iterator<Boleto> iterator(){
		List<Boleto> ret = new ArrayList<Boleto>();
		for(int i = 0; i < this.tope; i++){
			ret.add(this.arreglo[i]);
		}
		return ret.iterator();
	}
	/*nuevaCap tiene que ser mayor a la capacidad maxima del arreglo anterior*/
	public void actualizarMax(int nuevaCap, int topeViejo) {
		Boleto[] nuevoArr = new Boleto[nuevaCap];
		int i;
		for(i= 0; i < topeViejo; i++){
			if(this.arreglo[i].getTipo() == "Comun"){
				nuevoArr[i] = new Boleto(arreglo[i]);
			}
			else{
				nuevoArr[i] = new Especial(arreglo[i]);
			}
			this.arreglo[i] = null;
		}
		this.setArreglo(nuevoArr);
		this.tope = topeViejo;
	}

	@Override
	public boolean equals(Object obj) {	
		boolean iguales = true;
		if((this == null && obj != null) || (this != null && obj == null)){
			iguales = false;
		}
		else{
			Iterator<Boleto> iteOther = ((Boletos) obj).iterator();
			Iterator<Boleto> iteThis = this.iterator();
			while(iguales && iteOther.hasNext() && iteThis.hasNext()){
				Boleto bolOther = iteOther.next();
				Boleto bolThis = iteThis.next();
				
				if(!bolThis.equals(bolOther)){
					iguales = false;
				}
			}
			if((iteThis.hasNext() && !iteOther.hasNext()) || (!iteThis.hasNext() && iteOther.hasNext())){
				iguales = false;
			}
		}
		return iguales;
	}
	
	@Override
	public String toString() {
		String devolver = "Boletos[";
		for(int i = 0; i < this.tope; i++){
			devolver = devolver + this.arreglo[i].toString() + " ,";
		}
		devolver = devolver + "]";
		return devolver;
	}

}
