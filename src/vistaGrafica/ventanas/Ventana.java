package vistaGrafica.ventanas;

import javax.swing.JOptionPane;

import logica.fachada.Fachada;
import vistaGrafica.controladoras.Controladora;

public class Ventana {
	private Controladora c = null;
//	private Fachada f = null;
	
	public Ventana() {
		c = new Controladora();
//		f = Fachada.getInstancia();
	}

	public Controladora getControladora(){
		return this.c;
	}
	
//	public Fachada getFachada(){
//		return this.f;
//	}
	
	//
	public void mostrarError(String mensaje, int tipoError){
		switch (tipoError) {
		case 0:
			JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
			break;

		case 1:
			JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
			break;
		}
	}
	
	
}
