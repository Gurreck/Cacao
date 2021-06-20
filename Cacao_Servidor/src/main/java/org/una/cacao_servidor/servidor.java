
package org.una.cacao_servidor;
import com.google.gson.Gson;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import org.una.cacao_servidor.clases.Globales;
import org.una.cacao_servidor.clases.Jugadores;
import org.una.cacao_servidor.clases.Partida;
import org.una.cacao_servidor.clases.Transferencia;


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

                System.out.println("Nuevo cliente conectado : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Asignando nuevo hilo para este cliente");

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
    final DataInputStream dis;      //Dato que envia
    final DataOutputStream dos;     //Dato que recibe
    final Socket s;
    Transferencia datos;
    Gson gson;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
    
    public void actualizarTodos(String operacion) throws IOException{
        
        Transferencia t = new Transferencia(operacion, new ArrayList<>(), Globales.getInstance().partida);
        System.out.println(gson.toJson(t)); 
        for (Socket sock : Globales.getInstance().listaSockets){
            DataOutputStream a = new DataOutputStream(sock.getOutputStream());
            a.writeUTF(gson.toJson(t));
        }
    }
    
    @Override
    public void run()
    {       
        gson = new Gson(); // Objeto para serializar
        String received;
        String toreturn;
        
        while (true)
        {
            try {

                if(Globales.getInstance().partida == null){
                    
                   Globales.getInstance().partida = new Partida();
                   Globales.getInstance().partida.setJugadores(new ArrayList<Jugadores>());
                   
                }
                else if(Globales.getInstance().partida.getJugadores().size() > 4){
                    Transferencia t = new Transferencia("Servidor Lleno", new ArrayList<>() , null);
                    toreturn = gson.toJson(t);

                    dos.writeUTF(toreturn);
                    this.s.close();
                    break;
                }
                
                // receive the answer from client
                received = dis.readUTF();
                datos = gson.fromJson(received, Transferencia.class);
                System.out.println(received);
                
                if(datos.getOperacion().equals("salir"))
                {
                    //FALTAN EVALUAR CASO EN LOS QUE EL JUGADOR ABANDONA EL JUEGO
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    Globales.getInstance().listaSockets.remove(s);
                    this.s.close();
                    System.out.println("Connection closed");
                    //actualizarTodos();
                    break;
                }
                else if(datos.getOperacion().equals("login")){
                    Jugadores jugador = gson.fromJson(gson.toJson(datos.getDatosOperacion().get(0)), Jugadores.class);
                    boolean band = false;
                    for(int i = 0; i < Globales.getInstance().partida.getJugadores().size();i++){
                        if(Globales.getInstance().partida.getJugadores().get(i).getColor().equals(jugador.getColor())){
                           band = true;
                           break;
                        }
                    }
                    if(band){
                        Transferencia t = new Transferencia("Color Ocupado", new ArrayList<>() , null);
                        toreturn = gson.toJson(t);

                        dos.writeUTF(toreturn);
                    }
                    else{
                        Globales.getInstance().partida.getJugadores().add(jugador);
                        Transferencia t = new Transferencia("Ir SalaEspera", new ArrayList<>() , null);
                        toreturn = gson.toJson(t);
                        
                        dos.writeUTF(toreturn);
                        actualizarTodos("Actualizar SalaEspera");
                    }
                    
                    
                }
                else if(datos.getOperacion().equals("aceptarPartida")){
                    Jugadores jugador = gson.fromJson(gson.toJson(datos.getDatosOperacion().get(0)), Jugadores.class);
                    
                    for(int i = 0; i < Globales.getInstance().partida.getJugadores().size();i++){
                        if(Globales.getInstance().partida.getJugadores().get(i).getColor().equals(jugador.getColor())){
                            if(Globales.getInstance().partida.getJugadores().get(i).isAceptarPartida()){
                                Globales.getInstance().partida.getJugadores().get(i).setAceptarPartida(false);
                            }else{
                                Globales.getInstance().partida.getJugadores().get(i).setAceptarPartida(true);
                            }
                            break;
                        }
                    }
                    
                    actualizarTodos("Actualizar SalaEspera");
                }
                else if(datos.getOperacion().equals("iniciarPartida")){
                    boolean resultado = Globales.getInstance().partida.verificarIniciarPartida();
                    if(resultado){
                        actualizarTodos("Ir Juego");
                    }
                    else{
                        Transferencia t = new Transferencia("Faltan AceptarPartida", new ArrayList<>() , Globales.getInstance().partida);
                        toreturn = gson.toJson(t);

                        dos.writeUTF(toreturn);
                    }
                }
                else{
                    Transferencia t = new Transferencia("Error Peticion", new ArrayList<>(), Globales.getInstance().partida);
                    toreturn = gson.toJson(t);

                    dos.writeUTF(toreturn);
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
