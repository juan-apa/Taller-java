package vistaGrafica.ventanas;

import javax.swing.JOptionPane;


public class Ventana {
	
	/*Ventana que se encuentra abierta*/
	private static Ventana ven = null;
	
	public Ventana() {
		super();
		ven = null;
	}


	public void mostrarError(String mensaje, int tipoError){
		switch (tipoError) {
		case 0:
			JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
			break;

		case 1:
			JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
			break;
			
		case 2:
			JOptionPane.showMessageDialog(null, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
	
	public void mostrarCorrecto(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	
	
	public void setVentanaAbierta(Ventana ventana){
		Ventana.ven = ventana;
	}
	
	public Ventana getVentanaAbierta(){
		return Ventana.ven;
	}
	
	
	public void toFront(){
		
	}
	
}
