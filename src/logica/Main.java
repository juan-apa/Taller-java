package logica;

import java.util.Date;
import logica.colecciones.Boletos;
import logica.colecciones.Buses;

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
	}

}
