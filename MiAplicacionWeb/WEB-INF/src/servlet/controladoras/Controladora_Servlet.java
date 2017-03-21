package servlet.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import persistencia.Propiedades;
import servlet.servlets.Servlet_Listado;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.fachada.IFachada;

public class Controladora_Servlet {
	private IFachada f;
	private Servlet_Listado servlet;

	public Controladora_Servlet(Servlet_Listado serv){
		try {
			servlet = serv;
//			Propiedades p = new Propiedades();
//			String puerto = p.buscar("Puerto");
//			String ip = p.buscar("Ip");
//			f = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
			f = (IFachada) Naming.lookup("//localhost:1099/fachada");
		}catch (MalformedURLException | RemoteException | NotBoundException e) {
			this.servlet.setMensajeError("Error al conectarse con el servidor");
			this.servlet.setError("true");
		}/* catch (Exc_Persistencia e) {
			this.servlet.setMensajeError("Error al leer las properties.");
			this.servlet.setError("true");
		}*/
	}

	public ArrayList<VOExcursionListado> listadoExcuDestino(String destino){
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if(!destino.isEmpty()){
			try {
				ret=f.listadoExcursionesDestino(destino.toUpperCase());
			} catch (RemoteException e) {
				this.servlet.setMensajeError("Error al conectarse con el servidor");
				this.servlet.setError("true");
			} catch (Exc_Excursiones e) {
				if(e.getMessage() != null){
					if(e.getMessage().contains("destino")){
						this.servlet.setMensajeError("No hay excursiones ingresadas en el sistema con ese destino");
					}
					else{
						this.servlet.setMensajeError("No hay excursiones ingresadas en el sistema.");
					}
				}
				else{
					this.servlet.setMensajeError("No hay excursiones ingresadas en el sistema");
				}
				this.servlet.setAdvertencia("true");
			}
		}
		else{
			this.servlet.setMensajeError("Destino no puede estar vacío");
			this.servlet.setAdvertencia("true");
		}
		return ret.toArray();
	}

	public ArrayList<VOExcursionListado> listadoExcuPrecio(String precioMin,
			String precioMax){
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if(precioMin.isEmpty() || precioMin.isEmpty()){
			this.servlet.setMensajeError("Los precios no pueden estar vacios");
			this.servlet.setAdvertencia("true");
		}
		else{
			Double precmin = Double.parseDouble(precioMin);
			Double precmax = Double.parseDouble(precioMax);
			if(precmin>=precmax){
				this.servlet.setMensajeError("El precio minimo tiene que ser menor al precio maximo");
				this.servlet.setAdvertencia("true");
			}
			else{
				try {
					ret = f.listadoExcursionesPrecio(precmin, precmax);
				} catch (RemoteException e) {
					this.servlet.setMensajeError("Error al conectarse con el servidor");
					this.servlet.setError("true");
				} catch(Exc_Excursiones e){
					this.servlet.setMensajeError("No hay excursiones ingresadas en el sistema");
					this.servlet.setAdvertencia("true");
				}
			}
		}
		return ret.toArray();
	}

}
