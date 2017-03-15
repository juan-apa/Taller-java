/*VO utilizado para los requerimientos:
 *req. 4 */

package logica.ValueObjects;

import java.io.Serializable;
import java.util.Date;


public class VOExcursion implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String destino;
	private Date hPartida;
	private Date hLlegada;
	private double precioBase;
	
	public VOExcursion(){
		super();
		this.codigo = "";
		this.destino = "";
		this.hPartida = new Date();
		this.hLlegada = new Date();
		this.precioBase = 0.0;
	}
	
	public VOExcursion(String codigo, String destino, Date hPartia, Date hLlegada, double precioBase) {
		super();
		this.codigo = codigo;
		this.destino = destino;
		this.hPartida = hPartia;
		this.hLlegada = hLlegada;
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
	
	public Date gethPartida() {
		return hPartida;
	}
	
	public void sethPartida(Date hPartida) {
		this.hPartida = hPartida;
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
}
