package logica;


import java.util.Date;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.Serializable;

public class Buses {
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
		boolean busLibre = false;
//		Set<Map.Entry<String, Bus>> setValores = this.diccionario.entrySet();
//		Iterator<Entry<String, Bus>> ite = setValores.iterator();
		
		Iterator<Entry<String, Bus>> ite = this.diccionario.entrySet().iterator();
		//Obtengo un iterador de entradas. Cada entrada esta compuesta por una clave:String y un valor:Bus
		
		while(!busLibre && (ite.hasNext())){
			//De las entradas, quiero obtener el valor:Bus, eso lo hago usando la funcion getValue().
			Bus busAux = ite.next().getValue();
			//Aca va la comparacion que le quiero hacer a cada bus
			System.out.println("Pasada\n"+ busAux.toString());
			
		}
		return busLibre;
	}
	
	public void imprimir(){
		System.out.println(this.diccionario.toString());
	}
	
}
