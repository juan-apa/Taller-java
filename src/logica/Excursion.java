package logica;

import java.util.Date;
import java.io.Serializable;

public class Excursion implements Serializable{
	private String codigo;
	private String destino;
	private Date hpartida;
	private Date hllegada;
	private double precioBase;
	
	public Excursion(String codigo, String destino, Date hpartida,
			Date hllegada, double precioBase) {
		super();
		this.codigo = codigo;
		this.destino = destino;
		this.hpartida = hpartida;
		this.hllegada = hllegada;
		this.precioBase = precioBase;
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
	
}
