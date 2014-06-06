import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
import java.net.ServerSocket; 
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.Executor; 
import java.util.concurrent.Executors; 




public class JavaWebServer
{   
	private static final int fNumberOfThreads = 100;	//para que el  servidor pueda tener varios clientes
	private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);//para ejecutar hebras   



	static String direccion;
	static String parametro;
 
    

	private static String corta(String palabra, String pedido){ //modificar para que me devuelva lo que yo quiero
		int i=0;
		String nombre = null;
		String ip = null;
		String puerto = null;
		String entrega = null;
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

		 if (pedido.equals("nombre")){
			 entrega = nombre;			 
		 }

		 if (pedido.equals("ip")){
			 entrega = ip;			 
		 }
		 if (pedido.equals("puerto")){
			 entrega = puerto;			 
		 }

		 return entrega;
	}

	private static void escritor(String palabro) throws IOException {

		String fichero = "contactos.html";
	    FileWriter escri = new FileWriter("src/" + fichero,true); //valor true agregara los datos nuevos
	    escri.write("<p>"+palabro+"</p>\r\n");// se guarda el nombre en el archivo de texto con formato html
	    escri.close();

	}

	private static void escritor2(String palabro) throws IOException {

		String fichero = "todo.txt";
	    FileWriter escri = new FileWriter("src/" + fichero,true); //valor true agregara los datos nuevos
	    escri.write(palabro+"\n");// se guarda el nombre en el archivo de texto con formato html
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
    
    private static String obtener(String contacto, String pedido) {
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String nombre;
	      String entrega = null;

	      try {
	         archivo = new File ("src/todo.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea = br.readLine())!= null){


	        	 nombre = corta(linea,"nombre");

	        	 if(nombre.equals(contacto)){
	        		 entrega = corta(linea,pedido);	        		 


	        	 }
	         }
	        	// return entrega;
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{

	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
		return entrega;
	   }




	public static void main(String[] args) throws IOException 
	{ 
<<<<<<< HEAD
		ServerSocket socket = new ServerSocket(10100); // se crea socket en puerto designado
=======
		ServerSocket socket = new ServerSocket(10016); // se crea socket en puerto designado
>>>>>>> 87c2a40c7dae2099051b247f79e4d99530a878c3
		
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

	static String contacto = "no a seleccionado contacto"; // ver si resulta
	static String ip_contacto;
	static String port_contacto;

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
				clienteTCP clienton = new clienteTCP();
					

				
				//System.out.println(contacto);
				//System.out.println(a); //ver que se escribe en los "Post"

				if  (a.contains("%24")){ // $ quiere chatear con alguien	
<<<<<<< HEAD
					
					
=======
					System.out.println("muerte acaaaaaa");
					//clienteTCP clienton = new clienteTCP();
>>>>>>> 87c2a40c7dae2099051b247f79e4d99530a878c3
					clienton.say_hi();
					String respuesta = clienton.read_server();
					StringTokenizer tokens = new StringTokenizer(respuesta,"¬¬");
					String Protocolo =tokens.nextToken();
					if (Protocolo.equals("hi_back")){
						String massage =tokens.nextToken();
						System.out.println(massage);
					}	
				
				
				contacto = a.substring(10);
				
				ip_contacto = obtener(contacto,"ip");
				port_contacto = obtener(contacto,"puerto");
				
				
				clienton.ask_message( ip_contacto, port_contacto);//crear variables para las cosa sque se manda
				String respuesta_peticion;
				String fichero = "chatactual.html";
			    FileWriter escri = new FileWriter("src/" + fichero,true); //valor true agregara los datos nuevos
<<<<<<< HEAD
			   int control=1;
				while (control==1){
					 respuesta_peticion=clienton.read_server();
					 
					 if(respuesta_peticion.equals("fin")){
							control=0;
							
						}
					 else{
						escri.write("<p>"+respuesta_peticion+"</p>\r\n");
						//System.out.println(respuesta_peticion);
					 }
					}
					
				
=======
			   
				while (clienton.read_server()!= null){
					respuesta_peticion = clienton.read_server();
					System.out.println(respuesta_peticion);
					if (respuesta_peticion.equals("okrady")){
						 System.out.println("finalize");
					}
					else{
					StringTokenizer token = new StringTokenizer(respuesta_peticion,"¬¬");
					String Protocolo1 =token.nextToken();
					if(Protocolo1.equals("dispach_message")){
						String chat =token.nextToken();
						escri.write("<p>"+chat+"</p>\r\n");
					}
					if(Protocolo1.equals("end")){
						String chat =token.nextToken();
						System.out.println(chat);
					}
				}
			}
>>>>>>> 87c2a40c7dae2099051b247f79e4d99530a878c3
				 escri.close();
				 System.out.println("finalize");
				//System.out.println(ip_contacto);
				//System.out.println(port_contacto);
<<<<<<< HEAD
				 	
					postsize=0;
					
=======
						
>>>>>>> 87c2a40c7dae2099051b247f79e4d99530a878c3
				}

				if(a.contains("%23")){ // esta escribiendo en el chat
				
					//clienteTCP clienton = new clienteTCP();
					clienton.say_hi();
					String respuesta = clienton.read_server();
					StringTokenizer tokens = new StringTokenizer(respuesta,"¬¬");
					String Protocolo =tokens.nextToken();
					if (Protocolo.equals("hi_back")){
						String massage =tokens.nextToken();
						System.out.println(massage);
					}	
					
					
				String mensaje = a.substring(10);
				clienton.send_message(ip_contacto, port_contacto, mensaje);
				
<<<<<<< HEAD
				
=======
				System.out.println("infinito en html");
>>>>>>> 87c2a40c7dae2099051b247f79e4d99530a878c3
				
				String ultima_respuesta = clienton.read_server();
				StringTokenizer toke = new StringTokenizer(respuesta,"¬¬");
				String Protocolo3 =toke.nextToken();
				if (Protocolo3.equals("save_message")){
					String massage =toke.nextToken();
					System.out.println(massage);
				}
				
				
				System.out.println("termino");
				postsize=0;
				}
				
				if(a.contains("&")){ // esta agregando un contacto

				escritor(corta(a,"nombre"));
				escritor2(a);
				postsize = 0;

				}
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