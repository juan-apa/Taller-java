package logica;

public class Boleto {
	int nroBoleto;
	String lugarPrecedencia;
	int edadPasajero;
	long nroCelular;
	
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
	
}