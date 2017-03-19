package logica.Excepciones.colecciones;

public class Exc_Excursiones extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Exc_Excursiones(){
		super("Error generico en Excursiones.");
	}
	public Exc_Excursiones(String mensaje){
		super(mensaje);
	}
}
