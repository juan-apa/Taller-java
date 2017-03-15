/*VO utilizado para los requerimientos:
 *req. 7 */

package logica.ValueObjects;

import java.io.Serializable;

public class VOBoleto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codExcursion;
	private String lugarPrecedencia;
	private int edadPasajero;
	private long nroCelular;
	private double dtoAdicional;
	
	public VOBoleto(){
		super();
		this.codExcursion = "";
		this.lugarPrecedencia = "";
		this.edadPasajero = 1;
		this.nroCelular = 0;
		this.dtoAdicional = 0.0;
	}
	
	public VOBoleto(String codExcursion, String lugarPrecedencia, int edadPasajero, long nroCelular, String tipoBoleto, double dtoAdicioal) {
		super();
		this.codExcursion = codExcursion;
		this.lugarPrecedencia = lugarPrecedencia;
		this.edadPasajero = edadPasajero;
		this.nroCelular = nroCelular;
		this.dtoAdicional = dtoAdicioal;
	}

	public String getCodExcursion() {
		return codExcursion;
	}

	public void setCodExcursion(String codExcursion) {
		this.codExcursion = codExcursion;
	}

	public String getLugarPrecedencia() {
		return lugarPrecedencia;
	}

	public void setLugarPrecedencia(String lugarPrecedencia) {
		this.lugarPrecedencia = lugarPrecedencia;
	}

	public int getEdadPasajero() {
		return edadPasajero;
	}

	public void setEdadPasajero(int edadPasajero) {
		this.edadPasajero = edadPasajero;
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
