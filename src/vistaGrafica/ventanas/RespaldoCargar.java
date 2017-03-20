package vistaGrafica.ventanas;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;

import logica.Excepciones.objetos.Exc_Persistencia;
import vistaGrafica.controladoras.Controladora_RespaldoCargar;

public class RespaldoCargar extends Ventana
{
	private JFrame frame;

	public RespaldoCargar() {
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes\\icon.png"));
		frame.setTitle("Respaldo de datos (C)");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Cargar datos");
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
		
		//ButtonGroup grupo = new ButtonGroup();
		
		final JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//codigo de lo que hace el boton Cargar
				try{
					Controladora_RespaldoCargar c;
					c = new Controladora_RespaldoCargar(((RespaldoCargar) getVentanaAbierta()));
					c.cargar(false);
				} catch (Exc_Persistencia e) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e){
					mostrarError("Error de conexion",0);
				}
			}
		});
		btnCargar.setBounds(97, 95, 89, 23);
		frame.getContentPane().add(btnCargar);
		btnCargar.setVisible(false);
		
		final JCheckBox chckbxDeseaRespaldarLos = new JCheckBox("Desea cargar los datos?");
		chckbxDeseaRespaldarLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxDeseaRespaldarLos.isSelected()){
					btnCargar.setVisible(true);
				}else{
					btnCargar.setVisible(false);
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