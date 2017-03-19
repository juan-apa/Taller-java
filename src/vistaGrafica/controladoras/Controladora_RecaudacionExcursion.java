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
		try {
			if(!f.getExcursiones().exists(codigo)){
				ven.mostrarError("La excursion con el codigo " + codigo + " no se encuentra ingresada en el sistema.", 1);
			}else{
				if(f.getExcursiones().find(codigo).getBoletos().empty()){
					ven.mostrarError("No hay boletos vendidos para la excursion con el codigo "+codigo+".", 1);
				}else{
					double total;
					try {
						total = f.recaudadoEnExcursion(codigo);
						ven.mostrarCorrecto("El total recaudado para "+codigo+" es "+total+".");
					} catch (RemoteException | Exc_Boletos | Exc_Excursiones e) {
						// TODO Auto-generated catch block
						ven.mostrarError(e.toString(), 0);
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			ven.mostrarError(e.toString(), 0);
		}
		
	}
}
