/*VO utilizado para los requerimientos:
 *req. 4 */

package logica;

import java.util.Date;


public class VOExcursion {
	private String codigo;
	private String destino;
	private Date hPartia;
	private Date hLlegada;
	private double precioBase;
	
	public VOExcursion(String codigo, String destino, Date hPartia, Date hLlegada, double precioBase) {
		super();
		this.codigo = codigo;
		this.destino = destino;
		this.hPartia = hPartia;
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
}
