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
import javax.swing.SwingConstants;
import vistaGrafica.controladoras.Controladora_ListadoExcuPrecio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import logica.Excepciones.objetos.Exc_Persistencia;
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
		frame.setSize(new Dimension(634, 322));
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
		scrollPane.setBounds(10, 83, 608, 165);
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
		
		//ButtonGroup grupo = new ButtonGroup();
		
		final JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					btnBuscar.setText("Refrescar");
					String desde,hasta;
					desde = precioIni.getText();
					hasta = precioFin.getText();
					
					
					
					
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
					
					Controladora_ListadoExcuPrecio c;
					c=new Controladora_ListadoExcuPrecio((ListadoExcuPrecio)getVentanaAbierta());
					Iterador<VOExcursionListado> ite = c.ListadoExcuPrecio(desde,hasta);
					while (ite.hasNext()){
						VOExcursionListado aux = ite.next();
						dlm.addRow(new String[] {aux.getCodigo(),aux.getDestino(),String.valueOf(aux.gethPartida()),String.valueOf(aux.gethLlegada()),String.valueOf(aux.getPrecioBase()),String.valueOf(aux.getAsientosDisp())});
					}
					
					list.setModel(dlm);
					
					/////////////////////////////////////////////////////
				} catch (Exc_Persistencia e2) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e3){
					mostrarError("Error de conexion",0);
				}				
			}
		});
		btnBuscar.setBounds(430, 49, 115, 23);
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
	
	
	
	
}