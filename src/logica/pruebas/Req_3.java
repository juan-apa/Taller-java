package logica.pruebas;

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
import logica.fachada.Fachada;

public class Req_3 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Fachada f = Fachada.getInstancia();
		try{
			f.registroNuevoBus(new VOBus("aaa001", "Marca x", 25));
			f.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 21, 10, 10), new Date(2017, 12, 21, 10, 50), 50.00));
			f.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, "Comun", 0.0));
			f.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, "Especial", 0.10));
			Iterator<VOExcursionListado> itereq3 = f.listadoExcursionesDeBus("aaa001");
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
		} catch (Exc_Excursion e) {
			e.printStackTrace();
		} catch (Exc_Boleto e) {
			e.printStackTrace();
		} catch (Exc_Boletos e) {
			e.printStackTrace();
		}
	}
}
