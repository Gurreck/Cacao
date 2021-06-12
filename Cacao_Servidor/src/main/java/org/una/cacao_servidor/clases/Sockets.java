package org.una.cacao_servidor.clases;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Sockets{
    
    private static Sockets INSTANCE = null;
    public static List<Socket> listaSockets = new ArrayList<Socket>();
    
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (Sockets.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Sockets();
                }
            }
        }
    }

    public static Sockets getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
}