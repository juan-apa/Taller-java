package servidor;

import java.net.MalformedURLException; 
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.rmi.registry.LocateRegistry;

import logica.fachada.Fachada;

public class Servidor {
	public static void main(String [] args) { 
		try {  
			// pongo a correr el rmiregistry 
			LocateRegistry.createRegistry(1099); 
			// instanciomi Objeto Remoto y lo publico 
			Fachada f = Fachada.getInstancia(); 
			System.out.println("Antes de publicarlo"); 
			Naming.rebind("//Guillermo:8080/Fachada", f); 
			System.out.println("Luego de publicarlo");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
 
}


