package vistaGrafica.ventanas;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import vistaGrafica.controladoras.Controladora_RegistroExcursion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;

import logica.Excepciones.objetos.Exc_Persistencia;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistroExcursion extends Ventana
{
	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JComboBox<Integer> comboBox;
	private JLabel lblHora;
	private JLabel lblMinutos;
	private JComboBox<Integer> comboBox_1;
	private JLabel label_3;
	private JComboBox<Integer> comboBox_8;
	private JLabel label_4;
	private JComboBox<Integer> comboBox_9;
	private JButton btnIngresar;
	private JButton btnVolver;
	private JLabel lblFechaDeHoy;

	/* Constructor de la ventana */
	public RegistroExcursion() {
		super();
		initialize();
	}

	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(415, 322));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes\\icon.png"));
		frame.setTitle("Registro de Excursion");
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.getContentPane().setLayout(null);
		WindowAdapter manFrame = (new WindowAdapter(){
			public void windowClosing (WindowEvent arg0){ 
				setVentanaAbierta(null);
				setVisible(false); // cierro el frame
				}
		});
		frame.addWindowListener(manFrame);
		
		/* etiqueta de la ventana secundaria */
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblDestino);
		
		JLabel lblHoraDePartida = new JLabel("Hora de Partida");
		lblHoraDePartida.setBounds(10, 97, 103, 14);
		frame.getContentPane().add(lblHoraDePartida);
		
		JLabel lblHoraDeLlegada = new JLabel("Hora de Llegada:");
		lblHoraDeLlegada.setBounds(10, 153, 103, 14);
		frame.getContentPane().add(lblHoraDeLlegada);
		
		JLabel lblPrecioBase = new JLabel("Precio Base:");
		lblPrecioBase.setBounds(10, 213, 86, 14);
		frame.getContentPane().add(lblPrecioBase);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 8, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 33, 86, 20);
		frame.getContentPane().add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
		//Controlo que en el campo Precio solo pueda ingresar numeros enteros
			public void keyTyped(KeyEvent arg0) {
				escribirNumericoConPunto(textField_4.getText(), arg0);
			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(119, 210, 86, 20);
		frame.getContentPane().add(textField_4);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(45, 122, 56, 20);
		frame.getContentPane().add(comboBox);
		
		lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 125, 34, 14);
		frame.getContentPane().add(lblHora);
		
		lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(111, 125, 56, 14);
		frame.getContentPane().add(lblMinutos);
		
		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setBounds(159, 122, 56, 20);
		frame.getContentPane().add(comboBox_1);
		
		label_3 = new JLabel("Hora:");
		label_3.setBounds(10, 185, 34, 14);
		frame.getContentPane().add(label_3);
		
		comboBox_8 = new JComboBox<Integer>();
		comboBox_8.setBounds(45, 182, 56, 20);
		frame.getContentPane().add(comboBox_8);
		int horaInicial=00;
		int horaFinal=24;
		for(int i=horaInicial; i<=horaFinal; i++){
			comboBox.addItem(new Integer(i));
			comboBox_8.addItem(new Integer(i));
		}
		
		label_4 = new JLabel("Minutos:");
		label_4.setBounds(111, 185, 56, 14);
		frame.getContentPane().add(label_4);
		
		comboBox_9 = new JComboBox<Integer>();
		comboBox_9.setBounds(159, 182, 56, 20);
		frame.getContentPane().add(comboBox_9);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(114, 72, 178, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Agrego la fecha de hoy automaticamente
				DateFormat fechaAct = new SimpleDateFormat("dd/MM/yy");
				Date dateobj = new Date();
				lblNewLabel_1.setText(fechaAct.format(dateobj));
				lblFechaDeHoy = new JLabel("Fecha de hoy:");
				lblFechaDeHoy.setBounds(10, 73, 103, 14);
				frame.getContentPane().add(lblFechaDeHoy);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//codigo de lo que hace el boton INGRESAR
				try{
					String codigo = textField_1.getText().trim();
					String destino = textField_2.getText().trim();
					java.util.Date fecha = new Date();
					Date hPartida = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDay(), Integer.parseInt(comboBox.getSelectedItem().toString()), Integer.parseInt(comboBox_1.getSelectedItem().toString()));
					Date hLlegada = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDay(), Integer.parseInt(comboBox_8.getSelectedItem().toString()), Integer.parseInt(comboBox_9.getSelectedItem().toString())); 
					if(!textField_4.getText().isEmpty()){
						double precioBase = Double.parseDouble(textField_4.getText().toString());
						Controladora_RegistroExcursion c;
						c = new Controladora_RegistroExcursion(((RegistroExcursion) getVentanaAbierta()));
						c.registroExcursion(codigo, destino, hPartida, hLlegada, precioBase);	
					}else{
						mostrarError("La Excursion no cuenta con un precio base",0);
					}
				} catch (Exc_Persistencia e) {
					mostrarError("Error al cargar el archivo .properties", 0);
				} catch(MalformedURLException | RemoteException | NotBoundException e){
					mostrarError("Error de conexion",0);
				}
			}
		});
		btnIngresar.setBounds(189, 249, 97, 27);
		frame.getContentPane().add(btnIngresar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				setVentanaAbierta(null);
			}
		});
		btnVolver.setBounds(296, 249, 97, 27);
		frame.getContentPane().add(btnVolver);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 120, 103, 14);
		frame.getContentPane().add(label);
		
		int minInicial=00;
		int minFinal=59;
		for(int i=minInicial; i<=minFinal; i++){
			comboBox_1.addItem(new Integer(i));
			comboBox_9.addItem(new Integer(i));
		}
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
