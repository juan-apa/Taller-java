/*VO utilizado para los requerimientos:
 * req. 3
 * req. 10
 * req. 11*/

package logica.ValueObjects;

import java.io.Serializable;
import java.util.Date;


public class VOExcursionListado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String destino;
	private Date hPartia;
	private Date hLlegada;
	private double precioBase;
	private int asientosDisp;
	
	public VOExcursionListado(String codigo, String destino, Date hPartia, Date hLlegada, double precioBase, int asientosDisp) {
		super();
		this.codigo = codigo;
		this.destino = destino;
		this.hPartia = hPartia;
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

	public Date gethPartia() {
		return hPartia;
	}

	public void sethPartia(Date hPartia) {
		this.hPartia = hPartia;
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
	
	
}
