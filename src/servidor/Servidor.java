package servidor;

import java.rmi.Naming; 
import java.rmi.registry.LocateRegistry;
import persistencia.Propiedades;
import logica.fachada.Fachada;

public class Servidor {
	public static void main(String [] args) { 
		try {  
			Propiedades p = new Propiedades();
			String puerto = p.buscar("Puerto");
			String ip = p.buscar("Ip");
			// pongo a correr el rmiregistry 
			LocateRegistry.createRegistry(Integer.parseInt(puerto));
			// instanciomi Objeto Remoto y lo publico 
			Fachada fachada = Fachada.getInstancia();
			System.out.println("Antes de publicarlo");
			Naming.rebind("//"+ip+":"+puerto+"/fachada", fachada);
			System.out.println("Luego de publicarlo");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
 
}


