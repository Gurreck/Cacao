// Java implementation for a client
// Save file as Client.java

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.una.cacao_servidor.clases.Globales;
import org.una.cacao_servidor.clases.Transferencia;
// Client class
public class Client
{
	public static void main(String[] args) throws IOException
	{
		try
		{       
                        Gson gson = new Gson();
			Scanner scn = new Scanner(System.in);
			
			// getting localhost ip
			InetAddress ip = InetAddress.getByName("localhost");
	
			// establish the connection with server port 5056
			Socket s = new Socket(ip, 5056);
	
			// obtaining input and out streams
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	
			// the following loop performs the exchange of
			// information between client and client handler
			while (true)
			{       
				//Deserializar, Json-Objeto
                                Globales.getInstance().transferencia = gson.fromJson(dis.readUTF(), Transferencia.class);
				System.out.println("Recibido: " + Globales.getInstance().transferencia.getOperacion() + "|");
                                
                                System.out.println("Digite un dato");
				String tosend = scn.nextLine();
				dos.writeUTF(tosend);
				
				// If client sends exit,close this connection
				// and then break from the while loop
				if(tosend.equals("Exit"))
				{
					System.out.println("Closing this connection : " + s);
					s.close();
					System.out.println("Connection closed");
					break;
				}
				
				// printing date or time as requested by client
				String received = dis.readUTF();
				System.out.println(received);
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
