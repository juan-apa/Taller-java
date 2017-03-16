package vistaGrafica.ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import java.awt.Font;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;

import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Boleto;
import logica.ValueObjects.VOBoleto;
import logica.fachada.Fachada;
import vistaGrafica.controladoras.Controladora;
import vistaGrafica.controladoras.Controladora_RegistroBoleto;
import vistaGrafica.controladoras.Controladora_RegistroBus;

public class RegistroBoleto extends Ventana
{
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/* Constructor de la ventana */
	public RegistroBoleto() {
		//llamo el constructor de la clase Ventana para que me inicialize la controladora
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
		frame.setTitle("Registro de Boleto");
		

		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVentanaAbierta(null);
				setVisible(false); // cierro el frame
				
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblMatricula = new JLabel("Codigo de excursion:");
		lblMatricula.setBounds(10, 11, 122, 25);
		frame.getContentPane().add(lblMatricula);
		
		JLabel lblMarca = new JLabel("DATOS PASAJERO");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(10, 47, 148, 25);
		frame.getContentPane().add(lblMarca);
		
		JLabel lblCapPasajeros = new JLabel("Edad");
		lblCapPasajeros.setBounds(10, 81, 47, 25);
		frame.getContentPane().add(lblCapPasajeros);
		
		textField = new JTextField();
		textField.setBounds(136, 13, 122, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(89, 83, 53, 20);
		int edadInicial = 1;
		int edadFinal = 100;
		for(int i=edadInicial; i<=edadFinal; i++){
			comboBox.addItem(new Integer(i));
		}
		frame.getContentPane().add(comboBox);
		
		JButton btnIngresar = new JButton("Ingresar");
		
		btnIngresar.setBounds(99, 259, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(197, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JLabel lblProcedencia = new JLabel("Procedencia");
		lblProcedencia.setBounds(10, 117, 79, 25);
		frame.getContentPane().add(lblProcedencia);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 119, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(10, 153, 69, 25);
		frame.getContentPane().add(lblCelular);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(89, 155, 86, 20);
		frame.getContentPane().add(textField_2);

		final JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setBounds(10, 222, 69, 25);
		frame.getContentPane().add(lblDescuento);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(89, 224, 37, 20);
		frame.getContentPane().add(textField_3);
		
		//JCheckBox 
		final JCheckBox chckbxBoletoEspecial = new JCheckBox("Boleto Especial?");
		lblDescuento.setVisible(false);
		textField_3.setVisible(false);
		chckbxBoletoEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxBoletoEspecial.isSelected()){
					lblDescuento.setVisible(true);
					textField_3.setVisible(true);
				}else{
					lblDescuento.setVisible(false);
					textField_3.setVisible(false);
				}
			}
		});
		chckbxBoletoEspecial.setBounds(10, 194, 306, 23);
		frame.getContentPane().add(chckbxBoletoEspecial);
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//codigo de lo que hace el boton INGRESAR
				
				//Obtengo los datos de la pantalla.
				String codigoExcursion = textField.getText();
				int edad	 = Integer.parseInt(comboBox.getSelectedItem().toString());
				String procedencia 	= textField_1.getText();
				long celular = Long.parseLong(textField_2.getText());
				boolean especial = chckbxBoletoEspecial.isSelected();
				double descuento 	= Double.parseDouble(textField_3.getText());
				String tipoBoleto = "Comun";
				if(especial)
					tipoBoleto = "Especial";
				
				//Se los mando a la controladora para que me revise que los valores sean correctos.
				Controladora_RegistroBoleto c;
				c = new Controladora_RegistroBoleto(((RegistroBoleto) getVentanaAbierta()));
				c.ventaBoleto(codigoExcursion, procedencia, edad, celular, tipoBoleto, descuento);			
			}
		});
		
		
		
		
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
