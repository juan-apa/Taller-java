package logica.fachada;

import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.util.Iterator;
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

public  interface IFachada extends Remote{
	//Requerimiento 1
	public void registroNuevoBus(VOBus entrada) throws Exc_Bus, Exc_Buses, RemoteException;
	//Requerimiento 2
	public Iterator<VOBusExc> listadoGeneralBuses() throws Exc_Buses, RemoteException;
	//Requerimiento 3
	public Iterator<VOExcursionListado> listadoExcursionesDeBus(String matricula) throws Exc_Bus, Exc_Buses, Exc_Excursiones, RemoteException;
	//Requerimiento 4
	public void registroNuevaExcursion(VOExcursion entrada) throws Exc_Excursiones, Exc_Buses, Exc_Excursion, RemoteException;
	//Requerimiento 5
	public void reasignacionExcursion(String codigo) throws Exc_Buses, Exc_Excursiones, RemoteException;
	//Requerimiento 6
	public void respaldar() throws Exc_Persistencia, RemoteException;
	public void recuperar() throws Exc_Persistencia, RemoteException;
	//Requerimiento 7
	public void ventaBoleto(VOBoleto entrada) throws Exc_Boleto, Exc_Boletos, Exc_Excursiones, RemoteException;
	//Requerimiento 8
	public double recaudadoEnExcursion(String codigo) throws Exc_Boletos, Exc_Excursiones, RemoteException;
	//Requerimiento 9
	public Iterator<VOBoleto2> listadoBoletosExcursion(String codigo, String tipo) throws Exc_Boletos, Exc_Excursiones, RemoteException;
	//Requerimiento 10
	public Iterator<VOExcursionListado> listadoExcursionesDestino(String destino) throws Exc_Excursiones, RemoteException;
	//Requerimiento 11
	public Iterator<VOExcursionListado> listadoExcursionesPrecio(double precioMin, double precioMax) throws Exc_Excursiones, RemoteException;

}
