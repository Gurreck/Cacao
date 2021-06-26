package org.una.cacao_cliente.clases;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import org.una.cacao_cliente.controllers.JuegoController;
import org.una.cacao_cliente.controllers.MenuController;
import org.una.cacao_cliente.controllers.SalaEsperaController;
import org.una.cacao_cliente.utility.FlowController;
import org.una.cacao_cliente.utility.Mensaje;
/**
 *
 * @author Esteban Vargas
 */
public class Comunicacion {
    
    private final Socket s;
    // obtaining input and out streams
    private final DataInputStream dis;
    private final DataOutputStream dos;
    Mensaje msg = new Mensaje();
    private final Gson gson;

    public Comunicacion(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.gson = new Gson();
    }

    public Socket getS() {
        return s;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public Gson getGson() {
        return gson;
    }
    
    public void enviarMensajeServidor(Transferencia tranferencia){
        try{
            if(tranferencia.getOperacion().equals("salir")){  
                Globales.getInstance().escucharServidor = true;
                
                dos.writeUTF(gson.toJson(tranferencia));

                System.out.println("Closing this connection : " + s);
                s.close();
                System.out.println("Connection closed");

                dis.close();
                dos.close();

            }
            else{
                dos.writeUTF(gson.toJson(tranferencia));
            }
           
        }catch(Exception e){
            System.out.println("Error en la tranferencia de datos al servidor");
        }
    }
    
    public void escucharServidor(){
    
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Globales.getInstance().escucharServidor){
                    try {
                        String mensaje = dis.readUTF();
                        Transferencia t = gson.fromJson(mensaje, Transferencia.class);   
                        System.out.println("Recibido: " + mensaje + "|");
                        Globales.getInstance().partida = t.getPartida();
                        ControlVistas(t);
                    } catch (IOException ex) {
                        System.out.println("Error al recibir datos del servidor");
                    }
                }
            }
        });
        thread.start();
    }
    
    public void ControlVistas(Transferencia t){
        Platform.runLater(new Runnable() {
        @Override public void run() {
        
        if(t.getOperacion().equals("Ir SalaEspera")){
          
            MenuController MenuController = (MenuController) FlowController.getInstance().getController("Menu");
            FlowController.getInstance().goViewInStage("SalaEspera",MenuController.getStage());
            SalaEsperaController SalaEsperaController = (SalaEsperaController) FlowController.getInstance().getController("SalaEspera");
            SalaEsperaController.ActualizarVista();
        }
        else if(t.getOperacion().equals("Ir Juego")){ 

            SalaEsperaController SalaEsperaController = (SalaEsperaController) FlowController.getInstance().getController("SalaEspera");
            FlowController.getInstance().goViewInStage("Juego",SalaEsperaController.getStage());
            JuegoController JuegoController = (JuegoController) FlowController.getInstance().getController("Juego");
            JuegoController.ActualizarVista();
        }
        else if(t.getOperacion().equals("Actualizar SalaEspera")){

            SalaEsperaController SalaEsperaController = (SalaEsperaController) FlowController.getInstance().getController("SalaEspera");
            SalaEsperaController.ActualizarVista();
        }
        else if(t.getOperacion().equals("Actualizar Juego")){ 

            JuegoController JuegoController = (JuegoController) FlowController.getInstance().getController("Juego");
            JuegoController.ActualizarVista();
        }
        else if(t.getOperacion().equals("Faltan AceptarPartida")){ 
            SalaEsperaController SalaEsperaController = (SalaEsperaController) FlowController.getInstance().getController("SalaEspera");
            msg.alerta((StackPane)(SalaEsperaController.getRoot()), "Alerta", "Faltan jugadores por aceptar la partida, por favor espere");
            System.out.println(t.getOperacion());
        }
        else if(t.getOperacion().equals("Color Ocupado")){ 
            MenuController MenuController = (MenuController) FlowController.getInstance().getController("Menu");
            msg.alerta((StackPane)(MenuController.getRoot()), "Alerta", "El color seleccionado se encuentra ocupado, por favor escoja otro");
            System.out.println(t.getOperacion());
        }
       }
       });
        
    }
}
