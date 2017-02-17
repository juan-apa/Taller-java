package logica;


import logica.Boleto;

public class Especial extends Boleto{
	double dtoAdicional;
	
	public double getDtoAdicional() {
		return dtoAdicional;
	}

	public void setDtoAdicional(double dtoAdicional) {
		this.dtoAdicional = dtoAdicional;
	}

	public Especial(int NROBOLETO, String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR, double DTOADICIONAL) {
		super(NROBOLETO, LUGARPRECEDENCIA, EDADPASAJERO, NROCELULAR);
		// TODO Auto-generated constructor stub
		this.dtoAdicional = DTOADICIONAL;
		
	}

}