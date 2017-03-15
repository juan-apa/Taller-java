package vistaGrafica.ventanas;

import javax.swing.JOptionPane;


public class Ventana {

	
	public Ventana() {

	}


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
	
	public void mostrarCorrecto(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje);

	}
	
}
