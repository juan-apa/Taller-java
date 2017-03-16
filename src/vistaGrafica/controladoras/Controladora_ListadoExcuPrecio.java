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
import vistaGrafica.ventanas.ListadoExcuDestino;
import vistaGrafica.ventanas.ListadoExcuPrecio;

public class Controladora_ListadoExcuPrecio {
	private IFachada f;
	private ListadoExcuPrecio ven;
	
	public Controladora_ListadoExcuPrecio(ListadoExcuPrecio ventana){
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
	
	public Iterador<VOExcursionListado> ListadoExcuPrecio(String desde, String hasta){
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if (desde.isEmpty()){
			ven.mostrarError("El precio desde esta vacio", 0);
		}else{
			if (hasta.isEmpty()){
				ven.mostrarError("El precio hasta esta vacio", 0);
			}else{
				double des=Double.parseDouble(desde);
				double has=Double.parseDouble(hasta);
				try{
					ret=f.listadoExcursionesPrecio(des,has);
				} catch (RemoteException e) {
					ven.mostrarError(e.toString(), 0);
				}catch (Exc_Excursiones e) {
					ven.mostrarError(e.toString(), 0);
				}
				
				
				
				
			}
		}
		
		
		return ret;
	}
	
	
	
	
	
}
