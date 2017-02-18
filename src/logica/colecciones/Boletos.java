package logica.colecciones;

import java.io.Serializable;
import logica.Boleto;

public class Boletos implements Serializable{
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
		this.arreglo[this.tope].setNroBoleto(this.tope);
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
	
	/*Sobreescritura de metodos*/
}
