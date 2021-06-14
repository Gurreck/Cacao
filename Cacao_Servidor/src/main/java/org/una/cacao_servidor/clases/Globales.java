package org.una.cacao_servidor.clases;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adria
 */
public class Globales {
    
    private static Globales INSTANCE = null;
    public Transferencia transferencia = new Transferencia();
    public List<Socket> listaSockets = new ArrayList<>();
    
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (Globales.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Globales();
                }
            }
        }
    }

    public static Globales getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
}
