package logica.colecciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Iterador<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	int max;
	int actual;
	List<T> contenido;
	
	public Iterador(){
		contenido = new ArrayList<T>();
		max = 0;
		actual = 0;
	}
	
	public void add(T insertar){
		contenido.add(insertar);
		max++;
	}
	
	public boolean hasNext(){
		return(actual < max);
	}
	
	public T next(){
		actual++;
		return contenido.get(actual - 1);
	}
	
	public boolean empty(){
		return (contenido.size() == 0);
	}
	
	public ArrayList<T> toArray(){
		return (ArrayList<T>) this.contenido;
	}
	
}
