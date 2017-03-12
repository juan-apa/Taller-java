package vistaGrafica;

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

import javax.swing.JButton;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;
import javax.swing.JList;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class ListadoExcuPrecio 
{
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	public ListadoExcuPrecio() {
		initialize();
		
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Listado de excursiones por precio");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Listado de excursiones por precio");
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
		
		JList list = new JList();
		list.setBounds(10, 83, 393, 165);
		frame.getContentPane().add(list);
		
		textField = new JTextField();
		textField.setBounds(93, 49, 59, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPrecioInicial = new JLabel("Precio inicial:");
		lblPrecioInicial.setBounds(10, 49, 89, 23);
		frame.getContentPane().add(lblPrecioInicial);
		
		ButtonGroup grupo = new ButtonGroup();
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(314, 49, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JLabel lblPrecioFinal = new JLabel("Precio final:");
		lblPrecioFinal.setBounds(162, 49, 76, 23);
		frame.getContentPane().add(lblPrecioFinal);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(245, 49, 59, 23);
		frame.getContentPane().add(textField_1);
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