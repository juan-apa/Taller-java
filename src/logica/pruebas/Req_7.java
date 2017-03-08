package logica.pruebas;

import java.util.Date;

import logica.ValueObjects.VOBoleto;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOExcursion;
import logica.fachada.Fachada;

public class Req_7 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Fachada f = Fachada.getInstancia();
		try {
			f.registroNuevoBus(new VOBus("aaa001", "Marca x", 25));
			f.registroNuevaExcursion(new VOExcursion("001", "Chuy", new Date(2017, 12, 21, 10, 10), new Date(2017, 12, 21, 10, 50), 50.00));
			
			VOBoleto boleto = new VOBoleto("001", "Montevideo", 20, 20000000, "Comun", 1);
			VOBoleto boleto1 = new VOBoleto("001", "Atlantida",19, 94755770, "Especial", 2);
			f.ventaBoleto(boleto);
			f.ventaBoleto(boleto1);
			System.out.println("\n"+ f.getExcursiones().find("001").getBoletos().toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
	}
}
