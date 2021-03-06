package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.Excepciones.objetos.Exc_Persistencia;
import logica.fachada.IFachada;
import persistencia.Propiedades;
import vistaGrafica.ventanas.RespaldoCargar;

public class Controladora_RespaldoCargar {
	private IFachada f;
	private RespaldoCargar ven;
	
	public Controladora_RespaldoCargar(RespaldoCargar ventana) throws  MalformedURLException, RemoteException, NotBoundException, Exc_Persistencia {
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
	
	public void cargar(boolean primera){
		try {
			f.recuperar();
			if(!primera){
				ven.mostrarCorrecto("Recuperado Correctamente");
			}
			ven.setVentanaAbierta(null);
			ven.setVisible(false);
		}catch (Exc_Persistencia e) {
			if(e.getMessage().contains("informacion")){
				ven.mostrarError("Error, el archivo no existe. Guarde para generar el archivo", 0);
			}else{
				ven.mostrarError("Error al cargar el archivo .properties", 0);
			}
		}catch (RemoteException e1){
			ven.mostrarError(e1.toString(), 0);
		}
	}
}
