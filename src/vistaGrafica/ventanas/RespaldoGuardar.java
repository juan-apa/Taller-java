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
import vistaGrafica.controladoras.Controladora_RespaldoGuardar;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\guill\\Documents\\GitHub\\Taller-java\\Imagenes\\icon.png"));
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
		
		
		final JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo de lo que hace el boton Guardar
				try{
					Controladora_RespaldoGuardar c;
					c = new Controladora_RespaldoGuardar(((RespaldoGuardar) getVentanaAbierta()));
					c.guardar();
				} catch (Exc_Persistencia e1) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e2){
					mostrarError("Error de conexion",0);
				}
			}
		});
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