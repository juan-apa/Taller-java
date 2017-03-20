package logica.fachada;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

import logica.Excepciones.colecciones.*;
import logica.Excepciones.objetos.*;
import logica.objetos.*;
import logica.colecciones.*;
import logica.concurrencia.Monitor;
import logica.ValueObjects.*;
import persistencia.*;
 

public class Fachada extends UnicastRemoteObject implements IFachada{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Datos datos;
	private static Fachada instancia;
	private Monitor monitor;
	

	
	public static Fachada getInstancia(){
		if(instancia == null){
			try {
				instancia = new Fachada();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	private Fachada() throws RemoteException{
		datos = new Datos();
		monitor = Monitor.getInstancia();
	}
	
	public Buses getBuses() throws RemoteException {
		return this.datos.buses();
	}
	
	public Excursiones getExcursiones() throws RemoteException{
		return this.datos.excursiones();
	}
	
	public Datos getDatos() throws RemoteException{
		return this.datos;
	}
	
	public void setExcursiones(Excursiones excursiones) throws RemoteException{
		this.datos.setExcursiones(excursiones);
	}

	public void setBuses(Buses buses) throws RemoteException{
		this.datos.setBuses(buses);
	}

	public void setDatos(Datos datos) throws RemoteException{
		this.datos = datos;
	}

	
	public void registroNuevoBus(VOBus entrada) throws Exc_Bus, Exc_Buses, RemoteException{
		/*Es alfanumerico*/
		if(entrada.getMatricula().matches("[A-Z0-9]+")){
			monitor.startRead();
			if(! this.datos.buses().exists(entrada.getMatricula())){/*Si no hay un bus ingresado en el sistema con la matricula ingresada*/
				monitor.endRead();
				if(entrada.getCapPasajeros() > 0){/*Si la cantidad de pasajeros es mayor a 0*/
					Bus aux = new Bus(entrada.getMatricula(), entrada.getMarca(), entrada.getCapPasajeros());
					monitor.startWrite();
					this.datos.buses().insert(aux);
					monitor.endWrite();
				}
				else{
					throw new Exc_Bus("La cantidad de asientos '" + entrada.getCapPasajeros() + "' no es valida. La cantidad de pasajeros tiene que ser mayor a '0'.");
				}
			}
			else{
				monitor.endRead();
				throw new Exc_Buses("El bus con la matricula " + entrada.getMatricula() + " ya se encuentra registrado en el sistema.");
			}
		}
		else{
			throw new Exc_Bus("La matricula " + entrada.getMatricula() + " no tiene un formato alfanumerico.");
		}
	}

	public Iterador<VOBusExc> listadoGeneralBuses() throws Exc_Buses, RemoteException{
		/*Creo una nueva lista de VOBusExc que es la que voy a devolver.*/
		Iterador<VOBusExc> ret = new Iterador<VOBusExc>();
		/*Si buses se encuentra vacio, tiro un error.*/
		monitor.startRead();
		if(this.datos.buses().empty()){
			monitor.endRead();
			throw new Exc_Buses("No hay buses registrados en el sistema.");
		}
		else{
			monitor.endRead();
			/*Obtengo un iterador con el contenido de buse.s*/
			monitor.startRead();
			Iterator<Bus> iteradorBus = this.datos.buses().iterator();
			/*convierto los datos de tipo Bus a VOBusExc y los aniado a la lista ret.*/
			while(iteradorBus.hasNext()){
				Bus aux = (Bus) iteradorBus.next();
				VOBusExc vobusexcAux = new VOBusExc(aux.getMatricula(), aux.getMarca(), aux.getCapPasajeros(), aux.cantidadExcursionesAsignadas());
				ret.add(vobusexcAux);
			}
			monitor.endRead();
		}
		/*convierto la lista ret a un iterador y la devuelvo.*/
		return ret;
	}
	
	
	public Iterador<VOExcursionListado> listadoExcursionesDeBus(String matricula) throws Exc_Bus, Exc_Buses, Exc_Excursiones, RemoteException{
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>(); /*Creo un Iterador donde voy a guardar los VO que voy a devolver*/
		
		if(matricula.matches("[A-Z0-9]+")){ /*Si la matricula tiene un formato alfanumerico.*/
			monitor.startRead();
			if(this.datos.buses().exists(matricula)){ /*Si existe un bus con la matricula en el sistema.*/
				monitor.endRead();
				Bus aux;
				monitor.startRead();
				aux = (Bus) this.datos.buses().find(matricula); /*Obtengo el bus con la matricula ingresada del diccionario buses*/
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
					monitor.endRead();
				}
				else{
					monitor.endRead();
					throw new Exc_Excursiones("No hay ninguna excursion registrada para el bus con la matricula "+ matricula+".");
				}
			}
			else{
				monitor.endRead();
				throw new Exc_Buses("El bus con la matricula " + matricula + " no se encuentra registrado en el sistema.");
			}
		}
		else{
			throw new Exc_Bus("La matricula " + matricula + " no tiene un formato alfanumerico.");
		}
		return ret; /*Convierto la lista a un iterador y la devuelvo.*/
	}
	
	
	public void registroNuevaExcursion(VOExcursion entrada) throws Exc_Excursiones, Exc_Buses, Exc_Excursion, RemoteException{
		monitor.startRead();
		if(! this.datos.excursiones().exists(entrada.getCodigo())){
			monitor.endRead();
			if(entrada.gethPartida().before(entrada.gethLlegada()) || entrada.gethPartida().equals(entrada.gethLlegada())){
				if(entrada.getPrecioBase() >= 0){
					monitor.startRead();
					if(!this.datos.buses().empty()){
						monitor.endRead();
						Excursion insertar = new Excursion(entrada.getCodigo(), entrada.getDestino(), entrada.gethPartida(), entrada.gethLlegada(), entrada.getPrecioBase(), new Boletos(0));
						monitor.startWrite();
						try{
							this.datos.buses().asignarExcursionAUnBus(insertar);
							this.datos.excursiones().insert(insertar);
						}
						catch(Exc_Buses e){
							throw e;
						}
						finally {
							monitor.endWrite();
						}
					}
					else{
						monitor.endRead();
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
			monitor.endRead();
			throw new Exc_Excursiones("Ya hay una excursion con el codigo " + entrada.getCodigo() + " registrada en el sistema.");
		}
	}
	
	
	public void reasignacionExcursion(String codigo) throws Exc_Buses, Exc_Excursiones, RemoteException{		
		/*Verifico que la matricula tenga formato alfanumerico*/
		monitor.startRead();
		if(this.datos.excursiones().exists(codigo)){
			monitor.endRead();
			/*Obtengo la excursion con el codigo ingresado*/
			monitor.startRead();
			Excursion aux = this.datos.excursiones().find(codigo);
			monitor.endRead();
			/*Obtengo el bus que tiene la excursion con el codigo pasado por param*/
			monitor.startRead();
			Bus busConExcursion = this.datos.buses().obtenerBusConExcursion(codigo); 
			monitor.endRead();
			/*Antes de borrar la excursion del diccionario global tengo que estar seguro que la puedo
			 * reasignar a otro.*/
			monitor.startWrite();
			try{
				this.datos.buses().reasignarExcursion(busConExcursion, aux);
			}
			catch(Exc_Buses e) {
				throw e;
			}
			finally{
				monitor.endWrite();
			}
			
			/*Si llego a esta parte del codigo es porque la pude reasignar a otro bus.*/
			/*Actualizo la cantidad de boletos de la excursion en el dicc global y en el dicc del bus al que le acabo de asignar la excursion*/
			monitor.startWrite();
			aux.actualizarCantBoletos(this.datos.buses().obtenerBusConExcursion(codigo).getCapPasajeros(), this.datos.excursiones().find(codigo).getCantBoletosVendidos());
			monitor.endWrite();
		}
		else{
			monitor.endRead();
			throw new Exc_Excursiones("La excursion con el codigo " + codigo + " no se encuentra ingresada en el sistema");
		}
	}
	
	public void respaldar() throws Exc_Persistencia, RemoteException{
		monitor.startRead();
		try {
			Persistencia pers = new Persistencia();
			pers.respaldar(this.datos);
		}catch (Exception e){ 
			e.printStackTrace();
			throw new Exc_Persistencia("Hubo un error al respaldar la informacion");
		}
		finally{
			monitor.endRead();
		}
	}
	public void recuperar() throws Exc_Persistencia, RemoteException{
		monitor.startWrite();
		try {
			String nombreArch = "objeto.obj";
			Persistencia p = new Persistencia();
			
			this.setDatos(p.recuperar(nombreArch));
//			this.setExcursiones(this.datos.excursiones());
//			this.setBuses(this.datos.buses());
		}catch (Exception e){ 
			e.printStackTrace();
			throw new Exc_Persistencia("Hubo un error al recuperar la informacion");
		}
		finally{
			monitor.endWrite();
		}
	}
	

	public void ventaBoleto(VOBoleto entrada) throws Exc_Boleto, Exc_Boletos, Exc_Excursiones, RemoteException{
		monitor.startRead();
		if(this.datos.excursiones().exists(entrada.getCodExcursion())){
			monitor.endRead();
			if((entrada.getEdadPasajero() <= 0) || (entrada.getNroCelular()/10000000 < 0) || (entrada.getDtoAdicional() < 0.0)){
				throw new Exc_Boleto("Alguno de los datos del psajero ingresados no es valido.");
			}
			else{
				monitor.startRead();
				Excursion excAux = this.datos.excursiones().find(entrada.getCodExcursion());
				Boletos boletosAux = excAux.getBoletos();
				if(!boletosAux.full()){
					monitor.endRead();
					Boleto insertar = null;
					if(entrada.getDtoAdicional() != 0.0){
						insertar = new Especial(entrada.getLugarPrecedencia(), entrada.getEdadPasajero(), entrada.getNroCelular(), entrada.getDtoAdicional());
					}
					else{
						insertar = new Boleto(entrada.getLugarPrecedencia(), entrada.getEdadPasajero(), entrada.getNroCelular());
					}
					monitor.startWrite();
					boletosAux.insert(insertar);
					monitor.endWrite();
				}
				else{
					monitor.endRead();
					throw new Exc_Boletos("Todos los boletos para esta excursion ya estan vendidos.");
				}
			}
		}
		else{
			monitor.endRead();
			throw new Exc_Excursiones("La excursion con el codigo " + entrada.getCodExcursion() + " no se encuentra ingresada en el sistema.");
		}
	}
	
	
	public double recaudadoEnExcursion(String codigo) throws Exc_Boletos, Exc_Excursiones, RemoteException{
		double ret = 0.0;
		monitor.startRead();
		if(this.datos.excursiones().exists(codigo)){
			Excursion excAux = this.datos.excursiones().find(codigo);
			Boletos bolsAux = excAux.getBoletos();
			if(!bolsAux.empty()){
				ret = bolsAux.recaudado(excAux.getPrecioBase());
			}
			else{
				throw new Exc_Boletos("No hay boletos vendidos para la excursion con el codigo "+codigo);
			}
			monitor.endWrite();
		}
		else{
			monitor.endRead();
			throw new Exc_Excursiones("La excursion con el codigo " + codigo + " no se encuentra ingresada en el sistema.");
		}
		return ret;
	}
	
	
	public Iterador<VOBoleto2> listadoBoletosExcursion(String codigo, String tipo) throws Exc_Boletos, Exc_Excursiones, RemoteException{
		Iterador<VOBoleto2> ret = new Iterador<VOBoleto2>();
		monitor.startRead();
		if(!this.datos.excursiones().empty()){
			if(this.datos.excursiones().exists(codigo)){			
				Excursion excAux = this.datos.excursiones().find(codigo);
				if(! excAux.getBoletos().empty()){
					Iterator<Boleto> ite = excAux.getBoletos().iterator();
					int nroBoleto = 1;
					while(ite.hasNext()){
						Boleto bolAux = ite.next();
						if(bolAux.getTipo().equals(tipo)){
							if (tipo.equals("Especial")){
								ret.add(new VOBoleto2(nroBoleto, bolAux.getEdadPasajero(), bolAux.getLugarPrecedencia(), bolAux.getNroCelular(),((Especial) bolAux).getDtoAdicional()));
							}else{
								ret.add(new VOBoleto2(nroBoleto, bolAux.getEdadPasajero(), bolAux.getLugarPrecedencia(), bolAux.getNroCelular()));
							}
						}
						nroBoleto++;
					}
				}
				else{
					monitor.endRead();
					throw new Exc_Boletos("Advertencia: No hay boletos vendidos para esta excursion.");
				}
				monitor.endRead();
			}
			else{
				monitor.endRead();
				throw new Exc_Excursiones("La excursion con el codigo" + codigo + " no se encuentra ingresada en el sistema.");
			}
		}else{
			monitor.endRead();
			throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
		}
		return ret;
	}
	

	public Iterador<VOExcursionListado> listadoExcursionesDestino(String destino) throws Exc_Excursiones, RemoteException{
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		if (destino.equals("")){
			throw new Exc_Excursiones("Destino vacio");
		}else{
			monitor.startRead();
			if(!this.datos.excursiones().empty()){
			
				if (this.datos.excursiones().excursionMismoDestino(destino)){
					if(! this.datos.excursiones().empty()){
						Iterator<Excursion> ite = this.datos.excursiones().iterator();
						while(ite.hasNext()){
							Excursion excAux = ite.next();
							if(excAux.getDestino().equals(destino)){
								int asientosDisp = 0;
								Bus busAux = this.datos.buses().obtenerBusConExcursion(excAux.getCodigo());
								asientosDisp = busAux.asientosDisponiblesParaExcursion(excAux.getCodigo());
								ret.add(new VOExcursionListado(excAux.getCodigo(), excAux.getDestino(), excAux.getHpartida(), excAux.getHllegada(), excAux.getPrecioBase(), asientosDisp));
							}
						}
						monitor.endRead();
					}
					else{
						monitor.endRead();
						throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
					}
				}else{
					monitor.endRead();
					throw new Exc_Excursiones("No hay excursiones para ese destino.");
				}
			}else{
				monitor.endRead();
				throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
			}
			return ret;
		}
	}
	

	public Iterador<VOExcursionListado> listadoExcursionesPrecio(double precioMin, double precioMax) throws Exc_Excursiones, RemoteException{
		Iterador<VOExcursionListado> ret = new Iterador<VOExcursionListado>();
		monitor.startRead();
		if(!this.datos.excursiones().empty()){
			Iterator<Excursion> ite = this.datos.excursiones().iterator();
			while(ite.hasNext()){
				Excursion aux = ite.next();
				if(((Excursion) aux).dentroRango(precioMin, precioMax)){
					int asientosDisp = 0;
					Bus busAux = this.datos.buses().obtenerBusConExcursion(aux.getCodigo());
					asientosDisp = busAux.asientosDisponiblesParaExcursion(aux.getCodigo());
					ret.add(new VOExcursionListado(aux.getCodigo(), aux.getDestino(), aux.getHpartida(), aux.getHllegada(), aux.getPrecioBase(), asientosDisp));
				}
			}
			monitor.endRead();
		}
		else{
			monitor.endRead();
			throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
		}
		return ret;
	}
}
