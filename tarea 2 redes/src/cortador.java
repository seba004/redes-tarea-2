import java.util.Scanner;
import java.io.*;


public class cortador {

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
	
	private static void escritor(String palabro) throws IOException{
		try{
		String fichero = "contactos.html";
	    FileWriter escri = new FileWriter(fichero,true); //valor true agregara los datos nuevos
	    escri.write("<p>"+palabro+"</p>\r\n");// se guarda el nombre en el archivo de texto con formato html
	    escri.close();
		}
		catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String palabra;
		System.out.println("ingrese la palabra");
		palabra= in.next();
		String corti =corta(palabra);
		try {
			escritor(corti);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
