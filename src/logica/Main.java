package logica;

import java.util.Date;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.objetos.Exc_Bus;
import logica.ValueObjects.VOBus;
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
		
		
		Fachada f = new Fachada();
		VOBus vobus = new VOBus("aaa009", "Suzuki", 5);
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
	}

}
