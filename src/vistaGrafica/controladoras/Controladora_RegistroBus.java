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
	
	public Controladora_RegistroBus() throws MalformedURLException, RemoteException, NotBoundException, Exc_Persistencia{
		try {
			Propiedades p = new Propiedades();
			String puerto = p.buscar("Puerto");
			String ip = p.buscar("Ip");
			IFachada fachada = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
		}catch(MalformedURLException | RemoteException | NotBoundException | Exc_Persistencia e){
//			ven.mostrarError(e.toString().substring(e.toString().indexOf(':') + 2), 0);
			throw e;
		}
	}
	
	public void registroBus(String matricula, String marca, int capPasajeros){
		VOBus entrada = new VOBus(matricula, marca, capPasajeros);
		
		if((entrada.getMatricula() == null) || (entrada.getMatricula() == "")){
//			throw new Exc_Bus("El Bus a registrar no cuenta con una Matricula");
			ven.mostrarError("El Bus a registrar no cuenta con una Matricula", 0);
		}else{
			if(!entrada.getMatricula().matches("[a-z0-9]+")){
//				throw new Exc_Bus("La matricula ingresada no es Alfanumerica");
				ven.mostrarError("La matricula ingresada no es Alfanumerica", 0);
			}else{
				if((entrada.getMarca() == null) || (entrada.getMarca() == "")){
//					throw new Exc_Bus("El Bus a registrar no cuenta con una Marca");
					ven.mostrarError("El Bus a registrar no cuenta con una Marca", 0);
				}else{
					if(entrada.getCapPasajeros() <= 0){
//						throw new Exc_Bus("La cantidad de asientos '" + entrada.getCapPasajeros() + "' no es valida. La cantidad de pasajeros tiene que ser mayor a '0'.");
						ven.mostrarError("La cantidad de asientos '" + entrada.getCapPasajeros() + "' no es valida. La cantidad de pasajeros tiene que ser mayor a '0'.", 0);
					}else{
//						try {
//							if(f.getBuses().exists(entrada.getMatricula())){
////							throw new Exc_Buses("Ya existe un Bus con la misma Matricula");
//								ven.mostrarError("Ya existe un Bus con la misma Matricula", 0);
//							}else{
//								f.registroNuevoBus(entrada);
//							}
//						} catch (RemoteException | Exc_Bus | Exc_Buses e) {
//							// TODO Auto-generated catch block
//							ven.mostrarError(e.toString(), 0);
//						}
						try {
							f.registroNuevoBus(entrada);
						} catch (RemoteException | Exc_Bus | Exc_Buses e) {
							ven.mostrarError(e.toString(), 0);
						}
					}
				}
			}
		}
		
	}
	
}
