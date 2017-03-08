package logica.fachada;

import java.rmi.Remote; 
import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Boleto;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.ValueObjects.VOBoleto;
import logica.ValueObjects.VOBoleto2;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursion;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;

public  interface IFachada extends Remote{
	
	/**Requerimiento 1
	 * Inserta un nuevo bus al sistema.
	 * @param VOBus entrada
	 * @return void
	 * @throws Exc_Bus
	 * @throws Exc_Buses
	 * @throws RemoteException
	 * @exception Exc_Bus una excepcion que se genera en el caso de que la cantidad de pasajeros no es valida o la matricula no tiene un formato alfanumerico.
	 * @exception Exc_Buses una excepcion que se genera en el caso de que ya haya un bus ingresado en buses: Buses con la misma matricula que entrada: VOBus.
	 * */
	public void registroNuevoBus(VOBus entrada) throws Exc_Bus, Exc_Buses, RemoteException;

	/**Requerimiento 2
	 * Devuelve un Iterador con todos los buses del sistema ordenados por matricula en orden ascendiente.
	 * @param void
	 * @return Iterador<VOBusExc>
	 * @throws Exc_Buses
	 * @throws RemoteException
	 * @exception Exc_Buses una excepcion que se genera en el caso de que no hay ningun bus ingresado en el sistema.
	 * */
	public Iterador<VOBusExc> listadoGeneralBuses() throws Exc_Buses, RemoteException;

	/**Requerimiento 3
	 * Devuelve un Iterador con las Excursiones asociadas al bus con la matricula pasada por parametro.
	 * @param String matricula
	 * @return Iterador<VOExcursionListado>
	 * @throws Exc_Bus
	 * @throws Exc_Buses
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Bus una Excepcion que se genera si la matricula pasada por param no tiene un formato alfanumerico
	 * @exception Exc_Buses una excepcion que se genera si no hay un bus ingresado en buses con la matricula pasada por param
	 * @exception Exc_Excursiones una excepcion que se genera si no hay ninguna excursion asignada al bus con la matricula pasada por param
	 * */
	public Iterador<VOExcursionListado> listadoExcursionesDeBus(String matricula) throws Exc_Bus, Exc_Buses, Exc_Excursiones, RemoteException;

	/**Requerimiento 4
	 * Inserta una nueva Excursion con los datos de entrada pasada por parametro.
	 * Asigna automaticamente la excursion al prime bus con el horario disponible que encuentre.
	 * Si no hay ningun bus con un horario disponible, tira una excepcion (ver excepciones).
	 * @param VOExcursion entrada
	 * @return void
	 * @throws Exc_Excursiones
	 * @throws Exc_Buses
	 * @throws Exc_Excursion
	 * @throws RemoteException
	 * @exception Exc_Excursiones una excepcion que se genera si ya hay una excursion ingresda con el codigo de entrada
	 * @exception Exc_Buses una excepcion que se genera si no hay buses registrados en el sistema o si no hay ningun bus con un espacio lo suficientemente grande para que entre la excursion pasada por param.
	 * @exception Exc_Excursion una excepcion que se genera si el precio base de entrada es menor a 0 o si la hora de partida es despues o igual que la hora de llegada.
	 * */
	public void registroNuevaExcursion(VOExcursion entrada) throws Exc_Excursiones, Exc_Buses, Exc_Excursion, RemoteException;

	/**Requerimiento 5
	 * Reasigna la excursion con el codigo pasado por parametro al priemr bus que que encuentre con un horario disponible
	 * y una cantidad de asiento mayor o igual a la del bus que tenia asignada la excursion anteriormente.
	 * @param String codigo
	 * @return void
	 * @throws Exc_Buses
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Buses una excepcion que se genera si no hay un bus con un espacio disponible para asignarle la excursion.
	 * @throws Exc_Excursiones */
	public void reasignacionExcursion(String codigo) throws Exc_Buses, Exc_Excursiones, RemoteException;

