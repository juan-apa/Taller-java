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
		
		ButtonGroup grupo = new ButtonGroup();
		
		JButton btnIngresar = new JButton("Reasignar");
		btnIngresar.setBounds(91, 259, 104, 23);
		frame.getContentPane().add(btnIngresar);
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
	@Override
	public void toFront(){
		frame.toFront();
	}
}