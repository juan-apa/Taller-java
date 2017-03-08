package cliente;

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
import logica.ValueObjects.VOExcursion;
import logica.ValueObjects.VOExcursionListado;
import logica.fachada.IFachada;

public class Cliente {

	public static void main(String[] args) {
		try {
			
			IFachada fachada = (IFachada) Naming.lookup("//LAPTOP-JUAN:1099/fachada");
//			fachada.registroNuevoBus(new VOBus("aba001", "Marca x", 25));
//			fachada.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 21, 10, 10), new Date(2017, 12, 21, 10, 50), 50.00));
//			fachada.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, "Comun", 0.0));
//			fachada.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, "Especial", 0.10));
			Iterator<VOExcursionListado> itereq3 = fachada.listadoExcursionesDeBus("aba001");
			while(itereq3.hasNext()){
				System.out.println(itereq3.next().toString());
				
			}	
			
			fachada.listadoBoletosExcursion("003", "comun");
		}
		catch(Exception e){
			e.printStackTrace();
		}
					

	}
}
