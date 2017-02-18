/*VO utilizado para los requerimientos:
 * req. 1
 * req. 2*/

package logica.ValueObjects;


public class VOBus {
	private String matricula;
	private String marca;
	private int capPasajeros;
	
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
