package logica.Excepciones.colecciones;

public class Exc_Boletos extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Exc_Boletos(){
		super("Error generico en Boletos.");
	}
	public Exc_Boletos(String mensaje){
		super(mensaje);
	}
}
