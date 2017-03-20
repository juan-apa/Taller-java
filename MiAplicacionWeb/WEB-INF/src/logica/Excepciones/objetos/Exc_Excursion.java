package logica.Excepciones.objetos;

public class Exc_Excursion extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Exc_Excursion(){
		super("Error generico en Excursion.");
	}
	public Exc_Excursion(String mensaje){
		super(mensaje);
	}
}
