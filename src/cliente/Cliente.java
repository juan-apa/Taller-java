package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import logica.Excepciones.colecciones.Exc_Boletos;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.fachada.IFachada;

public class Cliente {

	public static void main(String[] args) {
		try {
			IFachada fachada = (IFachada) Naming.lookup("//localhost:1099/fachada");
			fachada.listadoBoletosExcursion("001", "comun");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		} catch (Exc_Boletos e) {
			e.printStackTrace();
		} catch (Exc_Excursiones e) {
			System.out.println(e.toString());
		}
					

	}

}
