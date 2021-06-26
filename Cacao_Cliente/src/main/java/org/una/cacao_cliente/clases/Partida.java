package org.una.cacao_cliente.clases;

import java.util.List;
import java.util.Collections;
/**
 *
 * @author Esteban Vargas
 */
public class Partida {
    
    private List<Jugadores> jugadores;
    private List<Losetas> baraja_losetasSelva;
    private Losetas[][] tablero;
    private String turno;

    public Partida() {
    }

    public Partida(List<Jugadores> jugadores, List<Losetas> baraja_losetasSelva, Losetas[][] tablero, String turno) {
        this.jugadores = jugadores;
        this.baraja_losetasSelva = baraja_losetasSelva;
        this.tablero = tablero;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    public void setBaraja_losetasSelva(List<Losetas> baraja_losetasSelva) {
        this.baraja_losetasSelva = baraja_losetasSelva;
    }

    public void setTablero(Losetas[][] tablero) {
        this.tablero = tablero;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    public List<Jugadores> getJugadores() {
        return jugadores;
    }

    public List<Losetas> getBaraja_losetasSelva() {
        return baraja_losetasSelva;
    }

    public Losetas[][] getTablero() {
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
            Losetas Recolector1 = new Losetas("Recolector1","Recolector",jugadores.get(pos).getColor(),0,1,1,1,1);
            jugadores.get(pos).getLosetasRecolectores().add(Recolector1);
        }
        //Crea las losetas de 2-1-0-1
        for ( int i = 0 ; i < 5 ; i++ ){
            Losetas Recolector2 = new Losetas("Recolector2","Recolector",jugadores.get(pos).getColor(),0,1,1,0,2);
            jugadores.get(pos).getLosetasRecolectores().add(Recolector2);
        }
        
        //Crea las losetas de 3-0-0-1
        Losetas Recolector3 = new Losetas("Recolector3","Recolector",jugadores.get(pos).getColor(),0,1,0,0,3);
        jugadores.get(pos).getLosetasRecolectores().add(Recolector3);
        
        //Crea las losetas de 3-1-0-0
        Losetas Recolector4 = new Losetas("Recolector4","Recolector",jugadores.get(pos).getColor(),0,0,1,0,3);
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
        for (Losetas obj : jugadores.get(pos).getLosetasRecolectores()) {
                
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
                Losetas Cacao1 = new Losetas("Cacao1","Selva");
                if(i == 0){
                    tablero[12][12] = Cacao1;
                }else{
                    baraja_losetasSelva.add(Cacao1);
                }
            }
            else { //2 losetas de 2 cacaos
                Losetas Cacao2 = new Losetas("Cacao2","Selva");
                baraja_losetasSelva.add(Cacao2);
            }
        }
        
        //Crea las losetas de mercado
        for ( int i = 0 ; i < 7 ; i++ ){
            if(i < 2) { // 2 Losetas de 2 monedas 
                Losetas Mercado2 = new Losetas("Mercado2","Selva");
                if(i == 1){
                     int valorDado = (int) Math.floor(Math.random()*4);
                    switch (valorDado) {
                        case 0:
                            tablero[11][11] = Mercado2; break;
                        case 1:
                            tablero[11][13] = Mercado2; break;
                        case 2:
                            tablero[13][11] = Mercado2; break;
                        default:
                            tablero[13][13] = Mercado2; break;
                    }
                }
                else{
                    baraja_losetasSelva.add(Mercado2);
                }
                
                
            }
            else if(i < 6) {  //4 losetas de 3 monedas
                Losetas Mercado3 = new Losetas("Mercado3","Selva");
                baraja_losetasSelva.add(Mercado3);
            }
            else { //1 loseta de 4 monedas
                Losetas Mercado4 = new Losetas("Mercado4","Selva");
                baraja_losetasSelva.add(Mercado4);
            }
        }
        
        //Crea las losetas de minas
        for ( int i = 0 ; i < 3 ; i++ ){
            if(i < 2) {  //2 losetas de valor 1
                Losetas Mina1 = new Losetas("Mina1","Selva");
                baraja_losetasSelva.add(Mina1);
            }
            else { //1 loseta de valor 2
                Losetas Mina2 = new Losetas("Mina2","Selva");
                baraja_losetasSelva.add(Mina2);
            }
        }
        
        //Crea las losetas de agua
        for ( int i = 0 ; i < 3 ; i++ ){
            Losetas Agua = new Losetas("Agua","Selva");
            baraja_losetasSelva.add(Agua);
        }
        
        //Crea las losetas solares
        for ( int i = 0 ; i < 2 ; i++ ){
            Losetas Solar = new Losetas("Solar","Selva");
            baraja_losetasSelva.add(Solar);
        }
        
        //Crea las losetas de los templos
        for ( int i = 0 ; i < 5 ; i++ ){
            Losetas Templo = new Losetas("Templo","Selva");
            baraja_losetasSelva.add(Templo);
        }
        
        //Si son 2 jugadores, retira las cartas correspondientes
        if (CantJugadores == 2){
            QuitaLosetasSelva();
        }
        
        int i = 0;
        for (Losetas obj : baraja_losetasSelva) {
                
          System.out.println(obj.getTipo() +" || "+ i);
          i +=1;
        }
        //Revuelve la baraja de losetas de selva
        Collections.shuffle(baraja_losetasSelva);
        
        //Imprimir lista de objetos
        /*int i = 0;
        for (Losetas obj : baraja_losetasSelva) {
                
          System.out.println(obj.getTipo() +" || "+ i);
          i +=1;
        }*/
    }
    
