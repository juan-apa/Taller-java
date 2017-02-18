package logica;
import java.util.TreeMap;

import logica.Bus;

import java.io.Serializable;

public class Buses implements Serializable{
	private TreeMap<String, Bus> Buses;

	public TreeMap<String, Bus> getBuses() {
		return Buses;
	}

	public void setBuses(TreeMap<String, Bus> buses) {
		Buses = buses;
	}

	public Buses(TreeMap<String, Bus> buses) {
		super();
		Buses = buses;
	}
	
	
} 