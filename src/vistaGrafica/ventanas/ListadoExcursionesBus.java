package vistaGrafica.ventanas;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import vistaGrafica.controladoras.Controladora_ListadoExcursionesBus;

import javax.swing.JScrollPane;

public class ListadoExcursionesBus extends Ventana
{
	private JFrame frame;
	private JTextField txt_Matricula;

	public ListadoExcursionesBus() {
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
		frame.setSize(new Dimension(598, 322));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\guill\\Documents\\GitHub\\Taller-java\\Imagenes\\icon.png"));
		frame.setTitle("Listado de excursiones por bus");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Listado de excuriones de bus");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(102, 11, 393, 36);
		frame.getContentPane().add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(314, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 572, 165);
		frame.getContentPane().add(scrollPane);
		
		final JTable list = new JTable();
		scrollPane.setViewportView(list);
		list.enable(false);
		
		txt_Matricula = new JTextField();
		txt_Matricula.setBounds(93, 49, 95, 23);
		frame.getContentPane().add(txt_Matricula);
		txt_Matricula.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 49, 89, 23);
		frame.getContentPane().add(lblMatricula);
		
		//ButtonGroup grupo = new ButtonGroup();
		
		final JButton btnIngresar = new JButton("Buscar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					btnIngresar.setText("Refrescar");
					
					String mat = txt_Matricula.getText().trim().toUpperCase();
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
					Controladora_ListadoExcursionesBus c;
					c= new Controladora_ListadoExcursionesBus((ListadoExcursionesBus)getVentanaAbierta());
					Iterador<VOExcursionListado> ite = c.ListadoExcursionesBus(mat);
					while (ite.hasNext()){
						VOExcursionListado aux = ite.next();
						String hpartida = aux.gethPartida().getHours() + ":" + aux.gethPartida().getMinutes();
						String hllegada = aux.gethLlegada().getHours() + ":" + aux.gethLlegada().getMinutes();
						dlm.addRow(new String[] {aux.getCodigo(),aux.getDestino(), hpartida/*String.valueOf(aux.gethPartida())*/, hllegada/*String.valueOf(aux.gethLlegada())*/,String.valueOf(aux.getPrecioBase()),String.valueOf(aux.getAsientosDisp())});
					}
					 list.setModel(dlm);
				} catch (Exc_Persistencia e2) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e3){
					mostrarError("Error de conexion",0);
				}
			}
		});
		btnIngresar.setBounds(314, 49, 109, 23);
		frame.getContentPane().add(btnIngresar);
	
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