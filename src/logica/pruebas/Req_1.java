package logica.pruebas;

import java.rmi.RemoteException;
import logica.colecciones.*;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.objetos.Exc_Bus;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.fachada.Fachada;

public class Req_1 {

	public static void main(String[] args) {
		Fachada f = Fachada.getInstancia();
		System.out.println("\n\nTest Requerimiento 1.");
		try {
			VOBus vobus = new VOBus("aaa009", "Suzuki", 5);
			f.registroNuevoBus(vobus);
			VOBus vobus1 = new VOBus("aaa008", "Chino", 5);
			f.registroNuevoBus(vobus1);
			Iterador<VOBusExc> ite = f.listadoGeneralBuses();
			while(ite.hasNext()){
				System.out.println(ite.next());
			}
			f.registroNuevoBus(vobus1);
		} catch (Exc_Bus e) {
			e.printStackTrace();
		} catch (Exc_Buses e) {
			e.printStackTrace();
		}
		catch(RemoteException e){
			System.out.println(e.toString());
		}
	}

}
