package es.pgv.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor {
	
	private static ArrayList<Aerogenerador> aerogeneradores = new ArrayList<>();
	
    public static void main(String[] args) {
    	
    	try  {
    		 
    		@SuppressWarnings("resource")
        		ServerSocket serverSocket = new ServerSocket(40080); 
        	
        	System.out.println("Servidor esperando conexiones...");

        	  while(true){
        		  Socket socket = serverSocket.accept();
        		  System.out.println("Cliente conectado.");
        	      
        	      HiloParqueEolico hilo = new HiloParqueEolico(socket, aerogeneradores);
				  hilo.start();
			  
              }
    	  } catch (IOException ex) {
              Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}