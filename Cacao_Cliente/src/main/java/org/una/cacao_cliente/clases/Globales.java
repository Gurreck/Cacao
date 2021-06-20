package org.una.cacao_cliente.clases;

/**
 *
 * @author adria
 */
public class Globales {
    
    private static Globales INSTANCE = null;
  
    public Partida partida;
    public boolean escucharServidor;
    public Comunicacion comunicacion;
    public Jugadores jugador;
    
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
