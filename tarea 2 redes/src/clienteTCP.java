
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;







public class clienteTCP {
	
	 Socket conexion;
	 final static int  port= 9100;
	 
	 String IP;
	 int Puerto;
	 String protocol_set[] ={"say_hi","ask_message","send_message"};
	 
	
	 
	 public clienteTCP(){ // in persona contacto
		 try {
			 
			this.conexion=new Socket("localhost",port);
			this.IP = Inet4Address.getLocalHost().getHostAddress();
			this.Puerto = port;	
			
			
		} catch (UnknownHostException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(clienteTCP.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	 public void say_hi () throws IOException{
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeBytes(protocol_set[0]+"¬¬"+"holiwi"+'\n');
		 outServer.flush();
	 }
	 
	 public void ask_message( String ip_contacto, String port_contacto) throws IOException{
		 String Puerto = Integer.toString(port);
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeBytes(protocol_set[1]+"¬¬"+ip_contacto+'\n');
		
		 outServer.flush();
		
	 }
	 
	 public void send_message( String ip_contacto, String port_contacto,String message) throws IOException{
		
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeBytes(protocol_set[2]+"¬¬"+IP+"¬¬"+message+'\n');//ip salida puerto salida ip llegada puerto llegda
		 
		 outServer.flush();
	 }
	
	 public String read_server () throws IOException{
		 	 
		 	BufferedReader inServidorTCP = new BufferedReader(new InputStreamReader(this.conexion.getInputStream()));
	        String line = inServidorTCP.readLine();
	        return line;
	 }
}
 