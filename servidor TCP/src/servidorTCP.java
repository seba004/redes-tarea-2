import java.io.*;
import java.net.*;

public class servidorTCP implements Runnable {
	
	static final int puerto  = 8081;
	Socket con;
	
	
	public servidorTCP(Socket con) {
		this.con = con;
	}

	public static void main(String argv[]) throws Exception{ 	 	
			
			ServerSocket socketservidor = new ServerSocket(puerto);
			
			while(true){
				servidorTCP cliente = new servidorTCP(socketservidor.accept());
				Thread hilo = new Thread(cliente);
				hilo.start();
				
				}
			}
		
	
	@Override
	public void run() {
		//aca va codigo java simeple gatita 1313
		
	}
		
}

