package logica.fachada;

import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursion;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.objetos.Bus;

public class Controladora {
	//REQUERIMIENTO 1
	public void registroNuevoBus(Fachada f, VOBus entrada) throws Exc_Bus, Exc_Buses, RemoteException{
		if(entrada.getMatricula() == null){
			throw new Exc_Bus("El Bus a registrar no cuenta con una Matricula");
		}else{
			if(entrada.getMatricula().matches("[a-z0-9]+")){
				throw new Exc_Bus("La matricula ingresada no es Alfanumerica");
			}else{
				if(entrada.getMarca() == null){
					throw new Exc_Bus("El Bus a registrar no cuenta con una Marca");
				}else{
					if(entrada.getCapPasajeros() <= 0){
						throw new Exc_Bus("La cantidad de asientos '" + entrada.getCapPasajeros() + "' no es valida. La cantidad de pasajeros tiene que ser mayor a '0'.");
					}else{
						if(f.getBuses().exists(entrada.getMatricula())){
							throw new Exc_Buses("Ya existe un Bus con la misma Matricula");
						}else{
							f.registroNuevoBus(entrada);
						}
					}
				}
			}
		}
	}
	
	//REQUERIMIENTO 2
	public Iterador<VOBusExc> listadoGeneralBuses(Fachada f) throws Exc_Buses, RemoteException{
		if(f.getBuses().empty()){
			throw new Exc_Buses("No hay Buses registrados en el sistema");
		}else{
		return f.listadoGeneralBuses();
		}
	}
	
	//REQUERIMIENTO 3
	public Iterador<VOExcursionListado> listadoExcursionesDeBus(Fachada f, String matricula) throws Exc_Bus, Exc_Buses, Exc_Excursiones, RemoteException{
		if(matricula.matches("[a-z0-9]+")){
			throw new Exc_Bus("La matricula ingresada no es Alfanumerica");
		}else{
			if(!f.getBuses().exists(matricula)){
				throw new Exc_Buses("No existe un Bus con la esa Matricula");
			}else{
				Bus aux = new Bus();
				aux = (Bus) f.getBuses().find(matricula);
				if(aux.getExcuBus().empty()){
					throw new Exc_Bus("El Bus no cuenta con excursiones cargadas");
				}else{
					return f.listadoExcursionesDeBus(matricula);
				}
			}
		}
	}
	
	//REQUERIMIENTO 4
	public void registroNuevaExcursion(Fachada f, VOExcursion entrada) throws Exc_Excursiones, Exc_Buses, Exc_Excursion, RemoteException{
		if(f.getBuses().empty()){
			throw new Exc_Buses("No hay buses registrados en el sistema");
		}else{
			if(entrada.getPrecioBase() <= 0){
				throw new Exc_Excursion("El precio base debe ser mayor a 0");
			}else{
				if((entrada.gethLlegada().before(entrada.gethPartida())) || (entrada.gethPartida().equals(entrada.gethLlegada()))){
					throw new Exc_Excursion("La hora de partida debe ser menor a la hora de llegada");
				}else{
					if(f.getExcursiones().exists(entrada.getCodigo())){
						throw new Exc_Excursiones("Existe una excursion con el mismo codigo");
					}else{
						f.registroNuevaExcursion(entrada);
					}
				}
			}
		}
	}
	
	//REQUERIMIENTO 5
	public void reasignacionExcursion(Fachada f, String codigo) throws Exc_Buses, Exc_Excursiones, RemoteException{
		if(!f.getExcursiones().exists(codigo)){
			throw new Exc_Excursiones("No existe una Excursion con ese codigo");
		}else{
			f.reasignacionExcursion(codigo);
		}
	}
	
	//REQUERIMIENTO 6
	//No hay verificaciones por que si el archivo existe lo sobreescribe y si no existe lo crea
	
	//REQUERIMIENTO 7
	//REQUERIMIENTO 8
	//REQUERIMIENTO 9
	//REQUERIMIENTO 10
	//REQUERIMIENTO 11
	
}