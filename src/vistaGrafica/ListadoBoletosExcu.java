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

public class ListadoBoletosExcu 
{
	private JFrame frame;
	private JTextField textField;

	public ListadoBoletosExcu() {
		initialize();
		
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Listado de boletos para excursion");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Listado de boletos vendidos para excursion");
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
		btnVolver.setBounds(109, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JList list = new JList();
		list.setBounds(134, 52, 269, 196);
		frame.getContentPane().add(list);
		
		textField = new JTextField();
		textField.setBounds(10, 86, 114, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCodigoDeExcursion = new JLabel("Codigo de Excursion:");
		lblCodigoDeExcursion.setBounds(10, 58, 114, 23);
		frame.getContentPane().add(lblCodigoDeExcursion);
		
		JRadioButton rdbtnComun = new JRadioButton("Comun");
		rdbtnComun.setBounds(10, 145, 122, 23);
		frame.getContentPane().add(rdbtnComun);
		rdbtnComun.setSelected(true);
		
		JRadioButton rdbtnEspecial = new JRadioButton("Especial");
		rdbtnEspecial.setBounds(10, 172, 118, 23);
		frame.getContentPane().add(rdbtnEspecial);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnEspecial);
		grupo.add(rdbtnComun);
		
		
		JLabel lblTipoDeBoleto = new JLabel("Tipo de Boleto:");
		lblTipoDeBoleto.setBounds(10, 124, 114, 14);
		frame.getContentPane().add(lblTipoDeBoleto);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(10, 259, 89, 23);
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
}
