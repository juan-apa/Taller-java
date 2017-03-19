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
	
	public Controladora_RegistroExcursion(RegistroExcursion ventana) throws MalformedURLException, RemoteException, NotBoundException, Exc_Persistencia {
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
	
	public void registroExcursion(String codigo, String destino, Date hPartida, Date hLlegada, double precioBase){
		VOExcursion entrada = new VOExcursion(codigo.trim().toUpperCase(), destino.trim().toUpperCase(), hPartida, hLlegada, precioBase);
		try {
			if(!entrada.getDestino().isEmpty()){
				if(!entrada.getCodigo().isEmpty()){
					if(f.getBuses().empty()){
						ven.mostrarError("No hay buses registrados en el sistema", 1);
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
									ven.setVentanaAbierta(null);
									ven.setVisible(false);
								}
							}
						}
					}
				}else{
					ven.mostrarError("La excursion ha ingresar no cuenta con un Codigo", 1);
				}
			}else{
				ven.mostrarError("La excursion ha ingresar no cuenta con un Destino", 1);
			}
		} catch (RemoteException | Exc_Excursiones | Exc_Buses | Exc_Excursion e) {
			// TODO Auto-generated catch block
			ven.mostrarError(e.toString(), 0);;
		}
	}
}
