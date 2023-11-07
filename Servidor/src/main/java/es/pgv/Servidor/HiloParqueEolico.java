package es.pgv.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HiloParqueEolico extends Thread {
    private Socket sk;
    private ArrayList<Aerogenerador> aerogeneradores;

    public HiloParqueEolico(Socket sk, ArrayList<Aerogenerador> aerogeneradores) {
        this.sk = sk;
        this.aerogeneradores = aerogeneradores;
        
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(sk.getOutputStream(), true);

            String opcion;
            while ((opcion = entrada.readLine()) != null) {
                String respuesta = "";

                // Procesar la opción del cliente y generar la respuesta
                switch (opcion) {
                    case "1":
                    	    if (aerogeneradores.isEmpty()) {
                    	        respuesta = "No hay aerogeneradores.";
                    	    } else {
                    	        for (Aerogenerador aero : aerogeneradores) {
                    	                           	        
                    	        respuesta = aero.toString();
                    	    }
                    	    
                    	    }
                        break;
                        
                    case "2":
                        
                        int activarAeroGenerador = Integer.parseInt(entrada.readLine());
                        if (activarAeroGenerador >= 0 && activarAeroGenerador < aerogeneradores.size()) {
                            aerogeneradores.get(activarAeroGenerador).setEstado(true);
                            respuesta = "Aerogenerador activado.";
                        } else {
                            respuesta = "ID de aerogenerador no válido.";
                        }
                        break;

                    case "3":
                       
                        int desactivarAeroGenerador = Integer.parseInt(entrada.readLine());
                        if (desactivarAeroGenerador >= 0 && desactivarAeroGenerador < aerogeneradores.size()) {
                            aerogeneradores.get(desactivarAeroGenerador).setEstado(false);
                            respuesta = "Aerogenerador desactivado.";
                        } else {
                            respuesta = "ID de aerogenerador no válido.";
                        }
                        break;

                    case "4":
                        Aerogenerador nuevoAerogenerador = new Aerogenerador(aerogeneradores.size(), false, 0.0);
                        aerogeneradores.add(nuevoAerogenerador);
                        respuesta = "Aerogenerador añadido con ID: " + nuevoAerogenerador.getId();
                        break;

                    case "5":
                       
                        int eliminarAerogenerador = Integer.parseInt(entrada.readLine());
                        if (eliminarAerogenerador >= 0 && eliminarAerogenerador < aerogeneradores.size()) {
                            aerogeneradores.remove(eliminarAerogenerador);
                            respuesta = "Aerogenerador eliminado.";
                        } else {
                            respuesta = "ID de aerogenerador no válido.";
                        }
                        break;

                    case "0":
                        respuesta = "Desconectado."; // Desconectar el cliente del servidor
                        sk.close();          
                        break;
                        
                    default:
                        respuesta = "Opción no válida. Inténtalo de nuevo.";
                }

                // Enviar la respuesta al cliente
                salida.println(respuesta);

                // Si el cliente solicitó desconectar, salir del bucle
           
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}