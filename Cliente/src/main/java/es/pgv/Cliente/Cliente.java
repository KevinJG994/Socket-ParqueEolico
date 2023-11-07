package es.pgv.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
	    
        try {    	
            Socket socket = new Socket("localhost", 40080);
            System.out.println("Conexión establecida con el servidor.");
            
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            
            int opcion;
            do {
                // Mostrar el menú y obtener la opción del usuario
                System.out.println("Menú");
                System.out.println("<1> Relación de aerogeneradores");
                System.out.println("<2> Activar aerogenerador");
                System.out.println("<3> Desactivar aerogenerador");
                System.out.println("<4> Añadir aerogenerador");
                System.out.println("<5> Eliminar aerogenerador");
                System.out.println("<0> Salir \n");
                System.out.print("Ingrese su opción: ");
                
                opcion = scanner.nextInt();
                salida.println(opcion);

                switch (opcion) {
                    case 2:
                    	System.out.print("Ingrese el ID del aerogenerador: ");
                        int activarAeroGenerador = scanner.nextInt();
                        salida.println(activarAeroGenerador);
                        break;
                    case 3:
                    	System.out.print("Ingrese el ID del aerogenerador: ");
                        int desactivarAeroGenerador = scanner.nextInt();
                        salida.println(desactivarAeroGenerador);
                        break;
                    case 5:
                        // Pedir al usuario que ingrese el ID del aerogenerador
                    	System.out.print("Ingrese el ID del aerogenerador: ");
                        int eliminarAerogenerador = scanner.nextInt();
                        salida.println(eliminarAerogenerador);
                        break;
                
                }
                // Recibir y mostrar la respuesta del servidor
                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta+ "\n");
                
               
            } while (opcion != 0);

            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
}
}