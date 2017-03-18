package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.Excepciones.objetos.Exc_Persistencia;
import logica.fachada.IFachada;
import persistencia.Propiedades;
import vistaGrafica.ventanas.RespaldoGuardar;

public class Controladora_RespaldoGuardar {
	private IFachada f;
	private RespaldoGuardar ven;
	
	public Controladora_RespaldoGuardar(RespaldoGuardar ventana) throws Exc_Persistencia, MalformedURLException,  RemoteException,  NotBoundException {
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
	
	public void guardar(){
		try {
			f.respaldar();
			ven.mostrarCorrecto("Guardado Correctamente");
			ven.setVentanaAbierta(null);
			ven.setVisible(false);
		}catch (Exc_Persistencia e) {
			// TODO Auto-generated catch block
			ven.mostrarError(e.toString(), 0);
		}catch (RemoteException e1){
			ven.mostrarError(e1.toString(), 0);
		}
	}
}
