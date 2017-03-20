package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.fachada.IFachada;
import persistencia.Propiedades;
import vistaGrafica.ventanas.RecaudacionExcursion;

public class Controladora_RecaudacionExcursion {
	private IFachada f;
	private RecaudacionExcursion ven;
	
	public Controladora_RecaudacionExcursion(RecaudacionExcursion ventana) throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException  {
		try {
			ven = ventana;
			Propiedades p = new Propiedades();
			String puerto = p.buscar("Puerto");
			String ip = p.buscar("Ip");
			f = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
		}catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw e;
		} catch (Exc_Persistencia e2) {
			throw e2;
		}
	}
	
	public void recaudadoEnExcursion(String codigo){
		if (codigo.isEmpty()){
			ven.mostrarError("El campo del codigo esta vacio.", 1);
		}else{
			try {
				double total;
				total = f.recaudadoEnExcursion(codigo.trim().toUpperCase());
				ven.mostrarCorrecto("El total recaudado para "+codigo+" es "+total+".");
			} catch (RemoteException e) {
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Boletos e) {
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Excursiones e) {
				ven.mostrarError(e.toString(), 0);
			}
		}
	}
}
