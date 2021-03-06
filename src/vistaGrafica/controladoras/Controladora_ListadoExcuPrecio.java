package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;
import persistencia.Propiedades;
import vistaGrafica.ventanas.ListadoExcuPrecio;

public class Controladora_ListadoExcuPrecio {
	private IFachada f;
	private ListadoExcuPrecio ven;
	
	public Controladora_ListadoExcuPrecio(ListadoExcuPrecio ventana) throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException{
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
	
	public Iterador<VOExcursionListado> ListadoExcuPrecio(String desde, String hasta){
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if (desde.isEmpty()){
			ven.mostrarError("El precio inicial esta vacio", 1);
		}else{
			if (hasta.isEmpty()){
				ven.mostrarError("El precio final esta vacio", 1);
			}else{
				double des=Double.parseDouble(desde);
				double has=Double.parseDouble(hasta);
				if (des>has){
					ven.mostrarError("El precio inicial, debe de ser menor que el precio final", 1);
				}else{
					try{
						ret=f.listadoExcursionesPrecio(des,has);
					} catch (RemoteException e) {
						ven.mostrarError(e.toString(), 0);
					}catch (Exc_Excursiones e) {
						ven.mostrarError(e.toString(), 1);
					}
				}	
			}
		}
		return ret;
	}
	
	
	
	
	
}
