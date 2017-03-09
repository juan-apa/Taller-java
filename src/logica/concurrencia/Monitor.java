package logica.concurrencia;

import java.io.Serializable;


public class Monitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	  private int readers       = 0;
	  private int writers       = 0;
	  private int writeRequests = 0;
	  private static Monitor instancia;
	
	public static Monitor getInstancia(){
		if(instancia == null){
			return new Monitor();
		}
		else{
			return null;
		}
	}
	private Monitor(){
		readers = 0;
		writers = 0;
		writeRequests = 0;
	}
	
	  
	  public synchronized void startRead(){
	    while(writers > 0 || writeRequests > 0){
	      try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    }
	    readers++;
	  }
	
	  public synchronized void endRead(){
	    readers--;
	    notifyAll();
	  }
	
	  public synchronized void startWrite(){
	    writeRequests++;
	
	    while(readers > 0 || writers > 0){
	      try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    }
	    writeRequests--;
	    writers++;
	  }
	
	  public synchronized void endWrite(){
	    writers--;
	    notifyAll();
	  }
}
