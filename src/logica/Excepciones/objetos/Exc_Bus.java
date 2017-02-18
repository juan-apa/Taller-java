package logica.Excepciones.objetos;

public class Exc_Bus extends Exception{
	
	public Exc_Bus(){
		super("Error generico en Bus.");
	}
	
	public Exc_Bus(String mensaje){
		super(mensaje);
	}
}
