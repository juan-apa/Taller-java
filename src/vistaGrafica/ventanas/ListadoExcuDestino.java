package vistaGrafica.ventanas;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;

import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import vistaGrafica.controladoras.Controladora_ListadoExcuDestino;

import javax.swing.JScrollPane;

public class ListadoExcuDestino extends Ventana
{
	private JFrame frame;
	private JTextField Destino;

	public ListadoExcuDestino() {
		super();
		initialize();
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	@SuppressWarnings("deprecation")
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(569, 322));
		frame.setTitle("Listado de excursiones por destino");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Listado de excursiones por destino");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(83, 11, 393, 36);
		frame.getContentPane().add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(213, 259, 103, 23);
		frame.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 543, 165);
		frame.getContentPane().add(scrollPane);
		
		final JTable list = new JTable();
		scrollPane.setViewportView(list);
		list.enable(false);
		
		Destino = new JTextField();
		Destino.setBounds(69, 49, 114, 23);
		frame.getContentPane().add(Destino);
		Destino.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(10, 49, 114, 23);
		frame.getContentPane().add(lblDestino);
		
		
		final JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBuscar.setText("Refrescar");
				String dest = Destino.getText().trim().toUpperCase();
				
				final String[] columnas = {"Codigo", "Destino", "H.Partida", "H.Regreso", "Precio","Asientos Disponibles"};
				
				 DefaultTableModel dlm = new DefaultTableModel(){
					 /**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					@Override
					 public int getColumnCount(){
						 return columnas.length;
					 }
					 @Override 
		            public String getColumnName(int index) { 
		                return columnas[index]; 
		            }
				 }; 
				try{
					Controladora_ListadoExcuDestino c;
					c= new Controladora_ListadoExcuDestino((ListadoExcuDestino)getVentanaAbierta());
					Iterador<VOExcursionListado> ite = c.ListadoExcuDestino(dest);
					while (ite.hasNext()){
						VOExcursionListado aux = ite.next();
						dlm.addRow(new String[] {aux.getCodigo(),aux.getDestino(),String.valueOf(aux.gethPartida()),String.valueOf(aux.gethLlegada()),String.valueOf(aux.getPrecioBase()),String.valueOf(aux.getAsientosDisp())});
					}
					
					 list.setModel(dlm);
				} catch (Exc_Persistencia e2) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e3){
					mostrarError("Error de conexion",0);
				}	
			}
		});
		btnBuscar.setBounds(213, 49, 103, 23);
		frame.getContentPane().add(btnBuscar);
		
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