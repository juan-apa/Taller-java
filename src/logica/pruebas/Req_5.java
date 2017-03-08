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

public class Req_5 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Fachada f = Fachada.getInstancia();
		VOBus vob1 = new VOBus("aaa001", "mercedes", 12);
		VOBus vob2 = new VOBus("zzz999", "Suzuki", 13);
		VOExcursion voe1 = new VOExcursion("001", "punta", new Date(2017, 10, 10, 10, 10), new Date(2017, 10, 10, 10, 50), 50.0);
		try {
			f.registroNuevoBus(vob1);
			f.registroNuevaExcursion(voe1);
			f.registroNuevoBus(vob2);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exc_Bus e) {
			e.printStackTrace();
		} catch (Exc_Buses e) {
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			e.printStackTrace();
		} catch (Exc_Excursion e) {
			e.printStackTrace();
		}
		
		try {
			String Bus1 = f.getBuses().obtenerBusConExcursion("001").getMatricula();
			f.reasignacionExcursion("001");
			String Bus2 = f.getBuses().obtenerBusConExcursion("001").getMatricula();
			if(Bus1 != Bus2){
				System.out.println("\nSe cambio correctamente de bus");
			}else{
				System.out.println("\nNo se cambio de bus");
			}
		} catch (RemoteException | Exc_Buses | Exc_Excursiones e) {
			e.printStackTrace();
		}
		
	}

}
