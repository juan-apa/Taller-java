package logica.objetos;

import java.io.Serializable;

public class Boleto implements Serializable{
	private String lugarPrecedencia;
	private int edadPasajero;
	private long nroCelular;
	private static String tipo = "Comun";
	
	public Boleto(String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR){
		this.lugarPrecedencia = LUGARPRECEDENCIA;
		this.edadPasajero = EDADPASAJERO;
		this.nroCelular = NROCELULAR;
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
	
	public static String getTipo() {
		return tipo;
	}

	public static void setTipo(String tipo) {
		Boleto.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Boleto [lugarPrecedencia=" + lugarPrecedencia + ", edadPasajero="
				+ edadPasajero + ", nroCelular=" + nroCelular + "]";
	}
		
}
