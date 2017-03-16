package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOExcursion;
import logica.fachada.IFachada;
import persistencia.Propiedades;
import vistaGrafica.ventanas.RegistroExcursion;

public class Controladora_RegistroExcursion {
	private IFachada f;
	private RegistroExcursion ven;
	
	public Controladora_RegistroExcursion(RegistroExcursion ventana) {
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
	
	public void registroExcursion(String codigo, String destino, Date hPartida, Date hLlegada, double precioBase){
		VOExcursion entrada = new VOExcursion(codigo, destino, hPartida, hLlegada, precioBase);
		try {
			if(f.getBuses().empty()){
				ven.mostrarError("No hay buses registrados en el sistema", 0);
			}else{
				if(entrada.getPrecioBase() <= 0){
					ven.mostrarError("El precio base debe ser mayor a 0", 0);
				}else{
					if((entrada.gethLlegada().before(entrada.gethPartida())) || (entrada.gethPartida().equals(entrada.gethLlegada()))){
						ven.mostrarError("La hora de partida debe ser menor a la hora de llegada", 0);
					}else{
						if(f.getExcursiones().exists(entrada.getCodigo())){
							ven.mostrarError("Existe una excursion con el mismo codigo", 0);
						}else{
							f.registroNuevaExcursion(entrada);
							ven.mostrarCorrecto("Ingreso con Exito!");
						}
					}
				}
			}
		} catch (RemoteException | Exc_Excursiones | Exc_Buses | Exc_Excursion e) {
			// TODO Auto-generated catch block
			ven.mostrarError(e.toString(), 0);;
		}
	}
}
