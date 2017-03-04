package logica.Excepciones.colecciones;

public class Exc_Buses extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Exc_Buses(){
		super("Error generico en Buses.");
	}
	public Exc_Buses(String mensaje){
		super(mensaje);
	}
}
