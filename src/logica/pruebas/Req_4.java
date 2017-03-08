package logica.pruebas;

import java.util.Date;

import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOExcursion;
import logica.fachada.Fachada;

public class Req_4 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Fachada f = Fachada.getInstancia();
		VOBus bus3 = new VOBus("aaa011", "marcaRandom", 15);
		try {
			f.registroNuevoBus(bus3);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		VOExcursion exc1 = new VOExcursion("001", "Montevideo", new Date(2017, 2, 19, 10, 10), new Date(2017, 2, 19, 10, 50), 200);
		VOExcursion exc2 = new VOExcursion("002", "Montevideo", new Date(2017, 2, 19, 10, 51), new Date(2017, 2, 19, 10, 55), 250);
		try {
			f.registroNuevaExcursion(exc1);
			f.registroNuevaExcursion(exc2);
			System.out.println("\n\nExcursiones global: "+ f.getExcursiones().toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
