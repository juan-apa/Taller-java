package logica.Excepciones.colecciones;

public class Exc_Excursiones extends Exception{
	public Exc_Excursiones(){
		super("Error generico en Excursiones.");
	}
	public Exc_Excursiones(String mensaje){
		super(mensaje);
	}
}
