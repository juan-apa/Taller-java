/*VO utilizado para los requerimientos:
 *req. 9 */

package logica;

public class VOExcBol {
	private String codigoExcursion;
	private String tipoBoleto;
	
	public VOExcBol(String codigoExcursion, String tipoBoleto) {
		super();
		this.codigoExcursion = codigoExcursion;
		this.tipoBoleto = tipoBoleto;
	}

	public String getCodigoExcursion() {
		return codigoExcursion;
	}

	public void setCodigoExcursion(String codigoExcursion) {
		this.codigoExcursion = codigoExcursion;
	}

	public String getTipoBoleto() {
		return tipoBoleto;
	}

	public void setTipoBoleto(String tipoBoleto) {
		this.tipoBoleto = tipoBoleto;
	}
	
	
	
}
