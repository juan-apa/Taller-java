package logica.fachada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logica.Excepciones.colecciones.*;
import logica.Excepciones.objetos.*;
import logica.objetos.*;
import logica.colecciones.*;
import logica.ValueObjects.*;
import logica.Persistencia.*;

public class Fachada implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4670089808545963151L;
	private Excursiones excursiones;
	private Buses buses;
	private Datos datos;
	private static Fachada instancia;
	

	
	public static Fachada getInstancia(){
		if(instancia == null){
			instancia = new Fachada();
		}
		return instancia;
	}
	
	private Fachada(){
		excursiones = new Excursiones();
		buses = new Buses();
		datos = new Datos();
	}
	
	public Buses getBuses(){
		return this.buses;
	}
	
	public Excursiones getExcursiones(){
		return this.excursiones;
	}
	
	public Datos getDatos(){
		return this.datos;
	}
	
	public void actualizarDatos(){
		this.datos.setBuses(buses);
		this.datos.setExcursiones(excursiones);
	}
	
	
	
	public void setExcursiones(Excursiones excursiones) {
		this.excursiones = excursiones;
	}

	public void setBuses(Buses buses) {
		this.buses = buses;
	}

	public void setDatos(Datos datos) {
		this.datos = datos;
	}

	public static void setInstancia(Fachada instancia) {
		Fachada.instancia = instancia;
	}

	/**Requerimiento 1
	 * @param VOBus entrada
	 * @return void
	 * @throws Exc_Bus
	 * @throws Exc_Buses
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
	 * @param void
	 * @return Iterador<VOBusExc>
	 * @throws Exc_Buses
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
	 * @param String matricula
	 * @return Iterator<VOExcursionListado>
	 * @throws Exc_Bus
	 * @throws Exc_Buses
	 * @throws Exc_Excursiones
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
	 * @param VOExcursion entrada
	 * @return void
	 * @throws Exc_Excursiones
	 * @throws Exc_Buses
	 * @throws Exc_Excursion
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
	
	/**Requerimiento 5
	 * @param String codigo
	 * @return void
	 * @throws Exc_Buses
	 * @exception Exc_Buses uian excepcion que se genera si no hay un bus con un espacio disponible para mover
	 * @throws Exc_Excursiones */
	public void reasignacionExcursion(String codigo) throws Exc_Buses, Exc_Excursiones{
		/*TODO esta funcion puede tener errores (algo con punteros debe ser).*/
		
		
		/*Verifico que la matricula tenga formato alfanumerico*/
		if(this.excursiones.exists(codigo)){
			/*Obtengo la excursion con el codigo ingresado*/
			Excursion aux = this.excursiones.find(codigo);
			/*Obtengo el bus que tiene la excursion con el codigo pasado por param*/
			Bus busConExcursion = this.buses.obtenerBusConExcursion(codigo); 
			/*Antes de borrar la excursion del diccionario global tengo que estar seguro que la puedo
			 * reasignar a otro.*/
			this.buses.reasignarExcursion(busConExcursion, aux);
			/*Si llego a esta parte del codigo es porque la pude reasignar a otro bus.*/
			/*Actualizo la cantidad de boletos de la excursion en el dicc global y en el dicc del bus al que le acabo de asignar la excursion*/
			aux.actualizarCantBoletos(this.buses.obtenerBusConExcursion(codigo).getCapPasajeros());
		}
		else{
			throw new Exc_Excursiones("La excursion con el codigo " + codigo + " no se encuentra ingresada en el sistema");
		}
	}
	
	//Hacer requerimiento 6
	public void respaldar() throws Exc_Persistencia{
		try {
			this.actualizarDatos();
			String nombreArch = "objeto.obj";
			Persistencia p = new Persistencia();
			p.respaldar(nombreArch, this.getDatos());
		}catch (Exception e){ 
			e.printStackTrace();
		throw new Exc_Persistencia("Hubo un error al respaldar la informacion");
		}	
	}
	public void recuperar() throws Exc_Persistencia{
		try {
			String nombreArch = "objeto.obj";
			Persistencia p = new Persistencia();
			this.setDatos(p.recuperar(nombreArch));
			this.setExcursiones(this.getDatos().get_excursiones());
			this.setBuses(this.getDatos().get_buses());
		}catch (Exception e){ 
			e.printStackTrace();
		throw new Exc_Persistencia("Hubo un error al recuperar la informacion");
		}
	}
	
	/**Requerimiento 7
	 * @param VOBoleto entrada
	 * @return void
	 * @throws Exc_Boleto
	 * @throws Exc_Boletos
	 * @throws Exc_Excursiones
	 * @exception Exc_Boleto una excepcion que se genera si alguno de los datos del pasajero es incorrecto.
	 * @exception Exc_Boletos una excepcion que se genera si todos los boletos de la excursion ya estan vendidos.
	 * @exception Exc_Excursiones uan excepcion que se genera si no hay una excursion registrada con el codigo pasado por param
	 * */
	public void ventaBoleto(VOBoleto entrada) throws Exc_Boleto, Exc_Boletos, Exc_Excursiones{
		if(excursiones.exists(entrada.getCodExcursion())){
			if((entrada.getEdadPasajero() <= 0) || (entrada.getNroCelular()/10000000 < 0) || (entrada.getDtoAdicional() < 0.0)){
				throw new Exc_Boleto("Alguno de los datos del psajero ingresados no es valido.");
			}
			else{
				Excursion excAux = this.excursiones.find(entrada.getCodExcursion());
				Boletos boletosAux = excAux.getBoletos();
				if(!boletosAux.full()){
					Boleto insertar = null;
					if(entrada.getDtoAdicional() != 0.0){
						insertar = new Especial(entrada.getLugarPrecedencia(), entrada.getEdadPasajero(), entrada.getNroCelular(), entrada.getDtoAdicional());
					}
					else{
						insertar = new Boleto(entrada.getLugarPrecedencia(), entrada.getEdadPasajero(), entrada.getNroCelular());
					}
					boletosAux.insert(insertar);
				}
				else{
					throw new Exc_Boletos("Todos los boletos para esta excursion ya estan vendidos.");
				}
			}
		}
		else{
			throw new Exc_Excursiones("La excursion con el codigo " + entrada.getCodExcursion() + " no se encuentra ingresada en el sistema.");
		}
	}
	
	/**Requerimiento 8
	 * @param String codigo
	 * @return double
	 * @throws Exc_Boletos 
	 * @throws Exc_Excursiones
	 * @exception */
	public double recaudadoEnExcursion(String codigo) throws Exc_Boletos, Exc_Excursiones{
		double ret = 0.0;
		if(this.excursiones.exists(codigo)){
			Excursion excAux = this.excursiones.find(codigo);
			Boletos bolsAux = excAux.getBoletos();
			if(!bolsAux.empty()){
				ret = bolsAux.recaudado(excAux.getPrecioBase());
			}
			else{
				throw new Exc_Boletos("No hay boletos vendidos para la excursion con el codigo "+codigo);
			}
		}
		else{
			throw new Exc_Excursiones("La excursion con el codigo " + codigo + " no se encuentra ingresada en el sistema.");
		}
		return ret;
	}
	
	/**Requerimiento 9
	 * @throws Exc_Boletos 
	 * @throws Exc_Excursiones 
	 * @param String codigo
	 * @param String tipo
	 * @return Iterator<VOBoleto2>*/
	/*TODO testear requerimiento.*/
	public Iterator<VOBoleto2> listadoBoletosExcursion(String codigo, String tipo) throws Exc_Boletos, Exc_Excursiones{
		List<VOBoleto2> ret = new ArrayList<VOBoleto2>();
		if(excursiones.exists(codigo)){
			Excursion excAux = excursiones.find(codigo);
			if(! excAux.getBoletos().empty()){
				Iterator<Boleto> ite = excAux.getBoletos().iterator();
				int nroBoleto = 1;
				while(ite.hasNext()){
					Boleto bolAux = ite.next();
					if(bolAux.getTipo().equals(tipo)){
						ret.add(new VOBoleto2(nroBoleto, bolAux.getEdadPasajero(), bolAux.getLugarPrecedencia(), bolAux.getNroCelular()));
					}
					nroBoleto++;
				}
			}
			else{
				throw new Exc_Boletos("Advertencia: no hay boletos vendidos para esta excursion.");
			}
		}
		else{
			throw new Exc_Excursiones("La excursion con el codigo" + codigo + " no se encuentra ingresada en el sistema.");
		}
		return ret.iterator();
	}
	
	/**Requerimiento 10
	 * @throws Exc_Excursiones */
	public Iterator<VOExcursionListado> listadoExcursionesDestino(String destino) throws Exc_Excursiones{
		List<VOExcursionListado> ret = new ArrayList<VOExcursionListado>();
		if(! excursiones.empty()){
			Iterator<Excursion> ite = this.excursiones.iterator();
			while(ite.hasNext()){
				Excursion excAux = ite.next();
				if(excAux.getDestino().equals(destino)){
					int asientosDisp = 0;
					Bus busAux = this.buses.obtenerBusConExcursion(excAux.getCodigo());
					asientosDisp = busAux.asientosDisponiblesParaExcursion(excAux.getCodigo());
					ret.add(new VOExcursionListado(excAux.getCodigo(), excAux.getDestino(), excAux.getHpartida(), excAux.getHllegada(), excAux.getPrecioBase(), asientosDisp));
				}
			}
		}
		else{
			throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
		}
		return ret.iterator();
	}
	
	/**Requerimiento 11
	 * @throws Exc_Excursiones 
	 * */
	public Iterator<VOExcursionListado> listadoExcursionesPrecio(double precioMin, double precioMax) throws Exc_Excursiones{
		List<VOExcursionListado> ret = new ArrayList<VOExcursionListado>();
		if(!excursiones.empty()){
			Iterator<Excursion> ite = this.excursiones.iterator();
			while(ite.hasNext()){
				Excursion aux = ite.next();
				if(((Excursion) aux).dentroRango(precioMin, precioMax)){
					int asientosDisp = 0;
					Bus busAux = this.buses.obtenerBusConExcursion(aux.getCodigo());
					asientosDisp = busAux.asientosDisponiblesParaExcursion(aux.getCodigo());
					ret.add(new VOExcursionListado(aux.getCodigo(), aux.getDestino(), aux.getHpartida(), aux.getHllegada(), aux.getPrecioBase(), asientosDisp));
				}
			}
		}
		else{
			throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
		}
		return ret.iterator();
	}
}