    private void QuitaLosetasSelva(){
        baraja_losetasSelva.remove(0); //Remueve 1 plantacion simple
        baraja_losetasSelva.remove(0); //Remueve 1 plantacion simple
        baraja_losetasSelva.remove(7); //Remueve 1 mercado de precio de venta 3
        baraja_losetasSelva.remove(10); //Remueve 1 mina de valor 1
        baraja_losetasSelva.remove(12); //Remueve 1 cenote
        baraja_losetasSelva.remove(14); //Remueve 1 centro de culto solar
        baraja_losetasSelva.remove(15); //Remueve 1 templo
    }
    
    public void validarLosetaRecolector(Jugadores j, int fila, int col){
        Losetas loseta = tablero[fila][col];
        
        for(int i = 0; i < jugadores.size();i++){
            if(jugadores.get(i).getColor().equals(j.getColor())){
                j = jugadores.get(i);
                break;
            }
        }
        
        if(tablero[fila-1][col] != null){    //arriba
            EvaluarColocarLosetaPos(j, fila-1, col,loseta.getArriba());
        }
        if(tablero[fila+1][col] != null){    //abajo
            EvaluarColocarLosetaPos(j, fila+1, col, loseta.getAbajo());
        }
        if(tablero[fila][col-1] != null){    //Izquierda
            EvaluarColocarLosetaPos(j, fila, col-1, loseta.getIzquierda());
        }
        if(tablero[fila][col+1] != null){ // Derecha
            EvaluarColocarLosetaPos(j, fila, col+1, loseta.getDerecha());
        }
    }
    
    private void EvaluarColocarLosetaPos(Jugadores jug, int i, int j, int cant){
        for(int m=0;m<cant;m++){
            if(tablero[i][j+1].getTipo().equals("Cacao1")){
                if(jug.getCacaos()<5){
                    jug.setCacaos(jug.getCacaos()+1);
                }
            }
            else if(tablero[i][j+1].getTipo().equals("Cacao2")){
                if(jug.getCacaos()<4){
                    jug.setCacaos(jug.getCacaos()+2);
                }else if(jug.getCacaos()<5){
                    jug.setCacaos(jug.getCacaos()+1);
                }
            }
            else if(tablero[i][j+1].getTipo().equals("Mercado2")){
                if(jug.getCacaos()>1){
                   jug.setCacaos(jug.getCacaos()-2);
                   jug.setMonedas(jug.getMonedas()+2);
                }
                else if(jug.getCacaos()==1){
                    jug.setCacaos(jug.getCacaos()-1);
                   jug.setMonedas(jug.getMonedas()+1);
                }
            }
            else if(tablero[i][j+1].getTipo().equals("Mercado3")){
                if(jug.getCacaos()>2){
                   jug.setCacaos(jug.getCacaos()-3);
                   jug.setMonedas(jug.getMonedas()+3);
                }
                else if(jug.getCacaos()==2){
                    jug.setCacaos(jug.getCacaos()-2);
                   jug.setMonedas(jug.getMonedas()+2);
                }
                else if(jug.getCacaos()==1){
                    jug.setCacaos(jug.getCacaos()-1);
                   jug.setMonedas(jug.getMonedas()+1);
                }
            }
            else if(tablero[i][j+1].getTipo().equals("Mercado4")){
                if(jug.getCacaos()>3){
                   jug.setCacaos(jug.getCacaos()-4);
                   jug.setMonedas(jug.getMonedas()+4);
                }
                else if(jug.getCacaos()==3){
                   jug.setCacaos(jug.getCacaos()-3);
                   jug.setMonedas(jug.getMonedas()+3);
                }
                else if(jug.getCacaos()==2){
                    jug.setCacaos(jug.getCacaos()-2);
                   jug.setMonedas(jug.getMonedas()+2);
                }
                else if(jug.getCacaos()==1){
                    jug.setCacaos(jug.getCacaos()-1);
                   jug.setMonedas(jug.getMonedas()+1);
                }
            }
            else if(tablero[i][j+1].getTipo().equals("Mina1")){
                jug.setMonedas(jug.getMonedas()+1);
            }
            else if(tablero[i][j+1].getTipo().equals("Mina2")){
                jug.setMonedas(jug.getMonedas()+2);
            }
            else if(tablero[i][j+1].getTipo().equals("Agua")){
                if(jug.getPuntosRio()<17){
                    jug.setPuntosRio(jug.getPuntosRio()+4);
                }
            }
            else if(tablero[i][j+1].getTipo().equals("Solar")){
               /* if(jug.getSolares()<3){
                    jug.setSolares(jug.getSolares()+1);
                }*/
            }
        }
        
    }
}
