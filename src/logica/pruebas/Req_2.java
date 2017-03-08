package logica.pruebas;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursion;
import logica.colecciones.Iterador;
import logica.fachada.Fachada;

public class Req_2 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Fachada f = Fachada.getInstancia();
		System.out.println("\n\nTest Requerimiento 2.");
		try {
			f.registroNuevoBus(new VOBus("aaa001", "Marca x", 25));
			f.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 21, 10, 10), new Date(2017, 12, 21, 10, 50), 50.00));
			Iterador<VOBusExc> itegenbus = f.listadoGeneralBuses();
			while(itegenbus.hasNext()){
				VOBusExc vobusexcaux = itegenbus.next();
				System.out.println(vobusexcaux.toString());
			}
		} catch (Exc_Buses e) {
			System.out.println("Advertencia: " + e.toString());
		}
		catch(RemoteException e){
			System.out.println(e.toString());
		} catch (Exc_Bus e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
