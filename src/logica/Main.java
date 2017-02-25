package logica;

import java.util.Date;
import java.util.Iterator;

import vistaGrafica.principal;
import logica.colecciones.*;
import logica.Excepciones.colecciones.*;
import logica.Excepciones.objetos.*;
import logica.fachada.Fachada;
import logica.objetos.*;
import logica.ValueObjects.*;
import java.util.Properties;




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
		
		Boleto bcomun = new Boleto("Comun", 0, 0);
		System.out.println("Tipo boleto comun: " + bcomun.getTipo());
		
		Especial bespecial = new Especial("Especial", 0, 0, 0);
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
		Fachada f = Fachada.getInstancia();
		
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
			e.printStackTrace();
		} catch (Exc_Buses e) {
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
			System.out.println("Termine el try.");
		} catch (Exc_Buses e) {
			System.out.println("Advertencia: " + e.toString());
		}
		
		/*Test random*/
		System.out.println("\n\nTest random.");
		try{
			System.out.println("Antes del throw.");
			throw new Exception();
		}
		catch(Exception e){
			System.out.println("Adentro del catch");
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
		
		/*Test Requerimiento 4*/
		System.out.println("\n\nTest Requerimiento 4.");
		VOBus bus3 = new VOBus("aaa011", "marcaRandom", 15);
		try {
			f.registroNuevoBus(bus3);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		VOExcursion exc1 = new VOExcursion("001", "Montevideo", new Date(2017, 2, 19, 10, 10), new Date(2017, 2, 19, 10, 50), 200);
		VOExcursion exc2 = new VOExcursion("002", "Montevideo", new Date(2017, 2, 19, 10, 11), new Date(2017, 2, 19, 10, 21), 200);
		try {
			f.registroNuevaExcursion(exc1);
			System.out.println(f.getBuses().toString());
			f.registroNuevaExcursion(exc2);
			System.out.println("\n\nExcursiones global: "+ f.getExcursiones().toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
