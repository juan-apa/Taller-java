package vistaGrafica.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

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
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBoleto;
import logica.ValueObjects.VOBoleto2;
import logica.ValueObjects.VOBusExc;
import logica.colecciones.Iterador;
import vistaGrafica.controladoras.Controladora_ListadoBoletosExcu;

import javax.swing.JScrollPane;

public class ListadoBoletosExcu extends Ventana
{
	private JFrame frame;
	private JTextField cod_exc;

	public ListadoBoletosExcu() {
		super();
		initialize();
	}
	
	
	
	
	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(665, 322));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\guill\\Documents\\GitHub\\Taller-java\\Imagenes\\icon.png"));
		frame.setTitle("Listado de boletos para excursion");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
			});
		frame.addWindowListener(manFrame);
		
		
		JLabel lblTitulo = new JLabel("Listado de boletos vendidos para excursion");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(107, 11, 393, 36);
		frame.getContentPane().add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(126, 259, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 52, 505, 196);
		frame.getContentPane().add(scrollPane);
		
		final JTable list = new JTable();
		scrollPane.setViewportView(list);
		list.enable(false);
		
		cod_exc = new JTextField();
		cod_exc.setBounds(10, 86, 114, 23);
		frame.getContentPane().add(cod_exc);
		cod_exc.setColumns(10);
		
		
		JLabel lblCodigoDeExcursion = new JLabel("Codigo de Excursion:");
		lblCodigoDeExcursion.setBounds(10, 58, 140, 23);
		frame.getContentPane().add(lblCodigoDeExcursion);
		
		final JRadioButton rdbtnComun = new JRadioButton("Comun");
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
		
		final JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBuscar.setText("Refrescar");
				String cod = cod_exc.getText().trim().toUpperCase();
				String tipo;
				if (rdbtnComun.isSelected()){
					tipo = "Comun";
				}else{
					tipo = "Especial";
				}
				
				if (tipo=="Comun"){
					 final String[] columnas = {"N.Bol", "Edad", "Procedencia", "N.Celular"};
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
					try{
						Controladora_ListadoBoletosExcu c;
						c=new Controladora_ListadoBoletosExcu((ListadoBoletosExcu) getVentanaAbierta());
						Iterador<VOBoleto2> ite = c.ListadoBoletosExc(cod, tipo);
						while (ite.hasNext()){
							VOBoleto2 aux = ite.next();
							dlm.addRow(new String[] {String.valueOf(aux.getNumero()), String.valueOf(aux.getEdad()) ,aux.getLugarProcedencia(), String.valueOf(aux.getNroCelular())});
						}
						list.setModel(dlm);
					} catch (Exc_Persistencia e2) {
						mostrarError("Error al cargar el archivo .properties", 0);
					} catch(MalformedURLException | RemoteException | NotBoundException e3){
						mostrarError("Error de conexion",0);
					}
				}else{
					final String[] columnas = {"N.Bol", "Edad", "Procedencia", "N.Celular" ,"Dto (%)"};
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
					try{
						Controladora_ListadoBoletosExcu c;
						c=new Controladora_ListadoBoletosExcu((ListadoBoletosExcu) getVentanaAbierta());
						Iterador<VOBoleto2> ite = c.ListadoBoletosExc(cod, tipo);
						while (ite.hasNext()){
							VOBoleto2 aux = ite.next();
							dlm.addRow(new String[] {String.valueOf(aux.getNumero()), String.valueOf(aux.getEdad()) ,aux.getLugarProcedencia(), String.valueOf(aux.getNroCelular()),String.valueOf(aux.getDtoAdicional()*100)});
						}
						list.setModel(dlm);
					} catch (Exc_Persistencia e2) {
						mostrarError("Error al cargar el archivo .properties", 0);
					} catch(MalformedURLException | RemoteException | NotBoundException e3){
						mostrarError("Error de conexion",0);
					}
					
					
					
				}
			}
		});
		btnBuscar.setBounds(10, 259, 106, 23);
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
