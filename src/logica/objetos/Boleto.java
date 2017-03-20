package logica.objetos;

import java.io.Serializable;

public class Boleto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lugarPrecedencia;
	private int edadPasajero;
	private long nroCelular;
	private String tipo = "Comun";
	
	public Boleto(String LUGARPRECEDENCIA, int EDADPASAJERO, long NROCELULAR){
		super();
		this.lugarPrecedencia = LUGARPRECEDENCIA;
		this.edadPasajero = EDADPASAJERO;
		this.nroCelular = NROCELULAR;
	}
	
	public Boleto(Boleto boleto) {
		super();
		this.edadPasajero = boleto.getEdadPasajero();
		this.lugarPrecedencia = boleto.getLugarPrecedencia();
		this.nroCelular = boleto.getNroCelular();
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
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Boleto [lugarPrecedencia=" + lugarPrecedencia + ", edadPasajero="
				+ edadPasajero + ", nroCelular=" + nroCelular + ", tipo=" +this.tipo+"]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = true;
		if(this.edadPasajero != ((Boleto) obj).getEdadPasajero()){
			iguales = false;
		}
		if(!this.lugarPrecedencia.equals(((Boleto) obj).getEdadPasajero())){
			iguales = false;
		}
		if(this.nroCelular != ((Boleto) obj).getNroCelular()){
			iguales = false;
		}
		return iguales;
	}
	
}
