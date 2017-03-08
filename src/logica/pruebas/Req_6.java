package logica.pruebas;

import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.colecciones.Datos;
import logica.colecciones.Iterador;
import logica.fachada.Fachada;

public class Req_6 {
	public static void main(String[] args){
		Fachada f = Fachada.getInstancia();
		try {
			f.registroNuevoBus(new VOBus("aaa001", "Marca x", 25));
			f.respaldar();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (Exc_Persistencia e) {
			e.printStackTrace();
		} catch (Exc_Bus e) {
			e.printStackTrace();
		} catch (Exc_Buses e) {
			e.printStackTrace();
		}
		System.out.println("\n\nGuardo");
		
		
		//Vacío la fachada para luego recuperar los datos del disco
		try {
			f.setDatos(new Datos());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		try {
			f.recuperar();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (Exc_Persistencia e) {
			e.printStackTrace();
		}
		
		try{
		Iterador<VOBusExc> itegenbus = f.listadoGeneralBuses();
		System.out.println("\n");
		while(itegenbus.hasNext()){
			VOBusExc vobusexcaux = itegenbus.next();
			System.out.println(vobusexcaux.toString());
		}
		System.out.println("\nRecupero");
		}catch (Exc_Buses e) {
			System.out.println("Advertencia: " + e.toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