	/**Requerimiento 6.1
	 * Respalda los datos a disco en un archivo llamado objeto.obj
	 * @param void
	 * @return void
	 * @throws Exc_Persistencia
	 * @throws RemoteException
	 * @exception Exc_Persistencia una exceppcion que se produce si se produce un error al respaldar la informacion.
	 * */
	public void respaldar() throws Exc_Persistencia, RemoteException;
	
	/**Requerimiento 6.2
	 * Levanta los datos de disco desde un archivo llamado objeto.obj
	 * @param void
	 * @return void
	 * @throws Exc_Persistencia
	 * @throws RemoteException
	 * @exception Exc_Persistencia una exceppcion que se produce si se produce un error al levantar la informacion.
	 * */
	public void recuperar() throws Exc_Persistencia, RemoteException;
	
	/**Requerimiento 7
	 * Registra un nuevo boleto con los daots pasados por parametro, a la excursion con el codigo que se encuentra en entrada.
	 * @param VOBoleto entrada
	 * @return void
	 * @throws Exc_Boleto
	 * @throws Exc_Boletos
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Boleto una excepcion que se genera si alguno de los datos del pasajero es incorrecto.
	 * @exception Exc_Boletos una excepcion que se genera si todos los boletos de la excursion ya estan vendidos.
	 * @exception Exc_Excursiones uan excepcion que se genera si no hay una excursion registrada con el codigo pasado por param
	 * */
	public void ventaBoleto(VOBoleto entrada) throws Exc_Boleto, Exc_Boletos, Exc_Excursiones, RemoteException;
	
	/**Requerimiento 8
	 * Calcula lo recaudado por la venta de boleto de la excursion con el codigo pasado por parametro.
	 * Se calcula teniendo en cuenta si los boletos vendidos son Especiales o Comunes.
	 * @param String codigo
	 * @return double
	 * @throws Exc_Boletos 
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Boletos una excepcion que se genera si no hay boletos vendidos para la excursion con el codigo pasado por parametro
	 * @exception Exc_Excursiones una excepcion que se genera si la excursion con el codigo pasado por parametro no se encuentra registrada
	 * */
	public double recaudadoEnExcursion(String codigo) throws Exc_Boletos, Exc_Excursiones, RemoteException;
	
	/**Requerimiento 9
	 * Devuelve un iterador con los boletos vendidos para la excursion con el codigo pasado por parametro y de tipo pasado por parametro.
	 * @param String codigo
	 * @param String tipo
	 * @return Iterador<VOBoleto2>
	 * @throws Exc_Boletos 
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Boletos una excepcion que se genera si no hay boletos vendidos para la excursion con el codigo pasado por parametro.
	 * @exception Exc_Excursiones una excepcion que se genera si la excursion con el codigo pasado por parametro no se encuentra registrada.*/
	public Iterador<VOBoleto2> listadoBoletosExcursion(String codigo, String tipo) throws Exc_Boletos, Exc_Excursiones, RemoteException;
	
	/**Requerimiento 10
	 * Devuelve un Iterador con las excursiones registradas con el destino pasado por parametro.
	 * @param String destino 
	 * @return Iterador<VOExcursionListado>
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Excursiones una excepcion que se genera si no hay excursiones registradas en el sistema.
	 * */
	public Iterador<VOExcursionListado> listadoExcursionesDestino(String destino) throws Exc_Excursiones, RemoteException;
	
	/**Requerimniento 11
	 * Devuelve un Iterador con las Excursiones registradas cuyo precio se encuentre entre los precios pasados por parametro.
	 * @param double precioMin
	 * @param double precioMax
	 * @return Iterador<VOExcursionlistado>
	 * @throws Exc_Excursiones
	 * @throws RemoteException
	 * @exception Exc_Excursiones uan excepcion que se genera si no hay excursiones registradas en el sistema.*/
	public Iterador<VOExcursionListado> listadoExcursionesPrecio(double precioMin, double precioMax) throws Exc_Excursiones, RemoteException;

}
