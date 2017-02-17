package logica;

import logica.Boleto;

public class Especial extends Boleto{
	double dtoAdicional;
	
	public Especial(int NROBOLETO, String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR, double DTOADICIONAL) {
		super(NROBOLETO, LUGARPRECEDENCIA, EDADPASAJERO, NROCELULAR);
		// TODO Auto-generated constructor stub
		this.dtoAdicional = DTOADICIONAL;
		
	}

}