package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;

public class Cliente2 {

	public static void main(String[] args) throws Exc_Excursiones {
		try {
			IFachada fachada = (IFachada) Naming.lookup("//localhost:1099/fachada");
			
			try {
				fachada.registroNuevoBus(new VOBus("aaa001", "Suzuki", 15));
				
			} catch (Exc_Bus e1) {
				e1.printStackTrace();
			} catch (Exc_Buses e1) {
				e1.printStackTrace();
			}
			
			while(true){
				try{
					Iterador<VOBusExc> ite = fachada.listadoGeneralBuses();
					while(ite.hasNext()){
						System.out.println(ite.next().toString());
					}
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
			}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
					

	}
}
