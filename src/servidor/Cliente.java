package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import persistencia.Propiedades;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursion;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;

public class Cliente {

	public static void main(String[] args) throws Exc_Excursiones {
		try {
			Propiedades p = new Propiedades();
			String puerto = p.buscar("Puerto");
			String ip = p.buscar("Ip");
			IFachada fachada = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
			
			try {
				//Listo los buses ingresados en el servidor
				String aux = "asd123";
				if(aux.matches("[a-z]")){
					System.out.println("entro");
				}
				
				Iterador<VOBusExc> itegenbus = fachada.listadoGeneralBuses();//f.listadoGeneralBuses();
				while(itegenbus.hasNext()){
					VOBusExc vobusexcaux = itegenbus.next();
					System.out.println(vobusexcaux.toString());
				}

			} catch (Exc_Buses e1) {
				System.out.println(e1.toString());
				//e1.printStackTrace();
			}					
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		} catch (Exc_Persistencia e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
					

	}
}
