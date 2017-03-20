package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import logica.Excepciones.objetos.Exc_Persistencia;

public class Propiedades {
	
	public String buscar(String nomProp) throws Exc_Persistencia{ 
		try{
			Properties p = new Properties();
			String nombreProperties = ".settings/datos.properties";
			p.load(new FileInputStream(nombreProperties));
			if(nomProp == "Archivo"){
				String archivo = p.getProperty("Archivo");
				return archivo;
			}else if(nomProp == "Ip"){
				String archivo = p.getProperty("Ip");
				return archivo;
			}else if((nomProp == "Puerto")){
				String archivo = p.getProperty("Puerto");
				return archivo;
			}else{
			throw new Exc_Persistencia("No se encuentra los datos en la Properti");
			}
		}
		catch (IOException e){ 
			e.printStackTrace();
		throw new Exc_Persistencia("Hubo un error al buscar la propertin");
		}	
	}
}
