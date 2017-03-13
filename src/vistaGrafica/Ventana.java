package vistaGrafica;

import logica.fachada.Controladora;
import logica.fachada.Fachada;

public class Ventana {
	private Controladora c = null;
	private Fachada f = null;
	
	public Ventana() {
		c = new Controladora();
		f = Fachada.getInstancia();
	}

	public Controladora getControladora(){
		return this.c;
	}
	
	public Fachada getFachada(){
		return this.f;
	}
	
}
