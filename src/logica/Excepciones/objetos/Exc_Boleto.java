package logica.Excepciones.objetos;

public class Exc_Boleto extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Exc_Boleto(){
		super("Error generico en Exc_Boleto.");
	}
	public Exc_Boleto(String mensaje){
		super(mensaje);
	}
}
