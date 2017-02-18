package logica.fachada;

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
}
