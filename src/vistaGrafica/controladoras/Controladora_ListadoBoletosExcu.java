package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import persistencia.Propiedades;
import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBoleto2;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;
import vistaGrafica.ventanas.ListadoBoletosExcu;

public class Controladora_ListadoBoletosExcu {
	private IFachada f;
	private ListadoBoletosExcu ven;
	
	public Controladora_ListadoBoletosExcu(ListadoBoletosExcu ventana)throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException{
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
	
	public Iterador <VOBoleto2> ListadoBoletosExc(String codigo, String tipo){
		Iterador<VOBoleto2> ret = new Iterador<VOBoleto2>();
		if(codigo.isEmpty()){
			ven.mostrarError("El codigo de la excursion esta vacio", 0);
		}else{
			try {
				ret=f.listadoBoletosExcursion(codigo, tipo);
			} catch (RemoteException e) {
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Boletos e) {
				ven.mostrarError(e.toString(), 0);
			} catch (Exc_Excursiones e) {
				ven.mostrarError(e.toString(), 1);
			}
		}
	return ret;
	}
	

}
