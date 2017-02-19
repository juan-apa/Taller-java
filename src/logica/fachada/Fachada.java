package logica.fachada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import com.sun.xml.internal.ws.util.StringUtils;

import logica.Excepciones.colecciones.*;
import logica.Excepciones.objetos.*;
import logica.objetos.*;
import logica.colecciones.*;
import logica.ValueObjects.*;

public class Fachada {
	private Excursiones excursiones;
	private Buses buses;
	
	public Fachada(){
		excursiones = new Excursiones();
		buses = new Buses();
	}
	
	public Buses getBuses(){
		return this.buses;
	}
	
	public Excursiones getExcursiones(){
		return this.excursiones;
	}
	
	
	/**Requerimiento 1
	 * @param entrada: VOBus.
	 * @return vacio.
	 * @exception Exc_Bus una excepcion que se genera en el caso de que la cantidad de pasajeros no es valida o la matricula no tiene un formato alfanumerico.
	 * @exception Exc_Buses una excepcion que se genera en el caso de que ya haya un bus ingresado en buses: Buses con la misma matricula que entrada: VOBus.
	 * */
	public void registroNuevoBus(VOBus entrada) throws Exc_Bus, Exc_Buses {
		/*Es alfanumerico*/
		if(entrada.getMatricula().matches("[a-z0-9]+")){ /*Uso una expresion regular para verificar si la matricula
		 												  *solo tiene minusculas y numeros.*/
			
			if(! this.buses.exists(entrada.getMatricula())){/*Si no hay un bus ingresado en el sistema con la matricula ingresada*/
				if(entrada.getCapPasajeros() > 0){/*Si la cantidad de pasajeros es mayor a 0*/
					Bus aux = new Bus(entrada.getMatricula(), entrada.getMarca(), entrada.getCapPasajeros());
					this.buses.insert(aux);
					System.out.println("Ingreso del bus exitoso.");
				}
				else{
					throw new Exc_Bus("La cantidad de asientos '" + entrada.getCapPasajeros() + "' no es valida. La cantidad de pasajeros tiene que ser mayor a '0'.");
				}
			}
			else{
				throw new Exc_Buses("El bus con la matricula " + entrada.getMatricula() + " ya se encuentra registrado en el sistema.");
			}
		}
		else{
			throw new Exc_Bus("La matricula " + entrada.getMatricula() + " no tiene un formato alfanumerico.");
		}
	}
	/**Requerimiento 2
	 * @param vacio.
	 * @return Iterador<VOBusExc>.
	 * @exception Exc_Buses una excepcion que se genera en el caso de que no hay ningun bus ingresado en el sistema.
	 * */
	public Iterator<VOBusExc> listadoGeneralBuses() throws Exc_Buses{
		/*Creo una nueva lista de VOBusExc que es la que voy a devolver.*/
		List<VOBusExc> ret = new ArrayList<VOBusExc>();
		/*Si buses se encuentra vacio, tiro un error.*/
		if(this.buses.empty()){
			throw new Exc_Buses("No hay buses registrados en el sistema.");
		}
		else{
			/*Obtengo un iterador con el contenido de buse.s*/
			Iterator<Bus> iteradorBus = this.buses.iterator();
			/*convierto los datos de tipo Bus a VOBusExc y los aniado a la lista ret.*/
			while(iteradorBus.hasNext()){
				Bus aux = (Bus) iteradorBus.next();
				VOBusExc vobusexcAux = new VOBusExc(aux.getMatricula(), aux.getMarca(), aux.getCapPasajeros(), aux.cantidadExcursionesAsignadas());
				ret.add(vobusexcAux);
			}
		}
		/*convierto la lista ret a un iterador y la devuelvo.*/
		return ret.iterator();
	}
	
