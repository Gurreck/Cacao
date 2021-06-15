
package org.una.cacao_servidor;
import com.google.gson.Gson;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import org.una.cacao_servidor.clases.Globales;


// Server class
public class servidor implements Serializable
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Globales.getInstance().listaSockets.add(s);

                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                    s.close();
                    e.printStackTrace();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;      //Dato que envia
    final DataOutputStream dos;     //Dato que recibe
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {       
        Gson gson = new Gson(); // Objeto para serializar
        String received;
        String toreturn;
        
        while (true)
        {
            try {

                // Ask user what he wants
		//		dos.writeUTF("What do you want?[Date | Time]..\n"+
		//					"Type Exit to terminate connection.");
                
                Globales.getInstance().transferencia.Transferencia("Conexión establecida correctamente");
                dos.writeUTF(gson.toJson(Globales.getInstance().transferencia));
                
                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Exit"))
                {
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        break;
                }

                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                System.out.println("Recibido: " + received);
                switch (received) {

                        case "Date" :                                
                                //Serializar, Objeto-Json
                                Globales.getInstance().transferencia.Transferencia("PRUEBA");
                                toreturn = gson.toJson(Globales.getInstance().transferencia);
                                
                                dos.writeUTF(toreturn);
                                
                                break;

                        case "Time" :
                                toreturn = fortime.format(date);
                                for (Socket sock : Globales.getInstance().listaSockets){
                                    DataOutputStream a = new DataOutputStream(sock.getOutputStream());
                                    a.writeUTF(toreturn);
                                }
                                //dos.writeUTF(toreturn);
                                break;

                        default:
                                dos.writeUTF("Invalid input");
                                break;
                }
            } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        try
        {
                // closing resources
                this.dis.close();
                this.dos.close();

        }catch(IOException e){
                e.printStackTrace();
            }
    }
}
