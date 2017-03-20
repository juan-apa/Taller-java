package logica;

import java.rmi.RemoteException;
import java.util.Date;

import logica.colecciones.*;
import logica.Excepciones.colecciones.*;
import logica.fachada.Fachada;
import logica.objetos.*;
import vistaGrafica.controladoras.Controladora;
import logica.ValueObjects.*;



public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Fachada fachada = Fachada.getInstancia();
		
		try{
			fachada.registroNuevoBus(new VOBus("AAA001", "asd", 4));
			fachada.registroNuevoBus(new VOBus("AAA002", "asddasd", 5));
			fachada.registroNuevaExcursion(new VOExcursion("ch001", "monte", new Date(2017, 10, 10, 10, 10), new Date(2017, 10, 10, 10, 20), 200.0));
			fachada.ventaBoleto(new VOBoleto("ch001", "monte", 15, 91281074, 0.0));
			fachada.reasignacionExcursion("ch001");
			Iterador<VOBoleto2> ite = fachada.listadoBoletosExcursion("ch001", "Comun");
			
			while(ite.hasNext()){
				System.out.println(ite.next().toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
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
		Controladora c = new Controladora();
//		/*Test Requerimiento 1*/
//		System.out.println("\n\nTest Requerimiento 1.");
//		try {
//
//			VOBus vobus = new VOBus("aaa009", "Suzuki", 5);
//			//f.registroNuevoBus(vobus);
//			c.registroNuevoBus(f, vobus);
//			vobus.setMatricula("aaa010");
//			vobus.setCapPasajeros(30);
//			VOBus vobus1 = new VOBus("aaa008", "Chino", 5);
//			//f.registroNuevoBus(vobus1);
//			c.registroNuevoBus(f, vobus1);
//		} catch (Exc_Bus e) {
//			e.printStackTrace();
//		} catch (Exc_Buses e) {
//			e.printStackTrace();
//		}
//		catch(RemoteException e){
//			System.out.println(e.toString());
//		}
//		
//		/*Test Requerimiento 2 (cont.)*/
//		System.out.println("\n\nTest Requerimiento 2.");
//		try {
//			Iterador<VOBusExc> itegenbus = c.listadoGeneralBuses(f);//f.listadoGeneralBuses();
//			while(itegenbus.hasNext()){
//				VOBusExc vobusexcaux = itegenbus.next();
//				System.out.println(vobusexcaux.toString());
//			}
//			System.out.println("Requerimiento 2 con exito.");
//		} catch (Exc_Buses e) {
//			System.out.println("Advertencia: " + e.toString());
//		}
//		catch(RemoteException e){
//			System.out.println(e.toString());
//		}
//	
//		/*Test Requerimiento 3*/
//		System.out.println("\n\nTest Requerimiento 3.");
//		try{
//			Iterador<VOExcursionListado> itereq3 = f.listadoExcursionesDeBus("aaa009");
//			while(itereq3.hasNext()){
//				System.out.println(itereq3.next().toString());
//			}
//		}
//		catch(Exc_Bus ebus){
//			System.out.println(ebus.toString());
//		}
//		catch(Exc_Buses ebuses){
//			System.out.println(ebuses.toString());
//		}
//		catch(Exc_Excursiones eexcs){
//			System.out.println(eexcs.toString());
//		}
//		catch(RemoteException e){
//			System.out.println(e.toString());
//		}
//		
//		/*Test Requerimiento 4*/
//		System.out.println("\n\nTest Requerimiento 4.");
//		VOBus bus3 = new VOBus("aaa011", "marcaRandom", 15);
//		try {
//			c.registroNuevoBus(f, bus3);//f.registroNuevoBus(bus3);
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		VOExcursion exc1 = new VOExcursion("001", "Montevideo", new Date(2017, 2, 19, 10, 10), new Date(2017, 2, 19, 10, 50), 200);
//		VOExcursion exc2 = new VOExcursion("002", "Montevideo", new Date(2017, 2, 19, 10, 11), new Date(2017, 2, 19, 10, 21), 250);
//		try {
//			c.registroNuevaExcursion(f, exc1);//f.registroNuevaExcursion(exc1);
//			c.registroNuevaExcursion(f, exc2);////f.registroNuevaExcursion(exc2);
//			System.out.println("\n\nExcursiones global: "+ f.getExcursiones().toString());
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		
//		/*Test Requerimiento 5*/
//		try {
//			System.out.println("\n\nTest Requerimiento 5.");
//			String Bus1 = new String();
//			Bus1 = f.getBuses().obtenerBusConExcursion("001").getMatricula();
//			c.reasignacionExcursion(f, "001");//f.reasignacionExcursion("001");
//			String Bus2 = new String();
//			Bus2 = f.getBuses().obtenerBusConExcursion("001").getMatricula();
//			System.out.println(Bus1);
//			if(Bus1 == Bus2){
//				System.out.println("\nSe cambio correctamente de bus");
//			}else{
//				System.out.println("\nNo se cambio de bus");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//		
//		
//		/*Test Requerimiento 6*/
//		System.out.println("\nTest Requerimiento 6.");
//		try {
//			c.respaldar(f);//;f.respaldar();
//		} catch (RemoteException e1) {
//			e1.printStackTrace();
//		} catch (Exc_Persistencia e) {
//			e.printStackTrace();
//		}
//		System.out.println("\n\nGuardo");
//		//Vacio la fachada para luego recuperar los datos del disco
//		try {
//			f.setDatos(new Datos());
//		} catch (RemoteException e1) {
//			e1.printStackTrace();
//		}
//
//		try {
//			c.recuperar(f); //f.recuperar();
//			Iterador<VOBusExc> itegenbus = f.listadoGeneralBuses();
//			System.out.println("\n");
//			while(itegenbus.hasNext()){
//				VOBusExc vobusexcaux = itegenbus.next();
//				System.out.println(vobusexcaux.toString());
//			}
//			System.out.println("\nRecupero");
//		}catch (Exc_Buses e) {
//			System.out.println("Advertencia: " + e.toString());
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		} catch (Exc_Persistencia e) {
//			e.printStackTrace();
//		}
//		/*Test Requerimiento 7*/
//		try {
//			System.out.println("\nTest Requerimiento 7.");
//			VOBoleto boleto = new VOBoleto("001", "Montevideo", 20, 20000000, "Comun", 1);
//			Excursion ex = f.getExcursiones().find("001");
//			VOBoleto boleto1 = new VOBoleto("001", "Atlantida",19, 94755770, "Comun", 2);
//			Boletos bolet = new Boletos(10);
//			ex.setBoletos(bolet);
//			c.ventaBoleto(f, boleto); //f.ventaBoleto(boleto);
//			c.ventaBoleto(f, boleto1); //f.ventaBoleto(boleto1);
//			System.out.println("\n"+ f.getExcursiones().find("001").getBoletos().toString());
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}	
//		
//		/*Test Requerimiento 8*/
//		try {
//			System.out.println("\nTest Requerimiento 8.");
//			double total;
//			total = f.recaudadoEnExcursion("001");
//			System.out.println("\nEl total recaudado es: "+total);
//			
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		
		System.out.println("\nTest Requerimiento 9.");

		try {
			Iterador<VOBoleto2> iter8 = c.listadoBoletosExcursion(f, "b", "Especial"); //f.listadoBoletosExcursion("001", "Especial");
			while(iter8.hasNext()){
				VOBoleto2 bo2 = iter8.next();
				System.out.println(bo2.toString());
			}
		} catch (Exc_Boletos e) {
			System.out.println("ERROR:\n");
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			e.printStackTrace();
		} 
		
//		System.out.println("\nTest Requerimiento 10.");
//		try {
//			Iterador<VOExcursionListado> iter10 = c.listadoExcursionesDestino(f, "Montevideo"); //f.listadoExcursionesDestino("Montevideo");
//			while(iter10.hasNext()){	
//				VOExcursionListado exLi = iter10.next();
//				System.out.println(exLi.toString());
//			}
//		} catch (RemoteException e) {
//			System.out.println("CATCH 1");
//			e.printStackTrace();
//		} catch (Exc_Excursiones e) {
//			System.out.println("CATCH 2");
//			e.printStackTrace();
//		}
//		
//		System.out.println("\nTest Requerimiento 11.");
//		try {
//			Iterador<VOExcursionListado> iter10 = c.listadoExcursionesPrecio(f, 150, 200);// f.listadoExcursionesPrecio(150, 220);
//			while(iter10.hasNext()){	
//				VOExcursionListado exLi1 = iter10.next();
//				System.out.println(exLi1.toString());
//			}
//		} catch (RemoteException e) {
//			System.out.println("CATCH 1");
//			e.printStackTrace();
//		} catch (Exc_Excursiones e) {
//			System.out.println("CATCH 2");
//			e.printStackTrace();
//		}
	}

	@SuppressWarnings("unused")
	private static Controladora Controladora() {
		// TODO Auto-generated method stub
		return null;
	}

}
