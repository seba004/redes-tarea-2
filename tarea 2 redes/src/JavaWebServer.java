import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
import java.net.ServerSocket; 
import java.net.Socket;
import java.util.concurrent.Executor; 
import java.util.concurrent.Executors; 



public class JavaWebServer
{   
	private static final int fNumberOfThreads = 100;	//para que el  servidor pueda tener varios clientes
	private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);//para ejecutar hebras   
	
	
	
	static String direccion;
	static String parametro;
 
    
	
	private static String corta(String palabra){
		int i=0;
		String nombre = null;
		String ip;
		String puerto;
		String[] tuplas =palabra.split("&");
		
		 
		 while ( i < tuplas.length){
			 String[] valor= tuplas[i].split("=");
			 if (valor[0].equals("nombre")){
				 nombre = valor[1];
			 }
			 if (valor[0].equals("ip")){
				 ip = valor[1];
			 }
			 if (valor[0].equals("puerto")){
				 puerto = valor[1];
			 }
			 i++;
		 }
		return nombre;
		
	}
	
	private static void escritor(String palabro) throws IOException {
		
		String fichero = "contactos.html";
	    FileWriter escri = new FileWriter("src/" + fichero,true); //valor true agregara los datos nuevos
	    escri.write("<p>"+palabro+"</p>\r\n");// se guarda el nombre en el archivo de texto con formato html
	    escri.close();
		
	}
	
    public static void identificar(String direct) {
            	  
    	    
        String[] splitPath = direct.split("\\?");
        
        if(splitPath.length > 1) {
            parametro = splitPath[1];
        }
        
        
        if(splitPath[0].equals("/")) {
        	direccion = "/src/pagredes2.html" ;
        }
        else {
            direccion = "/src" + direct;
        }
    
    }
    
	
	
	
	
	
	public static void main(String[] args) throws IOException 
	{ 
		ServerSocket socket = new ServerSocket(8086); // se crea socket en puerto designado
		
		while (true) 
		{
			final Socket connection = socket.accept(); //while  hasta que  exista conexion 
			Runnable task = new Runnable() // meto los hilos
			{ 
				@Override 
				public void run() 
				{ 
					HandleRequest(connection);//ejecuto hilos como  manejadores del soket
				} 
			};
			fThreadPool.execute(task);
		}
	}   
	
	private static void HandleRequest(Socket sockete)
	{ 
		BufferedReader in;
		PrintWriter out; 
		String request;
		int postsize = 0;
		try 
		{ 
			
			in = new BufferedReader(new InputStreamReader(sockete.getInputStream())); 
			out = new PrintWriter(sockete.getOutputStream(), true);
			
			while(true)
			{

				request = in.readLine(); 
				
				if(request != null) {
									
					
					if(request.startsWith("GET") || request.startsWith("POST") ){
						
		   	        	String[] splitted = request.split("\\s+");
		   		        identificar(splitted[1]);
		   	             
		   		         out.println("HTTP/1.0 200");
		   		         //out.println("Content-type: text/html"); 
		   		         out.println("Server-name: myserver"); 
		   	             
		   	             File file = new File(System.getProperty("user.dir") + direccion );
		   	             
		   	             FileInputStream fis = new FileInputStream(file);
		   	             
		   	             out.println("Content-length: " + file.length());
		   	             out.println("");

		   	             byte[] buffer = new byte[(int)file.length()];
		   	             
		   	             for(int i=0 ; i < file.length(); i++)
		   	             {
		   	            	 buffer[i] = (byte)fis.read();
		   	             }
		   	             
		   	             sockete.getOutputStream().write(buffer);
		   	             fis.close();
		   	        }
					
					if(request.startsWith("Content-Length:")) {
						postsize = Integer.parseInt(request.split("\\s+")[1]);
					}

					if(request.length() == 0) {
	                    break;
	                }
				}
				
				out.flush();
				out.close(); 
				sockete.close();
				
				
				
				
			}
			
			if(postsize > 0) {
				char cont[] = new char[postsize];
				in.read(cont);
				String a = new String(cont);
				escritor(corta(a));
				postsize = 0;
			}
			
		} 
		catch (IOException e)
		{ 
			System.out.println("Failed respond to client request: " + e.getMessage());
		} 
		finally 
		{ 
			if (sockete != null) 
			{
				try 
				{
					sockete.close();
				} 
				catch (IOException e)
				{ 
					e.printStackTrace();
				}
			}
		} 
		return; 
	}   
	
}