package logica;

import java.util.Date;

import logica.colecciones.Boletos;

import java.io.Serializable;

public class Excursion implements Serializable{
	private String codigo;
	private String destino;
	private Date hpartida;
	private Date hllegada;
	private double precioBase;
	private Boletos boletos;
	
	public Excursion(String codigo, String destino, Date hpartida,
			Date hllegada, double precioBase) {
		super();
		this.codigo = codigo;
		this.destino = destino;
		this.hpartida = hpartida;
		this.hllegada = hllegada;
		this.precioBase = precioBase;
		this.boletos = null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getHpartida() {
		return hpartida;
	}

	public void setHpartida(Date hpartida) {
		this.hpartida = hpartida;
	}

	public Date getHllegada() {
		return hllegada;
	}

	public void setHllegada(Date hllegada) {
		this.hllegada = hllegada;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public Boletos getBoletos() {
		return boletos;
	}

	public void setBoletos(Boletos boletos) {
		this.boletos = boletos;
	}
	
}
