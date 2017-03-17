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
	
	public Controladora_RespaldoCargar(RespaldoCargar ventana) {
		try {
			ven = ventana;
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
	
	public void cargar(){
		try {
			f.recuperar();
			ven.mostrarCorrecto("Recuperado Correctamente");
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
