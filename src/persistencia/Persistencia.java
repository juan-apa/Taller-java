package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import persistencia.Propiedades;
import logica.Excepciones.objetos.Exc_Persistencia;
import logica.colecciones.Datos;

public class Persistencia {
	
	public void respaldar (Datos d) throws Exc_Persistencia{ 
		try{
			Propiedades p = new Propiedades();
			String archivo = p.buscar("Archivo");
			FileOutputStream Arch = new FileOutputStream(archivo);
			ObjectOutputStream flujo = new ObjectOutputStream(Arch);
			flujo.writeObject(d);
			flujo.close();
			Arch.close();
		}
		catch (IOException e){ 
			throw new Exc_Persistencia("Hubo un error al respaldar la informacion");
		}	
	}
	
	public Datos recuperar (String archivo) throws Exc_Persistencia, ClassNotFoundException{ 
		try{
			FileInputStream Arch = new FileInputStream(archivo);
			ObjectInputStream flujo = new ObjectInputStream(Arch);
			Datos d = (Datos)flujo.readObject();
			flujo.close();
			Arch.close();
			return d;	
		}
		catch (IOException e){ 
			throw new Exc_Persistencia("Hubo un error al recuperar la informacion");
		}
	}
}
		
	