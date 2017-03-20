package logica.pruebas;

import java.util.Date;

import logica.ValueObjects.VOBoleto;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOExcursion;
import logica.fachada.Fachada;

public class Req_8 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Fachada f = Fachada.getInstancia();
		try {
			f.registroNuevoBus(new VOBus("aaa001", "Marca x", 25));
			f.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 21, 10, 10), new Date(2017, 12, 21, 10, 50), 50.00));
			f.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, 0.0));
			f.ventaBoleto(new VOBoleto("001", "Montevideo", 20, 20000000, 0.10));
			double total;
			total = f.recaudadoEnExcursion("001");
			System.out.println("\nEl total recaudado es: "+total);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
