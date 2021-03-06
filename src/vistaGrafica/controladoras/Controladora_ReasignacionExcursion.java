package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import persistencia.Propiedades;
import vistaGrafica.ventanas.ReasignacionExcursion;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.fachada.IFachada;

public class Controladora_ReasignacionExcursion {
	private IFachada f;
	private ReasignacionExcursion ven;
	
	public Controladora_ReasignacionExcursion(ReasignacionExcursion ventana) throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException {
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
	
	public void reasignacionExcursion(String codigo){
		if (codigo.isEmpty()){
			ven.mostrarError("El codigo esta vacio", 1);
		}else{
			try {
				f.reasignacionExcursion(codigo.trim().toUpperCase());
				ven.mostrarCorrecto("Reasignada Correctamente!");
				ven.setVentanaAbierta(null);
				ven.setVisible(false);
			} catch (RemoteException | Exc_Buses | Exc_Excursiones e) {
				ven.mostrarError(e.toString(), 0);
			}
		}
			
		
	}
}
