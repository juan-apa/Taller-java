package logica.objetos;

import logica.objetos.Boleto;

public class Especial extends Boleto{
	double dtoAdicional;
	
	
	public Especial(String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR, double DTOADICIONAL) {
		super(LUGARPRECEDENCIA, EDADPASAJERO, NROCELULAR);
		// TODO Auto-generated constructor stub
		this.dtoAdicional = DTOADICIONAL;
		Especial.setTipo("Especial");
	}
	
	public double getDtoAdicional() {
		return dtoAdicional;
	}

	public void setDtoAdicional(double dtoAdicional) {
		this.dtoAdicional = dtoAdicional;
	}

}