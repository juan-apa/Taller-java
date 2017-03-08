package logica;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;

import logica.colecciones.*;
import logica.Excepciones.colecciones.*;
import logica.Excepciones.objetos.*;
import logica.fachada.Fachada;
import logica.objetos.*;
import logica.ValueObjects.*;



public class Main {

	public static void main(String[] args){
		
		// TODO Auto-generated method stub
		Bus busAux = new Bus("matricula", "marca", 4);
		Buses bs = new Buses();
		bs.insert(busAux);		
		Boleto bcomun = new Boleto("Comun", 0, 0);
		Especial bespecial = new Especial("Especial", 0, 0, 0.8);
		Boletos boletos = new Boletos(3);
		boletos.insert(bcomun);
		boletos.insert(bespecial);
		/*Testeo requerimientos*/
		Fachada f = Fachada.getInstancia();
		/*Test Requerimiento 1*/
		System.out.println("\n\nTest Requerimiento 1.");
		try {

			VOBus vobus = new VOBus("aaa009", "Suzuki", 5);
			f.registroNuevoBus(vobus);
			vobus.setMatricula("aaa010");
			vobus.setCapPasajeros(30);
			VOBus vobus1 = new VOBus("aaa008", "Chino", 5);
			f.registroNuevoBus(vobus1);
		} catch (Exc_Bus e) {
			e.printStackTrace();
		} catch (Exc_Buses e) {
			e.printStackTrace();
		}
		catch(RemoteException e){
			System.out.println(e.toString());
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
		catch(RemoteException e){
			System.out.println(e.toString());
		}
	
		/*Test Requerimiento 3*/
		System.out.println("\n\nTest Requerimiento 3.");
		try{
			Iterator<VOExcursionListado> itereq3 = f.listadoExcursionesDeBus("aaa009");
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
		
		/*Test Requerimiento 4*/
		System.out.println("\n\nTest Requerimiento 4.");
		VOBus bus3 = new VOBus("aaa011", "marcaRandom", 15);
		try {
			f.registroNuevoBus(bus3);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		VOExcursion exc1 = new VOExcursion("001", "Montevideo", new Date(2017, 2, 19, 10, 10), new Date(2017, 2, 19, 10, 50), 200);
		VOExcursion exc2 = new VOExcursion("002", "Montevideo", new Date(2017, 2, 19, 10, 11), new Date(2017, 2, 19, 10, 21), 250);
		try {
			f.registroNuevaExcursion(exc1);
			f.registroNuevaExcursion(exc2);
			System.out.println("\n\nExcursiones global: "+ f.getExcursiones().toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		/*Test Requerimiento 5*/
		try {
			System.out.println("\n\nTest Requerimiento 5.");
			String Bus1 = new String();
			Bus1 = f.getBuses().obtenerBusConExcursion("001").getMatricula();
			f.reasignacionExcursion("001");
			String Bus2 = new String();
			Bus2 = f.getBuses().obtenerBusConExcursion("001").getMatricula();
			System.out.println(Bus1);
			if(Bus1 == Bus2){
				System.out.println("\nSe cambio correctamente de bus");
			}else{
				System.out.println("\nNo se cambio de bus");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		/*Test Requerimiento 6*/
		System.out.println("\nTest Requerimiento 6.");
		try {
			f.respaldar();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exc_Persistencia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\nGuardo");
		//Vacio la fachada para luego recuperar los datos del disco
		try {
			f.setDatos(new Datos());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			f.recuperar();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exc_Persistencia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		Iterator<VOBusExc> itegenbus = f.listadoGeneralBuses();
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
		/*Test Requerimiento 7*/
		try {
			System.out.println("\nTest Requerimiento 7.");
			VOBoleto boleto = new VOBoleto("001", "Montevideo", 20, 20000000, "Comun", 1);
			Excursion ex = f.getExcursiones().find("001");
			VOBoleto boleto1 = new VOBoleto("001", "Atlantida",19, 94755770, "Comun", 2);
			Boletos bolet = new Boletos(10);
			ex.setBoletos(bolet);
			f.ventaBoleto(boleto);
			f.ventaBoleto(boleto1);
			System.out.println("\n"+ f.getExcursiones().find("001").getBoletos().toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		
		/*Test Requerimiento 8*/
		try {
			System.out.println("\nTest Requerimiento 8.");
			double total;
			total = f.recaudadoEnExcursion("001");
			System.out.println("\nEl total recaudado es: "+total);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		System.out.println("\nTest Requerimiento 9.");
		String codigoExcu = "001";
		String tipoBoleto = "Especial";
		try {
			Iterator<VOBoleto2> iter8 = f.listadoBoletosExcursion("001", "Especial");
			while(iter8.hasNext()){
				VOBoleto2 bo2 = iter8.next();
				System.out.println(bo2.toString());
			}
		} catch (Exc_Boletos e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR:\n");
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("\nTest Requerimiento 10.");
		try {
			Iterator<VOExcursionListado> iter10 = f.listadoExcursionesDestino("Montevideo");
			while(iter10.hasNext()){	
				VOExcursionListado exLi = iter10.next();
				System.out.println(exLi.toString());
			}
		} catch (RemoteException e) {
			System.out.println("CATCH 1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			// TODO Auto-generated catch block
			System.out.println("CATCH 2");
			e.printStackTrace();
		}
		
		System.out.println("\nTest Requerimiento 11.");
		try {
			Iterator<VOExcursionListado> iter10 = f.listadoExcursionesPrecio(150, 220);
			while(iter10.hasNext()){	
				VOExcursionListado exLi1 = iter10.next();
				System.out.println(exLi1.toString());
			}
		} catch (RemoteException e) {
			System.out.println("CATCH 1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			// TODO Auto-generated catch block
			System.out.println("CATCH 2");
			e.printStackTrace();
		}
	}

}
