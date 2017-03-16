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
	
	public Controladora_RegistroBoleto(RegistroBoleto ventana) {
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
	
	public void ventaBoleto(String codExcursion, String lugarPrecedencia, int edadPasajero, long nroCelular, String tipoBoleto, double dtoAdicioal){
		VOBoleto entrada = new VOBoleto(codExcursion, lugarPrecedencia, edadPasajero, nroCelular, tipoBoleto, dtoAdicioal);
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
						if(!f.getExcursiones().exists(entrada.getCodExcursion())){
							ven.mostrarError("No existe una excursion con este codigo", 0);
						}else{
							if(f.getExcursiones().find(entrada.getCodExcursion()).getBoletos().full()){
								ven.mostrarError("Todos los boletos para esta excursion ya estan vendidos", 0);
							}else{
								f.ventaBoleto(entrada);
								ven.mostrarCorrecto("Boleto Vendido Correctamente");
							}
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						ven.mostrarError(e.toString(), 0);
					} catch (Exc_Boleto e) {
						// TODO Auto-generated catch block
						ven.mostrarError(e.toString(), 0);
					} catch (Exc_Boletos e) {
						// TODO Auto-generated catch block
						ven.mostrarError(e.toString(), 0);
					} catch (Exc_Excursiones e) {
						// TODO Auto-generated catch block
						ven.mostrarError(e.toString(), 0);
					}
				}
			}
		}
	}
}
