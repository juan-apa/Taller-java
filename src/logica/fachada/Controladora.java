package logica.fachada;

import java.io.Serializable;
import java.rmi.RemoteException;
import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.Excepciones.objetos.Exc_Boleto;
import logica.Excepciones.objetos.Exc_Bus;
import logica.Excepciones.objetos.Exc_Excursion;
import logica.ValueObjects.VOBoleto;
import logica.ValueObjects.VOBoleto2;
import logica.ValueObjects.VOBus;
import logica.ValueObjects.VOBusExc;
import logica.ValueObjects.VOExcursion;
import logica.ValueObjects.VOExcursionListado;
import logica.colecciones.Iterador;
import logica.objetos.Bus;

public class Controladora implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1771386298451442810L;
	
	public Controladora() throws RemoteException {

	}

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
	public void ventaBoleto(Fachada f, VOBoleto entrada) throws Exc_Boleto, Exc_Boletos, Exc_Excursiones, RemoteException{
		if(entrada.getEdadPasajero() < 0){
			throw new Exc_Boletos("La edad del pasajero no puede ser menor a 0");
		}else{
			if(entrada.getNroCelular()/10000000 < 0){
				throw new Exc_Boletos("Al numero de telefono le faltan digitos");
			}else{
				if(entrada.getDtoAdicional() < 0.0){
					throw new Exc_Boletos("El descuento no puede ser menor a 0");
				}else{
					if(!f.getExcursiones().exists(entrada.getCodExcursion())){
						throw new Exc_Excursiones("No existe una excursion con este codigo");
					}else{
						if(f.getExcursiones().find(entrada.getCodExcursion()).getBoletos().full()){
							throw new Exc_Boletos("Todos los boletos para esta excursion ya estan vendidos");
						}else{
							f.ventaBoleto(entrada);
						}
					}
				}
			}
		}
	}
	
	//REQUERIMIENTO 8
	public double recaudadoEnExcursion(Fachada f, String codigo) throws Exc_Boletos, Exc_Excursiones, RemoteException{
		if(!f.getExcursiones().exists(codigo)){
			throw new Exc_Excursiones("La excursion con el codigo " + codigo + " no se encuentra ingresada en el sistema.");
		}else{
			if(f.getExcursiones().find(codigo).getBoletos().empty()){
				throw new Exc_Boletos("No hay boletos vendidos para la excursion con el codigo "+codigo);
			}else{
				return f.recaudadoEnExcursion(codigo);
			}
		}
	}
	
	//REQUERIMIENTO 9
	public Iterador<VOBoleto2> listadoBoletosExcursion(Fachada f,String codigo, String tipo) throws Exc_Boletos, Exc_Excursiones, RemoteException{
		if(!f.getExcursiones().exists(codigo)){
			throw new Exc_Excursiones("La excursion con el codigo" + codigo + " no se encuentra ingresada en el sistema.");
		}else{
			if(f.getExcursiones().find(codigo).getBoletos().empty()){
				throw new Exc_Boletos("Advertencia: no hay boletos vendidos para esta excursion.");
			}else{
				Iterador<VOBoleto2> aux = new Iterador<VOBoleto2>();
				aux= f.listadoBoletosExcursion(codigo, tipo);
				if(aux.hasNext()){
					return aux;
				}else{
					throw new Exc_Boletos("De los Boletos vendidos no hay vnedidos del tipo "+tipo);
				}
			}
		}
	}
	
	//REQUERIMIENTO 10
	public Iterador<VOExcursionListado> listadoExcursionesDestino(Fachada f, String destino) throws Exc_Excursiones, RemoteException{
		if (destino.equals("")){
			throw new Exc_Excursiones("Destino vacio");
		}else{
			if(f.getExcursiones().empty()){
				throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
			}else{
				Iterador<VOExcursionListado> aux = new Iterador<VOExcursionListado>();
				aux = f.listadoExcursionesDestino(destino);
				if(aux.hasNext()){
					return aux;
				}else{
					throw new Exc_Excursiones("No hay Excursiones registradas con "+destino+" como destino");
				}
			}
		}
	}
	
	//REQUERIMIENTO 11
	public Iterador<VOExcursionListado> listadoExcursionesPrecio(Fachada f, double precioMin, double precioMax) throws Exc_Excursiones, RemoteException{
		if(f.getExcursiones().empty()){
			throw new Exc_Excursiones("No hay excursiones ingresadas en el sistema.");
		}else{
			Iterador<VOExcursionListado> aux = new Iterador<VOExcursionListado>();
			aux = f.listadoExcursionesPrecio(precioMin, precioMax);
			if(aux.hasNext()){
				return aux;
			}else{
				throw new Exc_Excursiones("No hay Excursiones registradas en el rango de precios de "+precioMin+" hasta "+precioMax+" inclusive");
			}
		}
	}
}