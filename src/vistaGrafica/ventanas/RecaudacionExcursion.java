package vistaGrafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
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
import javax.swing.JList;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class RecaudacionExcursion 
{
	private JFrame frame;
	private JTextField textField;

	public RecaudacionExcursion() {
		initialize();
		
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Recaudacion de excursion");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				VentanaPrincipal.controlVent=0;
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Recaudacion de excursion");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(10, 11, 393, 36);
		frame.getContentPane().add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				VentanaPrincipal.controlVent=0;
			}
		});
		btnVolver.setBounds(149, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		textField = new JTextField();
		textField.setBounds(135, 49, 103, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCodExc = new JLabel("Codigo excursion:");
		lblCodExc.setBounds(10, 49, 115, 23);
		frame.getContentPane().add(lblCodExc);
		
		ButtonGroup grupo = new ButtonGroup();
		
		JButton btnIngresar = new JButton("Calcular");
		btnIngresar.setBounds(280, 49, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		JLabel lblRecaudacionTotal = new JLabel("Recaudacion total:");
		lblRecaudacionTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRecaudacionTotal.setBounds(10, 116, 142, 23);
		frame.getContentPane().add(lblRecaudacionTotal);
		
		JLabel lblCalculoTotal = new JLabel("");
		lblCalculoTotal.setBounds(162, 114, 103, 29);
		frame.getContentPane().add(lblCalculoTotal);
		/*ACA AGREGO VALORES A LA LISTA
		DefaultListModel model= new DefaultListModel();
		model.addElement("HOLA");
		model.addElement("ALOHA");
		list.setModel(model);
		*/
	}
	
	/* Indico si deseo que la ventana sea visible o no */
	public void setVisible (boolean visible) {
		frame.setVisible(visible);
	}
}