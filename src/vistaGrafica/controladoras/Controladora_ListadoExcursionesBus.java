package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;
import persistencia.Propiedades;
import vistaGrafica.ventanas.ListadoExcursionesBus;

public class Controladora_ListadoExcursionesBus {
	private IFachada f;
	private ListadoExcursionesBus ven;
	
	public Controladora_ListadoExcursionesBus(ListadoExcursionesBus ventana){
		try{
			ven=ventana;
			Propiedades p = new Propiedades();
			String puerto = p.buscar("Puerto");
			String ip = p.buscar("Ip");
			f = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
		}catch (MalformedURLException | RemoteException | NotBoundException e) {
			ven.mostrarError(e.toString(), 0);
		} catch (Exc_Persistencia e2) {
			// TODO Auto-generated catch block
			ven.mostrarError(e2.toString(), 0);
		}
	}
	
	public Iterador<VOExcursionListado> ListadoExcursionesBus (String mat){
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if (mat.isEmpty()){
			ven.mostrarError("La matricula esta vacia", 0);
		}else{
			try {
				ret=f.listadoExcursionesDeBus(mat);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Bus e) {
				// TODO Auto-generated catch block
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Buses e) {
				// TODO Auto-generated catch block
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Excursiones e) {
				// TODO Auto-generated catch block
				ven.mostrarError(e.toString(), 0);
			}
		}
		return ret;
		
	}
	
	
	
	
	
	
	
	
}
