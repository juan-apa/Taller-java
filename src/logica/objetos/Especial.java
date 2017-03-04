package logica.objetos;

import logica.objetos.Boleto;

public class Especial extends Boleto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double dtoAdicional;
	
	
	public Especial(String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR, double DTOADICIONAL) {
		super(LUGARPRECEDENCIA, EDADPASAJERO, NROCELULAR);
		// TODO Auto-generated constructor stub
		this.dtoAdicional = DTOADICIONAL;
		Especial.setTipo("Especial");
	}
	
	public Especial(Boleto boleto){
		super(boleto);
		this.setTipo("Especial");
	}
	
	public double getDtoAdicional() {
		return dtoAdicional;
	}

	public void setDtoAdicional(double dtoAdicional) {
		this.dtoAdicional = dtoAdicional;
	}

}