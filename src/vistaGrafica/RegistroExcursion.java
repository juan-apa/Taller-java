package vistaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class RegistroExcursion
{
	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JComboBox comboBox;
	private JLabel lblHora;
	private JLabel lblMinutos;
	private JComboBox comboBox_1;
	private JLabel lblAo;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JLabel lblMes;
	private JLabel lblDia;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JLabel label;
	private JComboBox comboBox_6;
	private JLabel label_1;
	private JComboBox comboBox_7;
	private JLabel label_2;
	private JLabel label_3;
	private JComboBox comboBox_8;
	private JLabel label_4;
	private JComboBox comboBox_9;

	/* Constructor de la ventana */
	public RegistroExcursion() {
		initialize();
	}

	/* Inicializo los componentes de la ventana */
	private void initialize() 
	{
		/* marco de la ventana secundaria */
		frame = new JFrame();
		frame.setSize(new Dimension(419, 322));
		frame.setTitle("Registro de Excursion");
		
		/* cuando intenten cerrarme, solamente me cierro yo */
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		JLabel lblHoraDeLlegada = new JLabel("Hora de Llegada:");
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
		
		comboBox = new JComboBox();
		comboBox.setBounds(45, 121, 56, 20);
		frame.getContentPane().add(comboBox);
		
		lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 124, 34, 14);
		frame.getContentPane().add(lblHora);
		
		lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(111, 124, 56, 14);
		frame.getContentPane().add(lblMinutos);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(159, 121, 56, 20);
		frame.getContentPane().add(comboBox_1);
		
		lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(10, 86, 34, 20);
		frame.getContentPane().add(lblAo);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(45, 86, 56, 20);
		frame.getContentPane().add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(159, 83, 56, 20);
		frame.getContentPane().add(comboBox_3);
		
		lblMes = new JLabel("Mes:");
		lblMes.setBounds(111, 86, 38, 14);
		frame.getContentPane().add(lblMes);
		
		lblDia = new JLabel("Dia:");
		lblDia.setBounds(225, 86, 23, 14);
		frame.getContentPane().add(lblDia);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setBounds(258, 83, 56, 20);
		frame.getContentPane().add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setBounds(258, 172, 56, 20);
		frame.getContentPane().add(comboBox_5);
		//carga de dias combobox	ver meses con 28 29 30 31 dias
		int diaInicial=1;
		int diaFinal=31;
		for(int i=diaInicial; i<=diaFinal; i++){
			comboBox_4.addItem(new Integer(i));
			comboBox_5.addItem(new Integer(i));
		}
		
		label = new JLabel("Dia:");
		label.setBounds(225, 169, 23, 20);
		frame.getContentPane().add(label);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setBounds(159, 172, 56, 20);
		frame.getContentPane().add(comboBox_6);
		int mesInicial=1;
		int mesFinal=12;
		for(int i=mesInicial; i<=mesFinal; i++){
			comboBox_3.addItem(new Integer(i));
			comboBox_6.addItem(new Integer(i));
		}
		
		label_1 = new JLabel("Mes:");
		label_1.setBounds(111, 172, 38, 20);
		frame.getContentPane().add(label_1);
		
		comboBox_7 = new JComboBox();
		comboBox_7.setBounds(45, 175, 56, 20);
		frame.getContentPane().add(comboBox_7);
		//Carga de combobox años
		int añoInicial=2017;
		int añoFinal=2030;
		for(int i=añoInicial; i<=añoFinal; i++){
			comboBox_2.addItem(new Integer(i));
			comboBox_7.addItem(new Integer(i));
		}
		
		
		label_2 = new JLabel("A\u00F1o:");
		label_2.setBounds(10, 175, 34, 20);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("Hora:");
		label_3.setBounds(10, 213, 34, 14);
		frame.getContentPane().add(label_3);
		
		comboBox_8 = new JComboBox();
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
		
		comboBox_9 = new JComboBox();
		comboBox_9.setBounds(159, 210, 56, 20);
		frame.getContentPane().add(comboBox_9);
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
}
