package logica.concurrencia;

public class Monitor {
	int cantLectores;
	boolean leyendo;
	
	public Monitor(){
		this.cantLectores = 0;
		leyendo = false;
	}
	
	public synchronized void startRead(){
		
	}
	
	public synchronized void endRead(){
		
	}
	
	public synchronized void startWrite(){
		
	}
	
	public synchronized void endWrite(){
		
	}
}
