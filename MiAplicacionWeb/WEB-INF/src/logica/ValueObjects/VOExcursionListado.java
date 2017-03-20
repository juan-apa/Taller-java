/*VO utilizado para los requerimientos:
 * req. 3
 * req. 10
 * req. 11*/

package logica.ValueObjects;

import java.io.Serializable;
import java.util.Date;


public class VOExcursionListado implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String destino;
	private Date hPartida;
	private Date hLlegada;
	private double precioBase;
	private int asientosDisp;
	
	public VOExcursionListado(){
		super();
		this.codigo = "";
		this.destino = "";
		this.hPartida = new Date();
		this.hLlegada = new Date();
		this.precioBase = 0.0;
		this.asientosDisp = 0;
	}

	public VOExcursionListado(String codigo, String destino, Date hPartida, Date hLlegada, double precioBase, int asientosDisp) {
		super();
		this.codigo = codigo;
		this.destino = destino;
		this.hPartida = hPartida;
		this.hLlegada = hLlegada;
		this.precioBase = precioBase;
		this.asientosDisp = asientosDisp;
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

	public Date gethPartida() {
		return hPartida;
	}

	public void sethPartia(Date hPartia) {
		this.hPartida = hPartia;
	}

	public Date gethLlegada() {
		return hLlegada;
	}

	public void sethLlegada(Date hLlegada) {
		this.hLlegada = hLlegada;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public int getAsientosDisp() {
		return asientosDisp;
	}

	public void setAsientosDisp(int asientosDisp) {
		this.asientosDisp = asientosDisp;
	}
	
	public String toString() {
		return "VOExcursionListado [codigo=" + codigo + ", destino=" + destino
				+ ", hPartia=" + hPartida + ", hLlegada=" + hLlegada
				+ ", precioBase=" + precioBase + ", asientosDisp="
				+ asientosDisp + "]";
	}
}
