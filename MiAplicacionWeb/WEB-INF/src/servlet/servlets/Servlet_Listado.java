package servlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.controladoras.Controladora_Servlet;
import logica.Excepciones.colecciones.Exc_Excursiones;
import logica.ValueObjects.VOExcursionListado;

public class Servlet_Listado extends HttpServlet{
	private String info;
	private String error;
	private String advertencia;
	private static final long serialVersionUID = 1L;

	public void setMensajeError(String error){
		this.info = error;
	}
	
	public void setError(String error){
		this.error = error;
	}
	
	public void setAdvertencia(String advertencia){
		this.advertencia = advertencia;
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
		String destino = req.getParameter("destino");
		String precioMin = req.getParameter("precioMin");
		String precioMax = req.getParameter("precioMax");
		String seleccion = req.getParameter("listado");

		/*Como los radiobutton siempre va a haber alguno seleccionado, puedo preguntar por
		 * el valor de seleccion.*/

		/*Desde la capa grafica permito que me pasen solo datos validos*/
		Controladora_Servlet c;
		ArrayList<VOExcursionListado> arr = new ArrayList<VOExcursionListado>();
		error = new String("false");
		advertencia = new String("false");
		info = new String("Error");
		
		
		if(seleccion.equals("destino")){
			c = new Controladora_Servlet(this);
			if(this.error.equals("false")){
				arr = c.listadoExcuDestino(destino);
			}
		}
		else{
			c = new Controladora_Servlet(this);
			if(this.error.equals("false")){
				arr = c.listadoExcuPrecio(precioMin, precioMax);
			}	
		}

		ServletContext context = super.getServletContext();
		
		synchronized(context){
				ArrayList<VOExcursionListado> resultados = (ArrayList<VOExcursionListado>) context.getAttribute("resultados");
				String mensaje = (String) context.getAttribute("mensaje");
				String error1 = (String) context.getAttribute("error");
				String advertencia1 = (String) context.getAttribute("advertencia");
				mensaje = info;
				resultados = arr;
				error1 = error;
				advertencia1 = advertencia;
				context.setAttribute("resultados", resultados);
				context.setAttribute("mensaje", mensaje);
				context.setAttribute("error", error1);
				context.setAttribute("advertencia", advertencia1);
		}
		
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	}

}
