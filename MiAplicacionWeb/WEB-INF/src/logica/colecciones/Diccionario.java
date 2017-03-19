package logica.colecciones;

import java.util.Iterator;

public interface Diccionario {
	public boolean empty();
	public void insert(Object insertar);
	public Object find(String clave);
	public int length();
	public Iterator<?> iterator();
	public boolean exists(String clave);
	public void remove(String clave);
}
