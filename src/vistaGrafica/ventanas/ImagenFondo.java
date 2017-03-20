package vistaGrafica.ventanas;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagenFondo extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagen;

	/* creo el panel y cargo la imagen a colocar en el */
	public ImagenFondo()
	{
		imagen = new ImageIcon("Imagenes/imagen1.jpg");
		super.setSize(250, 250);
	}
	
	/* dibujo la imagen dentro del panel, este metodo es invocado automaticamente por java
	   cuando va a dibujar la ventana principal que contiene a este panel */
	public void paint (Graphics g)
	{
		if (imagen != null)
		{
			g.drawImage(imagen.getImage(), 0, 0, super.getWidth(), super.getHeight(), this);
			super.setOpaque(false);
			super.paint(g);
		}
	}
}
