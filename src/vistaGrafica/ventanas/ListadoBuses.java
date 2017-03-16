package vistaGrafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;
import javax.swing.JList;

import java.awt.Font;

import javax.swing.SwingConstants;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import logica.ValueObjects.VOBusExc;
import logica.colecciones.Iterador;
import vistaGrafica.controladoras.Controladora_ListaBus;

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

import jdk.nashorn.internal.scripts.JS;

public class ListadoBuses extends Ventana
{
	private JFrame frame;

	public ListadoBuses() {
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
		frame.setTitle("Listado de Buses");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblMatricula = new JLabel("Listado de buses");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMatricula.setBounds(82, 11, 223, 36);
		frame.getContentPane().add(lblMatricula);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(230, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 393, 196);
		frame.getContentPane().add(scrollPane);
		
	

		final JTable list = new JTable();
		scrollPane.setViewportView(list);
		list.enable(false);
		
		
		final JButton btnRecargar = new JButton("Cargar");
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					btnRecargar.setText("Re-Cargar");
					final String[] columnas = {"Matricula", "Marca", "Pasajeros", "Cant. Excursiones"};
					
					 DefaultTableModel dlm = new DefaultTableModel(){
						 @Override
						 public int getColumnCount(){
							 return columnas.length;
						 }
						 @Override 
			            public String getColumnName(int index) { 
			                return columnas[index]; 
			            }
					 }; 
					 
					Controladora_ListaBus c;
					c= new Controladora_ListaBus((ListadoBuses) getVentanaAbierta());
					Iterador<VOBusExc> ite = c.ListadoBuses();
					while (ite.hasNext()){
						VOBusExc aux = ite.next();
						dlm.addRow(new String[] {aux.getMatricula(), aux.getMarca(), String.valueOf(aux.getCapPasajeros()),String.valueOf(aux.getExcursionesAsignadas())});
					}
					
					 list.setModel(dlm);
					 
					
			}	
				
		});
		btnRecargar.setBounds(100, 259, 89, 23);
		frame.getContentPane().add(btnRecargar);
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
