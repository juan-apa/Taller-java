package logica.concurrencia;

public class Monitor {
	/*TODO revisar monitor*/
	int cantLectores;
	int cantEscritores;
	boolean leyendo;
	boolean escribiendo;
	private static Monitor instancia;
	
	/*Acceso a lectura: si nadie esta escribiendo o quiere escribir, leo*/
	/*Acceso a escritura: si nadie esta leyendo o escribiendo, escribo*/
	
	public static Monitor getInstancia(){
		if(instancia == null){
			return new Monitor();
		}
		else{
			return null;
		}
	}
	
	private Monitor(){
		this.cantLectores = 0;
		this.cantEscritores = 0;
		leyendo = false;
		escribiendo = false;
	}
	
	public synchronized void startRead(){
//		while(cantEscritores > 0){
//			try {
//				this.wait(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		/*Si llego aca es porque no hay gente escribiendo*/
//		cantLectores++;
		while(escribiendo){
			try{
				Thread.sleep(5);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		cantLectores++;
		leyendo = true;
	}
	
	public synchronized void endRead(){
		cantLectores--;
//		this.notify(); /*Esto va aca???*/
		if(cantLectores == 0){
			leyendo = false;
		}
//		this.notify();
	}
	
	public synchronized void startWrite(){
		while(leyendo || escribiendo){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		escribiendo = true;
		cantEscritores++;
//		while(cantLectores > 0 || cantEscritores > 0){
//			try {
//				this.wait(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println(e.toString());
//			}
//		}
//		cantEscritores++;
	}
	
	public synchronized void endWrite(){
		cantEscritores--;
		escribiendo = false;
//		this.notify();
	}
}
