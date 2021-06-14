package org.una.cacao_servidor.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esteban Vargas
 */
public class Partida {
    
    private List<Jugadores> jugadores;
    private List<LosetasSelva> baraja_losetasSelva;
    private ArrayList<ArrayList> tablero;
    private String turno;

    public void Partida() {
    }

    public void Partida(List<Jugadores> jugadores, List<LosetasSelva> baraja_losetasSelva, ArrayList<ArrayList> tablero, String turno) {
        this.jugadores = jugadores;
        this.baraja_losetasSelva = baraja_losetasSelva;
        this.tablero = tablero;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    public void setBaraja_losetasSelva(List<LosetasSelva> baraja_losetasSelva) {
        this.baraja_losetasSelva = baraja_losetasSelva;
    }

    public void setTablero(ArrayList<ArrayList> tablero) {
        this.tablero = tablero;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    public List<Jugadores> getJugadores() {
        return jugadores;
    }

    public List<LosetasSelva> getBaraja_losetasSelva() {
        return baraja_losetasSelva;
    }

    public ArrayList<ArrayList> getTablero() {
        return tablero;
    }

    public String getTurno() {
        return turno;
    }

}
