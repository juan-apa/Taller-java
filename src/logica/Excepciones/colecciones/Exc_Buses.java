package logica.Excepciones.colecciones;

public class Exc_Buses extends Exception{
	public Exc_Buses(){
		super("Error generico en Buses.");
	}
	public Exc_Buses(String mensaje){
		super(mensaje);
	}
}
