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

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exc_Excursiones {
		try {
			Propiedades p = new Propiedades();
			String puerto = p.buscar("Puerto");
			String ip = p.buscar("Ip");
			IFachada fachada = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
			
			try {
				System.out.println("Se registran los siguientes buese: \n Matricula:'aaa001' Marca:'Suzuki' Cap:15 \n Matricula:'aaa002' Marca:'VW' Cap:16 \n Matricula:'aaa003' Marca:'BMW' Cap:17");
				fachada.registroNuevoBus(new VOBus("aaa001", "Suzuki", 15));
				fachada.registroNuevoBus(new VOBus("aaa002", "VW", 16));
				fachada.registroNuevoBus(new VOBus("aaa003", "BMW", 17));
				System.out.println("\nSe registran las excursiones 001 y 002");
				fachada.registroNuevaExcursion(new VOExcursion("001", "Montevideo", new Date(2017, 2, 19, 10, 10), new Date(2017, 2, 19, 10, 50), 200));
				fachada.registroNuevaExcursion(new VOExcursion("002", "Montevideo", new Date(2017, 2, 19, 10, 11), new Date(2017, 2, 19, 10, 21), 250));		
			} catch (Exc_Bus e1) {
				System.out.println(e1.toString());
				//e1.printStackTrace();
			} catch (Exc_Buses e1) {
				System.out.println(e1.toString());
				//e1.printStackTrace();
			}catch (Exc_Excursion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//while(true){
				
			try{
				System.out.println("\nBuses registrados:");
				Iterador<VOBusExc> ite = fachada.listadoGeneralBuses();
				while(ite.hasNext()){
					System.out.println(ite.next().toString());
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			

			try{
				System.out.println("\nListado de excursiones del bus aaa001:");
				Iterador<VOExcursionListado> itereq3 = fachada.listadoExcursionesDeBus("aaa001");
				while(itereq3.hasNext()){
					System.out.println(itereq3.next().toString());
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			
			try{
				System.out.println("\nListado de excursiones por destino MONTEVIDEO:");
				Iterador<VOExcursionListado> iter10 = fachada.listadoExcursionesDestino("Montevideo");
				while(iter10.hasNext()){	
					VOExcursionListado exLi = iter10.next();
					System.out.println(exLi.toString());
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			
			try{
				System.out.println("\nListado de excursiones con precio entre 100 y 210:");
				Iterador<VOExcursionListado> iter10 = fachada.listadoExcursionesPrecio(100, 210);
				while(iter10.hasNext()){	
					VOExcursionListado exLi1 = iter10.next();
					System.out.println(exLi1.toString());
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			try{
				System.out.println("\nListado de excursiones con precio entre 100 y 300:");
				Iterador<VOExcursionListado> iter10 = fachada.listadoExcursionesPrecio(100, 300);
				while(iter10.hasNext()){	
					VOExcursionListado exLi1 = iter10.next();
					System.out.println(exLi1.toString());
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			
			
			
			
			try{
				System.out.println("\nRecaudado en excursion 001");
				fachada.recaudadoEnExcursion("001");
				System.out.println("\n\n");
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			try{
				System.out.println("\nBuses registrados:");
				Iterador<VOBusExc> ite = fachada.listadoGeneralBuses();
				while(ite.hasNext()){
					System.out.println(ite.next().toString());
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
					
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		} catch (Exc_Persistencia e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
					

	}
}
