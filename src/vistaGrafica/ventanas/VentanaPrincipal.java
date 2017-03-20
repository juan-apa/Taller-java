package vistaGrafica.ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;



public class VentanaPrincipal extends Ventana
{
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu B_registros, B_listados, B_otros, B_respaldo;
	private JMenuItem menuItem;
	private ImagenFondo fondo;
    
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaPrincipal window = new VentanaPrincipal();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
	
	
	/* Constructor de la ventana */
	public VentanaPrincipal() 
	{
		this.initialize();
	}

	/* Inicializo los componentes de la ventana */
	private void initialize()
	{	
		/* marco de la ventana principal */
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\guill\\Documents\\GitHub\\Taller-java\\Imagenes\\icon.png"));
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(566, 360));
		frame.setTitle("Menu Principal");
		
		/* cuando intenten cerrarme, termino toda la aplicación */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		/* creo el panel con la imagen de fondo y se lo cargo al frame */
		fondo = new ImagenFondo();
		fondo.setBounds(10, 11, 540, 288);
		fondo.setBackground(new Color(165,250,142));
		fondo.setLayout(null);
		frame.getContentPane().add(fondo);
 
		
		/* barra de menu que contiene un menu */
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		/* menu que contiene un unico item de menu */
		B_registros = new JMenu("Registros");
		menuBar.add(B_registros);
		B_listados = new JMenu("Listados");
		menuBar.add(B_listados);
		B_otros = new JMenu("Otros");
		menuBar.add(B_otros);
		B_respaldo = new JMenu("Respaldos");
		menuBar.add(B_respaldo);

		
		/* item de menu, al clickearlo se abre la ventana secundaria que emite un saludo */
		
		menuItem = new JMenuItem("Registro de Bus");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					RegistroDeBus registroBus = new RegistroDeBus();
					registroBus.setVisible(true);
					setVentanaAbierta(registroBus);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_registros.add(menuItem);
	
		menuItem = new JMenuItem("Registro de Excursion");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					RegistroExcursion registroExc = new RegistroExcursion();
					setVentanaAbierta(registroExc);
					registroExc.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_registros.add(menuItem);
		
		menuItem = new JMenuItem("Registro/Venta de Boleto");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					RegistroBoleto registroBoleto = new RegistroBoleto();
					setVentanaAbierta(registroBoleto);
					registroBoleto.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_registros.add(menuItem);
		
		menuItem = new JMenuItem("Listado de Buses");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					ListadoBuses listBuses = new ListadoBuses();
					setVentanaAbierta(listBuses);
					listBuses.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_listados.add(menuItem);
		
		menuItem = new JMenuItem("Listado de Boletos para excursion");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					ListadoBoletosExcu listBolExc = new ListadoBoletosExcu();
					setVentanaAbierta(listBolExc);
					listBolExc.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_listados.add(menuItem);
		
		menuItem = new JMenuItem("Listado de Excursiones por destino");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					ListadoExcuDestino listExcDest = new ListadoExcuDestino();
					setVentanaAbierta(listExcDest);
					listExcDest.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_listados.add(menuItem);
		
		menuItem = new JMenuItem("Listado de Excursiones por precio");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					ListadoExcuPrecio listExcPre = new ListadoExcuPrecio();
					setVentanaAbierta(listExcPre);
					listExcPre.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_listados.add(menuItem);
		
		menuItem = new JMenuItem("Listado de Excursiones por bus");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					ListadoExcursionesBus listExcBus = new ListadoExcursionesBus();
					setVentanaAbierta(listExcBus);
					listExcBus.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_listados.add(menuItem);
	
		menuItem = new JMenuItem("Reasignar Excursion");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					ReasignacionExcursion reasignExc = new ReasignacionExcursion();
					setVentanaAbierta(reasignExc);
					reasignExc.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_otros.add(menuItem);
	
		menuItem = new JMenuItem("Recaudacion de Excursion");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					RecaudacionExcursion recExc = new RecaudacionExcursion();
					setVentanaAbierta(recExc);
					recExc.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_otros.add(menuItem);
	
	
	
		menuItem = new JMenuItem("Guardar informacion");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					RespaldoGuardar respGuard = new RespaldoGuardar();
					setVentanaAbierta(respGuard);
					respGuard.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_respaldo.add(menuItem);
		
		menuItem = new JMenuItem("Levantar informacion");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(getVentanaAbierta() == null){
					RespaldoCargar respCarg = new RespaldoCargar();
					setVentanaAbierta(respCarg);
					respCarg.setVisible(true);
				}
				else{
					getVentanaAbierta().toFront();
				}
			}
		});
		B_respaldo.add(menuItem);
		
		
	}

	/* Indico si deseo que la ventana sea visible o no */
	public void setVisible (boolean visible) {
		frame.setVisible(visible);
	}
}
