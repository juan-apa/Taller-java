package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import persistencia.Propiedades;
import vistaGrafica.ventanas.ListadoBuses;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBusExc;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;

public class Controladora_ListaBus {
	private IFachada f;
	private ListadoBuses ven;
	
	public Controladora_ListaBus (ListadoBuses ventana)throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException{
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

	public Iterador<VOBusExc> ListadoBuses(){
		Iterador<VOBusExc> ret = new Iterador<VOBusExc>();
		try {
			ret=f.listadoGeneralBuses();
			return ret;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			ven.mostrarError(e.toString(), 0);
			ret = null;
			return ret;
		} catch (Exc_Buses e) {
			// TODO Auto-generated catch block
			ven.mostrarError(e.toString(), 0);
			ret = null;
			return ret;
		}
	}
	
	
	
}
