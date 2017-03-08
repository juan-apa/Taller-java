package logica.concurrencia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	/*TODO revisar monitor*/
	private int cantLectores;
	private int cantEscritores;
	private boolean leyendo;
	private boolean escribiendo;
	private final Lock lock;
	private final Condition bloquear;
	private final Condition cola_escribiendo;
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
		lock = new ReentrantLock();
		bloquear = lock.newCondition();
		cola_escribiendo = lock.newCondition();
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
		lock.lock();
		try{
			while(escribiendo){
				try{
					bloquear.await();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			cantLectores++;
			leyendo = true;
		}
		finally{
			lock.unlock();
		}		
	}
	
	public synchronized void endRead(){
		try{
			lock.lock();
			cantLectores--;
//			this.notify(); /*Esto va aca???*/
			if(cantLectores == 0){
				leyendo = false;
			}
			bloquear.signal();
		}
		finally{
			lock.unlock();
		}
		
		
//		this.notify();
	}
	
	public synchronized void startWrite(){
		try{
			lock.lock();
			while(leyendo || escribiendo){
				try {
					bloquear.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			escribiendo = true;
			cantEscritores++;
		}finally{
			lock.unlock();
		}
		
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
		try{
			lock.lock();
			cantEscritores--;
			escribiendo = false;
			bloquear.signal();
			
		}finally{
			lock.unlock();
		}
		
//		this.notify();
	}
}
