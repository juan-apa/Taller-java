package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import persistencia.Propiedades;
import vistaGrafica.ventanas.ListadoExcuDestino;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;

public class Controladora_ListadoExcuDestino {
	private IFachada f;
	private ListadoExcuDestino ven;
	
	public Controladora_ListadoExcuDestino(ListadoExcuDestino ventana) throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException{
		try{
			ven=ventana;
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
	
	
	public Iterador <VOExcursionListado> ListadoExcuDestino (String destino){
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if (destino.isEmpty()){
			ven.mostrarError("El destino esta vacio", 1);
		}else{
			try{
				ret=f.listadoExcursionesDestino(destino);
			} catch (RemoteException e) {
				ven.mostrarError(e.toString(), 0);
			}catch (Exc_Excursiones e) {
				ven.mostrarError(e.toString(), 1);
			}
			
			
		}
		
		return ret;
	}
	
	
	
}
