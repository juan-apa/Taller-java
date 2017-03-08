package logica.concurrencia;

import java.util.LinkedList;

public class Cola {
	private LinkedList lista;
	private static Cola instancia; 
	
	private Cola (){
		lista = new LinkedList();
	}
	
	public synchronized static Cola getInstancia (){ 
		if (instancia == null)
			instancia = new Cola(); // Singleton !!
		return instancia;
	}
	
}
