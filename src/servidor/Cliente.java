package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import persistencia.Propiedades;
import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBoleto2;
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
			
			
//			VOExcursion entrada = new VOExcursion("001", "Montevideo", new Date(2017, 2, 19, 10, 10), new Date(2017, 2, 19, 10, 50), 200);
//			fachada.registroNuevaExcursion(entrada);
			//Listo los Boletos
			try{
				Iterador<VOExcursionListado> itereq3 = fachada.listadoExcursionesDeBus("2");
				while(itereq3.hasNext()){
					System.out.println(itereq3.next().toString());
				}
			}
			catch(Exc_Bus ebus){
				System.out.println(ebus.toString());
			}
			catch(Exc_Buses ebuses){
				System.out.println(ebuses.toString());
			}
			catch(Exc_Excursiones eexcs){
				System.out.println(eexcs.toString());
			}
			catch(RemoteException e){
				System.out.println(e.toString());
			}
//			try {
//				//Listo los Bus ingresados en el servidor	
//
//				Iterador<VOBusExc> itegenbus = fachada.listadoGeneralBuses();//f.listadoGeneralBuses();
//				while(itegenbus.hasNext()){
//					VOBusExc vobusexcaux = itegenbus.next();
//					System.out.println(vobusexcaux.toString());
//				}
//				//Listo los Boletos
//				Iterador<VOBoleto2> iter8 = null;
//				try {
//					iter8 = fachada.listadoBoletosExcursion("001", "Especial");
//				} catch (Exc_Boletos e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				while(iter8.hasNext()){
//					VOBoleto2 bo2 = iter8.next();
//					System.out.println(bo2.toString());
//				}
//
//			} catch (Exc_Buses e1) {
//				System.out.println(e1.toString());
//				//e1.printStackTrace();
//			} catch (Exc_Excursion e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}					
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		} catch (Exc_Persistencia e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
					

	}
}
