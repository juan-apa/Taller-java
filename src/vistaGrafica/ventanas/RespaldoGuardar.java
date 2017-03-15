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

public class RespaldoGuardar extends Ventana
{
	private JFrame frame;

	public RespaldoGuardar() {
		super();
		initialize();
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(416, 216));
		frame.setTitle("Respaldo de datos (G)");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
		public void windowClosing (WindowEvent arg0){ 
			setVisible(false); // cierro el frame
			setVentanaAbierta(null);
			}
		});
		frame.addWindowListener(manFrame);
		
		
		JLabel lblTitulo = new JLabel("Guardar datos");
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
		btnVolver.setBounds(204, 95, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		ButtonGroup grupo = new ButtonGroup();
		
		final JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(97, 95, 89, 23);
		frame.getContentPane().add(btnGuardar);
		btnGuardar.setVisible(false);
		
		final JCheckBox chckbxDeseaRespaldarLos = new JCheckBox("Desea respaldar los datos?");
		chckbxDeseaRespaldarLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxDeseaRespaldarLos.isSelected()){
					btnGuardar.setVisible(true);
				}else{
					btnGuardar.setVisible(false);
				}
			}
		});
		chckbxDeseaRespaldarLos.setBounds(20, 54, 181, 23);
		frame.getContentPane().add(chckbxDeseaRespaldarLos);
		
		
		

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