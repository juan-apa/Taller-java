package logica.pruebas;

import java.rmi.RemoteException;
import java.util.Date;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOExcursion;
import logica.fachada.Fachada;
import logica.objetos.Bus;

public class PruebaReq5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fachada f = Fachada.getInstancia();
		VOBus vob1 = new VOBus("aaa001", "mercedes", 12);
		VOBus vob2 = new VOBus("zzz999", "Suzuki", 13);
		VOExcursion voe1 = new VOExcursion("001", "punta", new Date(2017, 10, 10, 10, 10), new Date(2017, 10, 10, 10, 50), 50.0);
		try {
			f.registroNuevoBus(vob1);
			f.registroNuevaExcursion(voe1);
			f.registroNuevoBus(vob2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Bus e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Buses e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			f.reasignacionExcursion("001");
		} catch (RemoteException | Exc_Buses | Exc_Excursiones e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
