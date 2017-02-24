/*VO utilizado para los requerimientos:
 *req. 9 */

package logica.ValueObjects;

import java.io.Serializable;

public class VOBoleto2 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private int edad;
	private String lugarProcedencia;
	private long nroCelular;
	private double dtoAdicional;
	/*Si el tipo de boleto es comun, el descuento adicional se pondra en 0*/
	
	
	
	public VOBoleto2(int numero, int edad, String lugarProcedencia, long nroCelular, double dtoAdicional) {
		super();
		this.numero = numero;
		this.edad = edad;
		this.lugarProcedencia = lugarProcedencia;
		this.nroCelular = nroCelular;
		this.dtoAdicional = dtoAdicional;
	}

	public VOBoleto2(int numero, int edad, String lugarProcedencia, long nroCelular) {
		super();
		this.numero = numero;
		this.edad = edad;
		this.lugarProcedencia = lugarProcedencia;
		this.nroCelular = nroCelular;
		this.dtoAdicional= 0.0;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getLugarProcedencia() {
		return lugarProcedencia;
	}

	public void setLugarProcedencia(String lugarProcedencia) {
		this.lugarProcedencia = lugarProcedencia;
	}

	public long getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(long nroCelular) {
		this.nroCelular = nroCelular;
	}

	public double getDtoAdicional() {
		return dtoAdicional;
	}

	public void setDtoAdicional(double dtoAdicional) {
		this.dtoAdicional = dtoAdicional;
	}
	
}
