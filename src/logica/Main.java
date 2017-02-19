package logica;

import java.util.Date;
import java.util.Iterator;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Boletos;
import logica.colecciones.Buses;
import logica.fachada.Fachada;
import logica.objetos.Boleto;
import logica.objetos.Bus;
import logica.objetos.Especial;;

public class Main {

	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
		Bus busAux = new Bus("matricula", "marca", 4);
		System.out.println("hola");
		Buses bs = new Buses();
		System.out.println("Antes de insertar.");
		bs.imprimir();
		bs.insert(busAux);
		System.out.println("Despues de insertar");
		bs.imprimir();
		Date uno = new Date();
		Date dos = new Date();
		boolean aux = bs.hayBusLibre(uno, dos);
		
		
		
		Boleto bcomun = new Boleto(0, "Comun", 0, 0);
		System.out.println("Tipo boleto comun: " + bcomun.getTipo());
		
		Especial bespecial = new Especial(0, "Especial", 0, 0, 0);
		System.out.println("Tipo boleto especial: " + bespecial.getTipo());
		
		//System.out.println("Casteo Comun --> Especial: " + (Especial)bcomun);
		
		Boletos boletos = new Boletos(3);
		boletos.insert(bcomun);
		boletos.insert(bespecial);
		System.out.println("Tipo comun--> " + boletos.getBoletoNro(1).getTipo());
		System.out.println("Tipo especial--> " + boletos.getBoletoNro(2).getTipo());
		try{
			throw new Exc_Bus();
		}catch(Exc_Bus e){
			System.out.println("Mensaje de e: " + e);
		}
		
		/*Testeo requerimientos*/
		Fachada f = new Fachada();
		
		/*Test Requerimiento 2*/
		System.out.println("\n\nTest Requerimiento 2.");
		VOBus vobus = new VOBus("aaa009", "Suzuki", 5);
		try {
			Iterator<VOBusExc> itegenbus = f.listadoGeneralBuses();
		} catch (Exc_Buses e) {
			System.out.println("Advertencia: " + e.toString());
		}
		
		/*Test Requerimiento 1*/
		System.out.println("\n\nTest Requerimiento 1.");
		try {
			f.registroNuevoBus(vobus);
			vobus.setMatricula("aaa010");
			vobus.setCapPasajeros(0);
			f.registroNuevoBus(vobus);
		} catch (Exc_Bus e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Buses e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Test Requerimiento 2 (cont.)*/
		System.out.println("\n\nTest Requerimiento 2.");
		try {
			Iterator<VOBusExc> itegenbus = f.listadoGeneralBuses();
			while(itegenbus.hasNext()){
				VOBusExc vobusexcaux = itegenbus.next();
				System.out.println(vobusexcaux.toString());
			}
			System.out.println("Requerimiento 2 con exito.");
		} catch (Exc_Buses e) {
			System.out.println("Advertencia: " + e.toString());
		}
		
		
		/*Test Requerimiento 3*/
		
		System.out.println("\n\nTest Requerimiento 3.");
		try{
			Iterator<VOExcursionListado> itereq3= f.listadoExcursionesDeBus("aaa009");
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
	}

}
