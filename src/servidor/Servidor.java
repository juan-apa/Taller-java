package servidor;

import java.rmi.Naming; 
import java.rmi.registry.LocateRegistry;

import logica.fachada.Fachada;

public class Servidor {
	public static void main(String [] args) { 
		try {  
			// pongo a correr el rmiregistry 
			LocateRegistry.createRegistry(1099);
			// instanciomi Objeto Remoto y lo publico 
			Fachada fachada = Fachada.getInstancia();
			System.out.println("Antes de publicarlo");
			Naming.rebind("//192.168.0.110:1099/fachada", fachada);
			System.out.println("Luego de publicarlo");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
 
}


