package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Boleto;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.ValueObjects.VOBoleto;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursion;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;

public class Cliente2 {

	public static void main(String[] args) throws Exc_Excursiones {
		try {
			IFachada fachada = (IFachada) Naming.lookup("//localhost:1099/fachada");
			
			try {
				fachada.registroNuevoBus(new VOBus("aaa001", "Suzuki", 15));
				
			} catch (Exc_Bus e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exc_Buses e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while(true){
				try{
//					System.out.println("pasada");
//					fachada.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 10, 10, 10), new Date(2017, 12, 10, 10, 50), 250.0));
//					System.out.println("\n\n");
					Iterador<VOBusExc> ite = fachada.listadoGeneralBuses();
					while(ite.hasNext()){
						System.out.println(ite.next().toString());
					}
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
			}
//			fachada.listadoBoletosExcursion("001", "comun");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
					

	}
}
