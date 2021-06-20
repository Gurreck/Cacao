package org.una.cacao_cliente.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 *
 * @author Esteban Vargas
 */
public class Partida {
    
    private List<Jugadores> jugadores;
    private List<LosetasSelva> baraja_losetasSelva;
    private ArrayList<ArrayList> tablero;
    private String turno;

    public Partida() {
        baraja_losetasSelva = new ArrayList<>();
    }

    public Partida(List<Jugadores> jugadores, List<LosetasSelva> baraja_losetasSelva, ArrayList<ArrayList> tablero, String turno) {
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
    
    public boolean verificarIniciarPartida(){
        boolean band = false;
        
        for(Jugadores j : jugadores){
            if(!j.isAceptarPartida()){
                band = true;
                break;
            }
        }
        if(jugadores.size() < 2){
            return false;
        }
        else if(band){
            return false;
        }
        else{
            System.out.println(jugadores.size());
            for(int i = 0; i<jugadores.size(); i++){
                CrearLosetasRecolectores(i, jugadores.size());
            }
            
            CrearLosetasSelva(jugadores.size());
            return true;
        }
    }
    
    public void CrearLosetasRecolectores(int pos, int CantJugadores){
        
        //Crea las losetas de 1-1-1-1
        for ( int i = 0 ; i < 4 ; i++ ){
            LosetasRecolectores Recolector1 = new LosetasRecolectores("Recolector1",jugadores.get(pos).getColor(),1,1,1,1);
            jugadores.get(pos).getLosetasRecolectores().add(Recolector1);
        }
        //Crea las losetas de 2-1-0-1
        for ( int i = 0 ; i < 5 ; i++ ){
            LosetasRecolectores Recolector2 = new LosetasRecolectores("Recolector2",jugadores.get(pos).getColor(),1,1,0,2);
            jugadores.get(pos).getLosetasRecolectores().add(Recolector2);
        }
        
        //Crea las losetas de 3-0-0-1
        LosetasRecolectores Recolector3 = new LosetasRecolectores("Recolector3",jugadores.get(pos).getColor(),1,0,0,3);
        jugadores.get(pos).getLosetasRecolectores().add(Recolector3);
        
        //Crea las losetas de 3-1-0-0
        LosetasRecolectores Recolector4 = new LosetasRecolectores("Recolector4",jugadores.get(pos).getColor(),0,1,0,3);
        jugadores.get(pos).getLosetasRecolectores().add(Recolector4);
        
        if(CantJugadores == 3){
            jugadores.get(pos).getLosetasRecolectores().remove(0);
        }
        else if(CantJugadores == 4){
            jugadores.get(pos).getLosetasRecolectores().remove(0);
            jugadores.get(pos).getLosetasRecolectores().remove(3);
        }
        
        //Revuelve la baraja de losetas de selva
        Collections.shuffle(jugadores.get(pos).getLosetasRecolectores());
        
        //Imprimir lista de objetos
        /*int i = 0;
        for (LosetasRecolectores obj : jugadores.get(pos).getLosetasRecolectores()) {
                
          System.out.println(obj.getTipo() +" || "+ i);
          i +=1;
        }*/
    }
    
    public void CrearLosetasSelva(int CantJugadores){
        
        //SACAR UNA PLANTACION DE CACAO SIMPLE Y PONERLA EN EL TABLERO
        //SACAR UN MERCADO DE VALOR 2 Y PONERLO EN EL TABLERO
        
        //Crea las losetas de cacao
        for ( int i = 0 ; i < 8 ; i++ ){
            
            if(i < 6){ //6 losetas de 1 cacao
                LosetasSelva Cacao1 = new LosetasSelva("Cacao1");
                baraja_losetasSelva.add(Cacao1);
            }
            else { //2 losetas de 2 cacaos
                LosetasSelva Cacao2 = new LosetasSelva("Cacao2");
                baraja_losetasSelva.add(Cacao2);
            }
        }
        
        //Crea las losetas de mercado
        for ( int i = 0 ; i < 7 ; i++ ){
            if(i < 2) { // 2 Losetas de 2 monedas 
                LosetasSelva Mercado2 = new LosetasSelva("Mercado2");
                baraja_losetasSelva.add(Mercado2);
            }
            else if(i < 6) {  //4 losetas de 3 monedas
                LosetasSelva Mercado3 = new LosetasSelva("Mercado3");
                baraja_losetasSelva.add(Mercado3);
            }
            else { //1 loseta de 4 monedas
                LosetasSelva Mercado4 = new LosetasSelva("Mercado4");
                baraja_losetasSelva.add(Mercado4);
            }
        }
        
        //Crea las losetas de minas
        for ( int i = 0 ; i < 3 ; i++ ){
            if(i < 2) {  //2 losetas de valor 1
                LosetasSelva Mina1 = new LosetasSelva("Mina1");
                baraja_losetasSelva.add(Mina1);
            }
            else { //1 loseta de valor 2
                LosetasSelva Mina2 = new LosetasSelva("Mina2");
                baraja_losetasSelva.add(Mina2);
            }
        }
        
        //Crea las losetas de agua
        for ( int i = 0 ; i < 3 ; i++ ){
            LosetasSelva Agua = new LosetasSelva("Agua");
            baraja_losetasSelva.add(Agua);
        }
        
        //Crea las losetas solares
        for ( int i = 0 ; i < 2 ; i++ ){
            LosetasSelva Solar = new LosetasSelva("Solar");
            baraja_losetasSelva.add(Solar);
        }
        
        //Crea las losetas de los templos
        for ( int i = 0 ; i < 5 ; i++ ){
            LosetasSelva Templo = new LosetasSelva("Templo");
            baraja_losetasSelva.add(Templo);
        }
        
        //Si son 2 jugadores, retira las cartas correspondientes
        if (CantJugadores == 2){
            QuitaLosetasSelva();
        }

        //Revuelve la baraja de losetas de selva
        Collections.shuffle(baraja_losetasSelva);
        
        //Imprimir lista de objetos
        /*int i = 0;
        for (LosetasSelva obj : baraja_losetasSelva) {
                
          System.out.println(obj.getTipo() +" || "+ i);
          i +=1;
        }*/
    }
    
    private void QuitaLosetasSelva(){
        baraja_losetasSelva.remove(0); //Remueve 1 plantacion simple
        baraja_losetasSelva.remove(0); //Remueve 1 plantacion simple
        baraja_losetasSelva.remove(9); //Remueve 1 mercado de precio de venta 3
        baraja_losetasSelva.remove(12); //Remueve 1 mina de valor 1
        baraja_losetasSelva.remove(14); //Remueve 1 cenote
        baraja_losetasSelva.remove(16); //Remueve 1 centro de culto solar
        baraja_losetasSelva.remove(17); //Remueve 1 templo
    }
}
