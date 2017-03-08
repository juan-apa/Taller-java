package logica.pruebas;

import java.rmi.RemoteException;
import java.util.Date;

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
import logica.colecciones.Iterador;
import logica.fachada.Fachada;

public class Req_11 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Fachada f = Fachada.getInstancia();
		try {
			f.registroNuevoBus(new VOBus("aaa001", "Marca x", 25));
			f.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 21, 10, 10), new Date(2017, 12, 21, 10, 50), 50.00));
			f.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, "Comun", 0.0));
			f.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, "Especial", 0.10));
			Iterador<VOExcursionListado> iter10 = f.listadoExcursionesPrecio(10, 50);
			while(iter10.hasNext()){	
				VOExcursionListado exLi1 = iter10.next();
				System.out.println(exLi1.toString());
			}
		} catch (RemoteException e){
			e.printStackTrace();
		} catch (Exc_Excursiones e){
			e.printStackTrace();
		} catch (Exc_Buses e) {
			e.printStackTrace();
		} catch (Exc_Excursion e){
			e.printStackTrace();
		} catch (Exc_Bus e) {
			e.printStackTrace();
		} catch (Exc_Boleto e) {
			e.printStackTrace();
		} catch (Exc_Boletos e) {
			e.printStackTrace();
		}
	}

}
