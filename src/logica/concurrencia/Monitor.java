package logica.concurrencia;

public class Monitor {
	/*TODO revisar monitor*/
	int cantLectores;
	int cantEscritores;
	boolean leyendo;
	
	/*Acceso a lectura: si nadie esta escribiendo o quiere escribir, leo*/
	/*Acceso a escritura: si nadie esta leyendo o escribiendo, escribo*/
	public Monitor(){
		this.cantLectores = 0;
		this.cantEscritores = 0;
	}
	
	public synchronized void startRead(){
		while(cantEscritores > 0){
			try {
				this.wait(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*Si llego aca es porque no hay gente escribiendo*/
		cantLectores++;
	}
	
	public synchronized void endRead(){
		cantLectores--;
		this.notify(); /*Esto va aca???*/
	}
	
	public synchronized void startWrite(){
			try {
				this.wait(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.toString());
			}
		}
		cantEscritores++;
	}
	
	public synchronized void endWrite(){
		cantEscritores--;
		this.notify();
	}
}
