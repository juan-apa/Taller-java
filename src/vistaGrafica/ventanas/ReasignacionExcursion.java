package vistaGrafica.ventanas;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;

import logica.Excepciones.objetos.Exc_Persistencia;
import vistaGrafica.controladoras.Controladora_ReasignacionExcursion;

public class ReasignacionExcursion extends Ventana
{
	private JFrame frame;
	private JTextField textField;

	public ReasignacionExcursion() {
		super();
		initialize();
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Reasignacion de Excursion");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Reasignacion de excursion");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 393, 36);
		frame.getContentPane().add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(205, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		textField = new JTextField();
		textField.setBounds(149, 84, 126, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPrecioInicial = new JLabel("Codigo de Excursion:");
		lblPrecioInicial.setBounds(10, 84, 136, 23);
		frame.getContentPane().add(lblPrecioInicial);
		
		JButton btnIngresar = new JButton("Reasignar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String codigo = textField.getText().toString();
					Controladora_ReasignacionExcursion c;
					c = new Controladora_ReasignacionExcursion((ReasignacionExcursion) getVentanaAbierta());
					c.reasignacionExcursion(codigo);
				} catch (Exc_Persistencia e2) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e3){
					mostrarError("Error de conexion",0);
				}
			}
		});
		btnIngresar.setBounds(91, 259, 104, 23);
		frame.getContentPane().add(btnIngresar);
	}
	
	/* Indico si deseo que la ventana sea visible o no */
	public void setVisible (boolean visible) {
		frame.setVisible(visible);
	}
	@Override
	public void toFront(){
		frame.toFront();
	}
}