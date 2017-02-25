package logica.Excepciones.objetos;

public class Exc_Persistencia extends Exception{
	public Exc_Persistencia(){
		super("Error generico en Persistencia.");
	}
	public Exc_Persistencia(String mensaje){
		super(mensaje);
	}
}
