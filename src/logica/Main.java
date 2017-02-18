package logica;

import java.util.Date;

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
	}

}
