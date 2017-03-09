package vistaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class RegistroExcursion
{
	private JFrame frame;
	private JLabel label;

	/* Constructor de la ventana */
	public RegistroExcursion() {
		initialize();
	}

	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Registro de Excursion");
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/* etiqueta de la ventana secundaria */
		label = new JLabel("New label");
		label.setText("Soy la ventana secundaria");
		label.setBackground(new Color(219,240,250));
		frame.getContentPane().add(label, BorderLayout.CENTER);
	}

	/* Indico si deseo que la ventana sea visible o no */
	public void setVisible (boolean visible) {
		frame.setVisible(visible);
	}
}
