package vistaGrafica.ventanas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
import vistaGrafica.controladoras.Controladora_RecaudacionExcursion;

public class RecaudacionExcursion extends Ventana
{
	private JFrame frame;
	private JTextField textField;

	public RecaudacionExcursion() {
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\guill\\Documents\\GitHub\\Taller-java\\Imagenes\\icon.png"));
		frame.setTitle("Recaudacion de excursion");
		
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVisible(false); // cierro el frame
				setVentanaAbierta(null);
				}
		});
		frame.addWindowListener(manFrame);
		
		JLabel lblTitulo = new JLabel("Recaudacion de excursion");
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
		
		textField = new JTextField();
		textField.setBounds(135, 49, 103, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCodExc = new JLabel("Codigo excursion:");
		lblCodExc.setBounds(10, 49, 115, 23);
		frame.getContentPane().add(lblCodExc);
		
		JButton btnIngresar = new JButton("Calcular");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String codigo = textField.getText().toString();
					Controladora_RecaudacionExcursion c;
					c = new Controladora_RecaudacionExcursion((RecaudacionExcursion) getVentanaAbierta());
					c.recaudadoEnExcursion(codigo);
				} catch (Exc_Persistencia e2) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e3){
					mostrarError("Error de conexion",0);
				}
			}
		});
		btnIngresar.setBounds(280, 49, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		JLabel lblRecaudacionTotal = new JLabel("Recaudacion total:");
		lblRecaudacionTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRecaudacionTotal.setBounds(10, 116, 142, 23);
		frame.getContentPane().add(lblRecaudacionTotal);
		
		JLabel lblCalculoTotal = new JLabel("");
		lblCalculoTotal.setBounds(162, 114, 103, 29);
		frame.getContentPane().add(lblCalculoTotal);
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