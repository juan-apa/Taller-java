/*VO utilizado para los requerimientos:
 * req. 1
 * req. 2*/

package logica.ValueObjects;

import java.io.Serializable;

public class VOBus implements Serializable{
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private int capPasajeros;
	
	public VOBus(){
		this.matricula = "000";
		this.marca = "generico";
		this.capPasajeros = 0;
	}
	
	public VOBus(String matricula, String marca, int capPasajeros) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.capPasajeros = capPasajeros;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCapPasajeros() {
		return capPasajeros;
	}

	public void setCapPasajeros(int capPasajeros) {
		this.capPasajeros = capPasajeros;
	}
	
	
}
