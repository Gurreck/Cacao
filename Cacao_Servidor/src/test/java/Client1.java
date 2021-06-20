// Java implementation for a client
// Save file as Client.java

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.una.cacao_servidor.clases.Globales;
import org.una.cacao_servidor.clases.Jugadores;
import org.una.cacao_servidor.clases.Transferencia;
// Client class
public class Client1
{
	public static void main(String[] args) throws IOException
	{
		try
		{       
                        Gson gson = new Gson();
                        Jugadores jugador = new Jugadores();
			Scanner scn = new Scanner(System.in);
			
			// getting localhost ip
			InetAddress ip = InetAddress.getByName("localhost");
                        Globales.getInstance().fd = false;
			// establish the connection with server port 5056
			Socket s = new Socket(ip, 5056);
	
			// obtaining input and out streams
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while(!Globales.getInstance().fd){
                                    try {
                                        System.out.println("Recibido: " + dis.readUTF() + "|");
                                    } catch (IOException ex) {
                                        System.out.println("Error al recibir datos");
                                    }
                                }
                            }
                        });
                        thread.start();
			// the following loop performs the exchange of
			// information between client and client handler
			while (true)
			{       
                                
                                System.out.println("Digite un dato");
				String tosend = scn.nextLine();
				
				
				// If client sends exit,close this connection
				// and then break from the while loop
				if(tosend.equals("salir"))
				{  
                                        Globales.getInstance().fd = true;
                                        Transferencia t = new Transferencia(tosend, new ArrayList<>(), null);
                                        dos.writeUTF(gson.toJson(t));
                                        

					System.out.println("Closing this connection : " + s);
					s.close();
					System.out.println("Connection closed");
                                        
					break;
				}
                                else if(tosend.equals("login")){
                                    jugador = new Jugadores("Esteban", null, "azul", new ArrayList<>(), 0, 0, -16, false);
                                    
                                    List<Object> lstObject = new ArrayList<Object>();
                                    lstObject.add(jugador);
                                    
                                    Transferencia t = new Transferencia();
                                    t.setOperacion(tosend);
                                    t.setDatosOperacion(lstObject);
                                    dos.writeUTF(gson.toJson(t));
                                }
                                else if(tosend.equals("aceptarPartida")){
                                    List<Object> lstObject = new ArrayList<Object>();
                                    lstObject.add(jugador);
                                    
                                    Transferencia t = new Transferencia(tosend, lstObject, null);
                                    
                                    dos.writeUTF(gson.toJson(t));
                                }
                                else if(tosend.equals("iniciarPartida")){
                                    Transferencia t = new Transferencia(tosend, new ArrayList<>(), null);
                                    dos.writeUTF(gson.toJson(t));
                                }
				
			}
			
			// closing resources
			scn.close();
			dis.close();
			dos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
