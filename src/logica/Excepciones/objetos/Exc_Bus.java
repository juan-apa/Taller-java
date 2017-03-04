package logica.Excepciones.objetos;

public class Exc_Bus extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Exc_Bus(){
		super("Error generico en Bus.");
	}
	
	public Exc_Bus(String mensaje){
		super(mensaje);
	}
}
