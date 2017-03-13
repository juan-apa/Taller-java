package vistaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import logica.fachada.Fachada;

public class RegistroDeBus 
{
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	public RegistroDeBus() {
		initialize();
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Registro de Bus");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				VentanaPrincipal.controlVent=0;
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 39, 71, 25);
		frame.getContentPane().add(lblMatricula);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 75, 71, 25);
		frame.getContentPane().add(lblMarca);
		
		JLabel lblCapPasajeros = new JLabel("Cap. Pasajeros:");
		lblCapPasajeros.setBounds(10, 111, 95, 25);
		frame.getContentPane().add(lblCapPasajeros);
		
		textField = new JTextField();
		textField.setBounds(101, 41, 122, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(101, 77, 122, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(105, 113, 53, 20);
		int capacidadBus = 120;
		for(int i=1; i<=capacidadBus; i++){
			comboBox.addItem(new Integer(i));
		}
		frame.getContentPane().add(comboBox);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//codigo de lo que hace el boton INGRESAR
				
			}
		});
		btnIngresar.setBounds(101, 176, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				VentanaPrincipal.controlVent=0;
			}
		});
		btnVolver.setBounds(200, 176, 89, 23);
		frame.getContentPane().add(btnVolver);
	}
	
	/* Indico si deseo que la ventana sea visible o no */
	public void setVisible (boolean visible) {
		frame.setVisible(visible);
	}
}