	/**Requerimiento 3
	 * @param matricula: String
	 * @return Iterator<VOExcursionListado>
	 * @exception Exc_Bus una Excepcion que se genera si la matricula pasada por param no tiene un formato alfanumerico
	 * @exception Exc_Buses una excepcion que se genera si no hay un bus ingresado en buses con la matricula pasada por param
	 * @exception Exc_Excursiones una excepcion que se genera si no hay ninguna excursion asignada al bus con la matricula pasada por param
	 * */
	public Iterator<VOExcursionListado> listadoExcursionesDeBus(String matricula) throws Exc_Bus, Exc_Buses, Exc_Excursiones{
		List<VOExcursionListado> ret = new ArrayList<VOExcursionListado>(); /*Creo una lista donde oy a guardar los VO que voy a devolver*/
		
		if(matricula.matches("[a-z09]+")){ /*Si la matricula tiene un formato alfanumerico.*/
			if(buses.exists(matricula)){ /*Si existe un bus con la matricula en el sistema.*/
				Bus aux;
				aux = (Bus) buses.find(matricula); /*Obtengo el bus con la matricula ingresada del diccionario buses*/
				if(! aux.getExcuBus().empty()){ /*Si las excursiones del bus obtenido no esta vacio.*/
					Excursiones excs;
					Iterator<Excursion> iteExc;
					excs = aux.getExcuBus();
					iteExc = excs.iterator(); /*Obtengo las excursiones del bus y las convierto a un iterador.*/
					while(iteExc.hasNext()){/*Recorro las excursiones del iterador y las convierto a VO y las inserto en la lista que voy a devolver*/
						Excursion excursionAux = iteExc.next();
						VOExcursionListado voexclist = new VOExcursionListado(excursionAux.getCodigo(), excursionAux.getDestino(), excursionAux.getHpartida(), excursionAux.getHllegada(), excursionAux.getPrecioBase(), aux.asientosDisponiblesParaExcursion(excursionAux.getCodigo()));
						ret.add(voexclist);
					}
				}
				else{
					throw new Exc_Excursiones("No hay ninguna excursion registrada para el bus con la matricula "+ matricula+".");
				}
			}
			else{
				throw new Exc_Buses("El bus con la matricula " + matricula + " no se encuentra registrado en el sistema.");
			}
		}
		else{
			throw new Exc_Bus("La matricula " + matricula + " no tiene un formato alfanumerico.");
		}
		return ret.iterator(); /*Convierto la lista a un iterador y la devuelvo.*/
	}
	
	/**Requerimiento 4
	 * @param entrada: VOExcursion
	 * @return vacio
	 * @exception Exc_Excursiones una excepcion que se genera si ya hay una excursion ingresda con el codigo de entrada
	 * @exception Exc_Buses una excepcion que se genera si no hay buses registrados en el sistema o si no hay ningun bus con un espacio lo suficientemente grande para que entre la excursion pasada por param.
	 * @exception Exc_Excursion una excepcion que se genera si el precio base de entrada es menor a 0 o si la hora de partida es despues o igual que la hora de llegada.
	 * */
	public void registroNuevaExcursion(VOExcursion entrada) throws Exc_Excursiones, Exc_Buses, Exc_Excursion{
		if(! excursiones.exists(entrada.getCodigo())){
			if(entrada.gethPartida().before(entrada.gethLlegada()) || entrada.gethPartida().equals(entrada.gethLlegada())){
				if(entrada.getPrecioBase() >= 0){
					if(!buses.empty()){
						Excursion insertar = new Excursion(entrada.getCodigo(), entrada.getDestino(), entrada.gethPartida(), entrada.gethLlegada(), entrada.getPrecioBase());
						buses.asignarExcursionAUnBus(insertar); 
						System.out.println("Asignando Excursion al diccionario global...");
						excursiones.insert(insertar);
						System.out.println("Asignacion al dicc global de Excursiones exitoso.");

						
					}
					else{
						throw new Exc_Buses("No hay buses registrados en el sistema.");
					}
				}
				else{
					throw new Exc_Excursion("El precio base " + entrada.getPrecioBase() + " no es mayor a 0.");
				}
			}
			else{
				throw new Exc_Excursion("La hora de partida ("+entrada.gethPartida().getTime()+") tiene que ser menor que la hora de llegada("+entrada.gethLlegada().getTime()+").");
			}
		}
		else{
			throw new Exc_Excursiones("Ya hay una excursion con el codigo " + entrada.getCodigo() + " registrada en el sistema.");
		}
	}
	
}
