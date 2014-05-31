import java.io.*;
import java.net.*;





public class clienteTCP {
	
	 Socket conexion;
	 final static int  port= 8086;
	 
	 public clienteTCP(){
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
	
	 //outToServer.writeBytes(sentence + '\n');//para escribir en servidor
}
 