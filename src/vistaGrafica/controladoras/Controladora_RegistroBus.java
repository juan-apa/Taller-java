package vistaGrafica.controladoras;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import persistencia.Propiedades;
import vistaGrafica.ventanas.RegistroDeBus;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBus;
import logica.fachada.IFachada;

public class Controladora_RegistroBus {
	private IFachada f;
	private RegistroDeBus ven;
	
	public Controladora_RegistroBus(RegistroDeBus ventana) {
		try {
//			ven = new RegistroDeBus();
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
	
	public void registroBus(String matricula, String marca, int capPasajeros) {
		VOBus entrada = new VOBus(matricula, marca, capPasajeros);
		if((entrada.getMatricula() == null) || (entrada.getMatricula() == "")){
			ven.mostrarError("El Bus a registrar no cuenta con una Matricula", 0);
		}else{
			//no esta funcionando esta prueba
//			if((matricula.matches(".*[a-zA-Z].*"))&&(matricula.matches("[0-9]"))){
			if((matricula.matches("^[a-zA-Z0-9]*$"))){
				ven.mostrarError("La matricula ingresada no es Alfanumerica", 0);
			}else{
				if((entrada.getMarca() == null) || (entrada.getMarca() == "")){
					ven.mostrarError("El Bus a registrar no cuenta con una Marca", 0);
				}else{
					if(entrada.getCapPasajeros() <= 0){
						ven.mostrarError("La cantidad de asientos '" + capPasajeros + "' no es valida. La cantidad de pasajeros tiene que ser mayor a '0'.", 0);
					}else{
						try {
							if(f.getBuses().exists(matricula)){
								ven.mostrarError("Ya existe un Bus con la misma Matricula", 0);
							}else{
								try {
									f.registroNuevoBus(entrada);
									ven.mostrarCorrecto("Ingreso con Exito!");
								} catch (RemoteException | Exc_Bus | Exc_Buses e) {
									// TODO Auto-generated catch block
									ven.mostrarError(e.toString(), 0);
								}
							}
						} catch (RemoteException e) {
							ven.mostrarError(e.toString(), 0);
						}
					}
				}
			}
		}
	}
	
}
