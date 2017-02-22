package logica.colecciones;

import java.util.Date;
import java.util.TreeMap;

import logica.Excepciones.colecciones.Exc_Buses;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.objetos.Bus;
import logica.objetos.Excursion;

import java.util.Iterator;
import java.io.Serializable;

public class Buses implements Diccionario, Serializable{
	/*Atributos*/
	private TreeMap<String, Bus> diccionario;
	
	/*Constructores*/
	public Buses() {
		super();
		diccionario = new TreeMap<String, Bus>();
	}
	
	/*Getters y Setters*/
	public TreeMap<String, Bus> getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(TreeMap<String, Bus> diccionario) {
		this.diccionario = diccionario;
	}
	
	
	/*Funciones propias*/
	
	public void asignarExcursionAUnBus(Excursion insertar) throws Exc_Buses{
		/*TODO revisar para sacar el codigo que verifica si la excrusion entra en el diccionario y ponerlo en la clase excursiones.*/
		boolean busLibre = false;
		Iterator<Bus> recorrida = this.diccionario.values().iterator();
		while(!busLibre && (recorrida.hasNext())){
			Bus busAux =recorrida.next();
			boolean entraEnBus = busAux.getExcuBus().entraExcursion(insertar);
			/*Si cuando salgo de la recorrida de Excursiones entraEnBus==true, es porque no hay ninguna excursion que interfiera con la pasada por param.*/
			if(entraEnBus){
				/*Cambio el valor de busLibre a true y asigno la excursion al bus.*/
				busLibre = true;
				busAux.insertarExcursion(insertar);
			}
		}
		if(!busLibre){
			throw new Exc_Buses("No hay ningun bus con un horario disponible para insertar la Excursion");
		}
	}
	
	public Bus obtenerBusConExcursion(String buscar){
		Bus busAux = null;
		boolean encontrado = false;
		Iterator<Bus> iteBus = this.iterator();
		while(iteBus.hasNext() && !encontrado){
			busAux = iteBus.next();
			encontrado = busAux.getExcuBus().exists(buscar);
		}
		return busAux;
	}
	
	public void reasignarExcursion(Bus original, Excursion reasignar) throws Exc_Buses{
		boolean reasignado = false;
		Iterator <Bus> recorrida = this.iterator();
		while(recorrida.hasNext() && !reasignado){
			Bus aux = recorrida.next();
			if(!original.equals(aux) && !reasignado){ /*Si el bus no es el que le quiero sacar la excursion*/
				if(aux.entraAsientosEnBus(original.getCapPasajeros())){ /*Si tiene  mayor o igual cantidad de asientos*/
					if(aux.getExcuBus().entraExcursion(reasignar)){ /*Si tiene un horario disponible para la reasignar la excursion*/
						/*Cumple todos los requisitos*/
						aux.insertarExcursion(reasignar); /*Le asigno la excursion al bus que cumple los requsitos*/
						/*Tengo que actualizar la cantidad de boletos maximos para la excursion.*/
						aux.getExcuBus().find(reasignar.getCodigo()).actualizarCantBoletos(aux.getCapPasajeros());
						original.sacarExcursion(reasignar); /*Le saco la excursion al bus original*/
					}
				}
			}
		}
		/*Si termine la recorrida y no pude reasignarle la excursion a nadie (reasignado == false)*/
		if(!reasignado){
			throw new Exc_Buses("La excursion no pudo ser reasignada.");
		}
	}
	

	

	
	public void imprimir(){
		System.out.println(this.diccionario.toString());
	}
	
	/*Metodos de la interface Diccionario sobreescritos*/
	@Override
	public boolean empty() {
		return this.diccionario.isEmpty();
	}

	@Override
	public void insert(Object insertar) {
		this.diccionario.put(((Bus)insertar).getMatricula(), (Bus)insertar);
	}

	@Override
	public int length() {
		return this.diccionario.size();
	}

	@Override
	public Iterator iterator() {
		return (this.diccionario.values().iterator());
	}
	
	@Override
	public Object find(String codigo){
		return this.diccionario.get(codigo);
	}

	@Override
	public boolean exists(String clave) {
		return this.diccionario.containsKey(clave);
	}

	@Override
	public String toString() {
		return "Buses [diccionario=" + diccionario.toString() + "]";
	}

	@Override
	public void remove(Object borrar) {
		this.diccionario.remove(borrar);
		
	}	
	
}
