package vistaGrafica.ventanas;


import java.awt.Dimension;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;

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
	private JComboBox<Integer> comboBox_5;
	private JLabel label;
	private JComboBox<Integer> comboBox_6;
	private JLabel label_1;
	private JComboBox<Integer> comboBox_7;
	private JLabel label_2;
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
		frame.setSize(new Dimension(419, 322));
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
		lblHoraDePartida.setBounds(10, 61, 103, 14);
		frame.getContentPane().add(lblHoraDePartida);
		
		JLabel lblHoraDeLlegada = new JLabel("Fecha de Llegada:");
		lblHoraDeLlegada.setBounds(10, 152, 103, 14);
		frame.getContentPane().add(lblHoraDeLlegada);
		
		JLabel lblPrecioBase = new JLabel("Precio Base:");
		lblPrecioBase.setBounds(10, 255, 86, 14);
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
		textField_4.setColumns(10);
		textField_4.setBounds(119, 252, 86, 20);
		frame.getContentPane().add(textField_4);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(45, 121, 56, 20);
		frame.getContentPane().add(comboBox);
		
		lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 124, 34, 14);
		frame.getContentPane().add(lblHora);
		
		lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(111, 124, 56, 14);
		frame.getContentPane().add(lblMinutos);
		
		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setBounds(159, 121, 56, 20);
		frame.getContentPane().add(comboBox_1);
		
		comboBox_5 = new JComboBox<Integer>();
		comboBox_5.setBounds(258, 172, 56, 20);
		frame.getContentPane().add(comboBox_5);
		//carga de dias combobox	ver meses con 28 29 30 31 dias
		int diaInicial=1;
		int diaFinal=31;
		for(int i=diaInicial; i<=diaFinal; i++){
			comboBox_5.addItem(new Integer(i));
		}
		
		label = new JLabel("Dia:");
		label.setBounds(225, 169, 23, 20);
		frame.getContentPane().add(label);
		
		comboBox_6 = new JComboBox<Integer>();
		comboBox_6.setBounds(159, 172, 56, 20);
		frame.getContentPane().add(comboBox_6);
		int mesInicial=1;
		int mesFinal=12;
		for(int i=mesInicial; i<=mesFinal; i++){
			comboBox_6.addItem(new Integer(i));
		}
		
		label_1 = new JLabel("Mes:");
		label_1.setBounds(111, 172, 38, 20);
		frame.getContentPane().add(label_1);
		
		comboBox_7 = new JComboBox<Integer>();
		comboBox_7.setBounds(45, 175, 56, 20);
		frame.getContentPane().add(comboBox_7);
		//Carga de combobox años
		int añoInicial=2017;
		int añoFinal=2030;
		for(int i=añoInicial; i<=añoFinal; i++){
			comboBox_7.addItem(new Integer(i));
		}
		
		
		label_2 = new JLabel("A\u00F1o:");
		label_2.setBounds(10, 175, 34, 20);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("Hora:");
		label_3.setBounds(10, 213, 34, 14);
		frame.getContentPane().add(label_3);
		
		comboBox_8 = new JComboBox<Integer>();
		comboBox_8.setBounds(45, 210, 56, 20);
		frame.getContentPane().add(comboBox_8);
		int horaInicial=00;
		int horaFinal=24;
		for(int i=horaInicial; i<=horaFinal; i++){
			comboBox.addItem(new Integer(i));
			comboBox_8.addItem(new Integer(i));
		}
		
		label_4 = new JLabel("Minutos:");
		label_4.setBounds(111, 213, 56, 14);
		frame.getContentPane().add(label_4);
		
		comboBox_9 = new JComboBox<Integer>();
		comboBox_9.setBounds(159, 210, 56, 20);
		frame.getContentPane().add(comboBox_9);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//codigo de lo que hace el boton INGRESAR
				String codigo = textField_1.getText();
				String destino = textField_2.getText();
				java.util.Date fecha = new Date();
				Date hPartida = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDay(), Integer.parseInt(comboBox.getSelectedItem().toString()), Integer.parseInt(comboBox_1.getSelectedItem().toString()));
				Date hLlegada = new Date(Integer.parseInt(comboBox_7.getSelectedItem().toString()), Integer.parseInt(comboBox_6.getSelectedItem().toString()), Integer.parseInt(comboBox_5.getSelectedItem().toString()), Integer.parseInt(comboBox_8.getSelectedItem().toString()), Integer.parseInt(comboBox_9.getSelectedItem().toString())); 
				double precioBase = Double.parseDouble(textField_4.getSelectedText().toString());
				Controladora_RegistroExcursion c;
				c = new Controladora_RegistroExcursion(((RegistroExcursion) getVentanaAbierta()));
				c.registroExcursion(codigo, destino, hPartida, hLlegada, precioBase);
			}
		});
		btnIngresar.setBounds(296, 213, 97, 27);
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
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(114, 85, 178, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Agrego la fecha de hoy automaticamente
		DateFormat fechaAct = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();
		lblNewLabel_1.setText(fechaAct.format(dateobj));
		lblFechaDeHoy = new JLabel("Fecha de hoy:");
		lblFechaDeHoy.setBounds(10, 86, 103, 14);
		frame.getContentPane().add(lblFechaDeHoy);
		
		
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
