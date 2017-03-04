package logica.objetos;

import java.util.Date;

import logica.colecciones.Boletos;

import java.io.Serializable;

public class Excursion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		/* TODO Agregarle la capMaxima de boletos*/
		this.boletos = new Boletos(0);
	}

	public Excursion() {
		// TODO Auto-generated constructor stub
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
	
	public void actualizarCantBoletos(int nuevaCap){
		this.boletos.actualizarMax(nuevaCap);
	}

	@Override
	public String toString() {
		return "Excursion [codigo=" + codigo + ", destino=" + destino + ", hpartida=" + hpartida + ", hllegada="
				+ hllegada + ", precioBase=" + precioBase + ", boletos=" + boletos + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = true;
		if(this.codigo != ((Excursion) obj).getCodigo()){
			iguales = false;
		}
		if(!this.destino.equals(((Excursion) obj).getDestino())){
			iguales = false;
		}
		if( (this.boletos == null && ((Excursion) obj).getBoletos() != null) || (this.boletos != null && ((Excursion) obj).getBoletos() == null)){
			iguales = false;
		}
		else{
			if(this.boletos == null && ((Excursion) obj).getBoletos() == null){
				//igual = true;
			}
			else{
				if(!this.boletos.equals(((Excursion) obj).getBoletos())){
					iguales = false;
				}
			}
		}
		
		if(!this.hllegada.equals(((Excursion) obj).getHllegada())){
			iguales = false;
		}
		if(this.hpartida.equals(((Excursion) obj).getHpartida())){
			iguales = false;
		}
		if(this.precioBase != ((Excursion) obj).getPrecioBase()){
			iguales = false;
		}		
		// TODO Auto-generated method stub
		return iguales;
		
	}

	public boolean dentroRango(double precioMin, double precioMax) {
		boolean dentroRango = false;
		if(precioMin <= this.precioBase && this.precioBase <= precioMax){
			dentroRango = true;
		}
		return dentroRango;
	}
	
	
	
}
