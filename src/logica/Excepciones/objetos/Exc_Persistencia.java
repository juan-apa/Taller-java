package logica.Excepciones.objetos;

public class Exc_Persistencia extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Exc_Persistencia(){
		super("Error generico en Persistencia.");
	}
	public Exc_Persistencia(String mensaje){
		super(mensaje);
	}
}
