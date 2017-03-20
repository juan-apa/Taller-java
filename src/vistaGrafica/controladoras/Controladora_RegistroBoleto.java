package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import persistencia.Propiedades;
import vistaGrafica.ventanas.RegistroBoleto;
import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Boleto;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBoleto;
import logica.fachada.IFachada;


public class Controladora_RegistroBoleto {
	private IFachada f;
	private RegistroBoleto ven;
	
	public Controladora_RegistroBoleto(RegistroBoleto ventana)throws MalformedURLException, RemoteException, NotBoundException, Exc_Persistencia {
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
	
	public void ventaBoleto(String codExcursion, String lugarPrecedencia, int edadPasajero, long nroCelular, String tipoBoleto, double dtoAdicioal){
		VOBoleto entrada = new VOBoleto(codExcursion.trim().toUpperCase(), lugarPrecedencia.trim().toUpperCase(), edadPasajero, nroCelular, dtoAdicioal);
		if(!entrada.getCodExcursion().isEmpty()){	
			if(!entrada.getLugarPrecedencia().isEmpty()){
				if(entrada.getEdadPasajero() < 0){
					ven.mostrarError("La edad del pasajero no puede ser menor a 0", 0);
				}else{
					if(entrada.getNroCelular()/10000000 < 1){
						ven.mostrarError("Al numero de telefono le faltan digitos", 0);
					}else{
						if(entrada.getDtoAdicional() < 0.0){
							ven.mostrarError("El descuento no puede ser menor a 0", 0);
						}else{
							try {	
								f.ventaBoleto(entrada);
								ven.mostrarCorrecto("Boleto Vendido Correctamente");
								ven.setVentanaAbierta(null);
								ven.setVisible(false);
							} catch (RemoteException e) {
								ven.mostrarError(e.toString(), 0);
							} catch (Exc_Boleto e) {
								ven.mostrarError(e.toString(), 0);
							} catch (Exc_Boletos e) {
								ven.mostrarError(e.toString(), 0);
							} catch (Exc_Excursiones e) {
								ven.mostrarError(e.toString(), 0);
							}
						}
					}
				}
			}else{
				ven.mostrarError("El boleto no cuenta con una procedencia del pasajero", 0);
			}
		}else{
			ven.mostrarError("El boleto no cuenta con un codigo de excursion", 0);
		}
	}
	
}
