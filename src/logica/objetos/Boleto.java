package logica.objetos;

import java.io.Serializable;

public class Boleto implements Serializable{
	private int nroBoleto;
	private String lugarPrecedencia;
	private int edadPasajero;
	private long nroCelular;
	private static String tipo = "Comun";
	
	public Boleto(int NROBOLETO, String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR){
		this.nroBoleto = NROBOLETO;
		this.lugarPrecedencia = LUGARPRECEDENCIA;
		this.edadPasajero = EDADPASAJERO;
		this.nroCelular = NROCELULAR;
	}
	
	public int getNroBoleto() {
		return nroBoleto;
	}
	public void setNroBoleto(int nroBoleto) {
		this.nroBoleto = nroBoleto;
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
		return "Boleto [nroBoleto=" + nroBoleto + ", lugarPrecedencia=" + lugarPrecedencia + ", edadPasajero="
				+ edadPasajero + ", nroCelular=" + nroCelular + "]";
	}
		
}