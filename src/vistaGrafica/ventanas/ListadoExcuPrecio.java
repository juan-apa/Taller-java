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
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import vistaGrafica.controladoras.Controladora_ListadoExcuDestino;
import vistaGrafica.controladoras.Controladora_ListadoExcuPrecio;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import javax.swing.JScrollPane;

public class ListadoExcuPrecio extends Ventana
{
	private JFrame frame;
	private JTextField precioIni;
	private JTextField precioFin;

	public ListadoExcuPrecio() {
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
		frame.setTitle("Listado de excursiones por precio");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Listado de excursiones por precio");
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
		btnVolver.setBounds(149, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 393, 165);
		frame.getContentPane().add(scrollPane);
		
		final JTable list = new JTable();
		scrollPane.setViewportView(list);
		
		precioIni = new JTextField();
		precioIni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char ch = arg0.getKeyChar();
				if (!esNumerico(ch)){
					arg0.consume();
				}
			}
		});
		precioIni.setBounds(93, 49, 59, 23);
		frame.getContentPane().add(precioIni);
		precioIni.setColumns(10);
		
		JLabel lblPrecioInicial = new JLabel("Precio inicial:");
		lblPrecioInicial.setBounds(10, 49, 89, 23);
		frame.getContentPane().add(lblPrecioInicial);
		
		ButtonGroup grupo = new ButtonGroup();
		
		final JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBuscar.setText("Re-Buscar");
				String desde,hasta;
				desde = precioIni.getText();
				hasta = precioFin.getText();
				
				
				
				
				final String[] columnas = {"Codigo", "Destino", "H.Partida", "H.Regreso", "Precio","Asientos Disponibles"};
				
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
				
				Controladora_ListadoExcuPrecio c;
				c=new Controladora_ListadoExcuPrecio((ListadoExcuPrecio)getVentanaAbierta());
				Iterador<VOExcursionListado> ite = c.ListadoExcuPrecio(desde,hasta);
				while (ite.hasNext()){
					VOExcursionListado aux = ite.next();
					dlm.addRow(new String[] {aux.getCodigo(),aux.getDestino(),String.valueOf(aux.gethPartida()),String.valueOf(aux.gethLlegada()),String.valueOf(aux.getPrecioBase()),String.valueOf(aux.getAsientosDisp())});
				}
				
				list.setModel(dlm);
				
				/////////////////////////////////////////////////////
				
				
				
			}
		});
		btnBuscar.setBounds(314, 49, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JLabel lblPrecioFinal = new JLabel("Precio final:");
		lblPrecioFinal.setBounds(162, 49, 76, 23);
		frame.getContentPane().add(lblPrecioFinal);
		
		precioFin = new JTextField();
		precioFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char ch = arg0.getKeyChar();
				if (!esNumerico(ch)){
					arg0.consume();
				}
			}
		});
		precioFin.setColumns(10);
		precioFin.setBounds(245, 49, 59, 23);
		frame.getContentPane().add(precioFin);

	}
	
	/* Indico si deseo que la ventana sea visible o no */
	public void setVisible (boolean visible) {
		frame.setVisible(visible);
	}
	@Override
	public void toFront(){
		frame.toFront();
	}
	
	public boolean esNumerico(char c){
		boolean valida=false;
		switch (c) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':	
		case '.':
			valida=true;
			break;
		}
		
		return valida;
	}
	
	
	
}