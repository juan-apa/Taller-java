package logica.fachada;

import java.util.ArrayList;
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
	/*Requerimiento 1*/
	public void registroNuevoBus(VOBus entrada) throws Exc_Bus, Exc_Buses {
		/*Es alfanumerico*/
		if(entrada.getMatricula().matches("[a-z0-9]+")){ /*Uso una expresion regular para verificar si la matricula
		 												  *solo tiene minusculas y numeros.*/
			
			if(! this.buses.existeBus(entrada.getMatricula())){/*Si no hay un bus ingresado en el sistema con la matricula ingresada*/
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
	/*Requerimiento 2*/
	public Iterator<VOBusExc> listadoGeneralBuses() throws Exc_Buses{
		/**/
		List<VOBusExc> ret = new ArrayList();
		if(this.buses.empty()){
			throw new Exc_Buses("No hay buses registrados en el sistema.");
		}
		else{
			Iterator<Object> iteradorBus = this.buses.iterator();
			
			while(iteradorBus.hasNext()){
				Bus aux = (Bus) iteradorBus.next();
				VOBusExc vobusexcAux = new VOBusExc(aux.getMatricula(), aux.getMarca(), aux.getCapPasajeros(), aux.cantidadExcursionesAsignadas());
				ret.add(vobusexcAux);
			}
		}
		return ret.iterator();
	}
}
