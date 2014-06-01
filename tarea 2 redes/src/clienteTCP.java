import java.io.*;
import java.net.*;





public class clienteTCP {
	
	 Socket conexion;
	 final static int  port= 8086;
	
	 
	 public clienteTCP(String ip_out,String port_out,String ip_in, String port_in,String message){
		 try {
			this.conexion=new Socket("localhost",port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	 }
	 
	 public void sendmessage (String message) throws IOException{
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeBytes(message+'\n');
		 outServer.flush();
	 }
	 
	 public void say_hi (String ip) throws IOException{
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeByte('%'+'\n');//preguntar por mensajes simbolo = %
		 outServer.writeBytes(ip+'\n');
		 outServer.flush();
	 }
	 public void ask_message(String ip_out,String port_out, String ip_in, String port_in) throws IOException{
		 String  chat= null;
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeByte('!'+'\n');//preguntar por mensajes simbolo = !
		 outServer.writeBytes(ip_out+port_out+ip_in+port_in+'\n');//ip salida puerto salida ip llegada puerto llegda
		 outServer.flush();
		
	 }
	 
	 public void send_message(String ip_out,String port_out, String ip_in, String port_in,String message) throws IOException{
		
		 DataOutputStream outServer = new DataOutputStream(this.conexion.getOutputStream());
		 outServer.writeByte('@'+'\n');//chatear = @
		 outServer.writeBytes(ip_out+port_out+ip_in+port_in+message+'\n');//ip salida puerto salida ip llegada puerto llegda
		 outServer.flush();
	 }
	
	 //outToServer.writeBytes(sentence + '\n');//para escribir en servidor
}
 