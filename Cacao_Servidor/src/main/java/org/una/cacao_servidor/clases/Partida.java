package org.una.cacao_servidor.clases;

import java.util.ArrayList;
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
    private boolean iniciado;
    private String ganador;

    public Partida() {
    }

    public Partida(List<Jugadores> jugadores, List<Losetas> baraja_losetasSelva, Losetas[][] tablero, String turno) {
        this.jugadores = jugadores;
        this.baraja_losetasSelva = baraja_losetasSelva;
        this.tablero = tablero;
        this.iniciado = false;
        this.ganador = "";
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
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

    public boolean getIniciado() {
        return iniciado;
    }
    
    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
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
            
            //Ordena el arreglo segun la fecha de nacimiento de los jugadores, para establecer el orden incial
            jugadores.sort((d1,d2) -> d1.getFechaNacimiento().compareTo(d2.getFechaNacimiento()));
            turno = jugadores.get(0).getColor();
            iniciado = true;
            return true;
        }
    }
    public void PasarTurno(){
                       
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getColor().equals(turno)) {
                if (i+1 < jugadores.size()) {
                    turno = jugadores.get(i+1).getColor();
                }
                else{
                    turno = jugadores.get(0).getColor();
                }                
                break;
            }
        }
        
    }
    
    public boolean QuedanLosetasRecolector() { //True si quedan cartas de recolectores por colocar
        boolean QuedanCartas = true;
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getLosetasRecolectores().isEmpty()) {
                QuedanCartas = false;
            }
            else {
                QuedanCartas = true;
                break;
            }
        }
        return QuedanCartas;
    }
    
    public void validarLosetaTemplo(){
        
        for (int i = 0; i < 25 ; i++) {
            for (int j = 0; j < 25 ; j++) {
                if (tablero[i][j] != null && tablero[i][j].getTipo().equals("Templo")) {
                    
                    int Mepples[] = new int[jugadores.size()];
                    
                    if (i < 24 && tablero[i+1][j] != null && tablero[i+1][j].getClasificacion().equals("Recolector")) {//Abajo                        
                        for (int h = 0; h < jugadores.size() ; h++) {                            
                            if (jugadores.get(h).getColor().equals(tablero[i+1][j].getColor())) {
                                Mepples[h] += tablero[i+1][j].getAbajo();
                                break;
                            }
                        }
                    }
                    
                    if (i > 0 && tablero[i-1][j] != null && tablero[i-1][j].getClasificacion().equals("Recolector")) {//Arriba
                        for (int h = 0; h < jugadores.size() ; h++) {                            
                            if (jugadores.get(h).getColor().equals(tablero[i-1][j].getColor())) {
                                Mepples[h] += tablero[i-1][j].getArriba();
                                break;
                            }
                        }
                    }
                    
                    if (j < 24 && tablero[i][j+1] != null && tablero[i][j+1].getClasificacion().equals("Recolector")) {//Derecha
                        for (int h = 0; h < jugadores.size() ; h++) {                            
                            if (jugadores.get(h).getColor().equals(tablero[i][j+1].getColor())) {
                                Mepples[h] += tablero[i][j+1].getDerecha();
                                break;
                            }
                        }
                    }
                    
                    if (j > 0 && tablero[i][j-1] != null && tablero[i][j-1].getClasificacion().equals("Recolector")) {//Izquierda
                        for (int h = 0; h < jugadores.size() ; h++) {                            
                            if (jugadores.get(h).getColor().equals(tablero[i][j-1].getColor())) {
                                Mepples[h] += tablero[i][j-1].getIzquierda();
                                break;
                            }
                        }
                    }
                                        
                    List<Integer> Ganadores = new ArrayList<Integer>();               
                    int Mayor = 0;
                    int Mayor2 = 0;
                    
                    for (int k = 0; k < Mepples.length; k++) {
                        if (Mepples[k] > Mayor) {
                            Mayor = Mepples[k];                            
                        }
                        if (Mepples[k] > Mayor2 && Mepples[k] < Mayor) {
                            Mayor2 = Mepples[k];                            
                        }                        
                    }
                    
                    for (int k = 0; k < Mepples.length; k++) {
                        if (Mepples[k] == Mayor) {
                            Ganadores.add(k);
                        }
                    }
                    
                    if (Ganadores.size()==1) { //Solo un primer lugar                        
                        
                        //Se le asigna la recompensa de 6 monedas
                        Jugadores PrimerLugar = jugadores.get(Ganadores.get(0));
                        PrimerLugar.setMonedas(PrimerLugar.getMonedas()+6);   
                        Ganadores.remove(0);//Se remueve de los ganadores, ya que ya se le asigno la recompensa
                        
                        //Se verifica el segundo lugar
                        for (int k = 0; k < Mepples.length; k++) {
                            if (Mepples[k] == Mayor2) {                                
                                Ganadores.add(k);                                
                            }
                        }
                        
                        int Recompensa;
                        if (Ganadores.size()==1) { //Si solo hay un segundo lugar, recompensa de 3 monedas
                            Recompensa = 3;
                        }
                        else  { //Si hay un empate, recompensa de 1 moneda
                            Recompensa = 1;
                        }
                        
                        //Se asignan las recompensas respectivas.
                        for (int k = 0; k < Ganadores.size(); k++) {
                            jugadores.get(Ganadores.get(k)).setMonedas(jugadores.get(Ganadores.get(k)).getMonedas()+Recompensa);
                        }
                    }
                    else {
                        for (int k = 0; k < Ganadores.size(); k++) {
                            jugadores.get(Ganadores.get(k)).setMonedas(jugadores.get(Ganadores.get(k)).getMonedas()+3);
                        }
                    }
                }
            }
        }
    }
    
    public Jugadores PuntuacionFinal(){
        
        int Mayor = -100;
        Jugadores Ganador = null;
        
        //Va sumando las monedas del rio
        for (int k = 0; k < jugadores.size(); k++) {
            jugadores.get(k).setMonedas(jugadores.get(k).getMonedas() + jugadores.get(k).getPuntosRio());
        }
        //Va sumando las monedas por ficha solar
        for (int k = 0; k < jugadores.size(); k++) {
            jugadores.get(k).setMonedas(jugadores.get(k).getMonedas() + jugadores.get(k).getSolares());
        }
        
        for (int k = 0; k < jugadores.size(); k++) {
            if (jugadores.get(k).getMonedas() > Mayor) {
                Mayor = jugadores.get(k).getMonedas();                            
                Ganador = jugadores.get(k);
            }                 
        }
        
        return Ganador;
        
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
    
    public void validarLosetaSelva(int fila, int col){
        if(tablero[fila-1][col] != null){    //arriba
            for(int i = 0; i < jugadores.size();i++){
                if(jugadores.get(i).getColor().equals(tablero[fila-1][col].getColor())){
                   EvaluarColocarLosetaPos(jugadores.get(i), fila, col, tablero[fila-1][col].getAbajo());
                   break;
                }
            }
        }
        if(tablero[fila+1][col] != null){    //abajo
            for(int i = 0; i < jugadores.size();i++){
                if(jugadores.get(i).getColor().equals(tablero[fila+1][col].getColor())){
                   EvaluarColocarLosetaPos(jugadores.get(i), fila, col, tablero[fila+1][col].getArriba());
                   break;
                }
            }
        }
        if(tablero[fila][col-1] != null){    //Izquierda
            for(int i = 0; i < jugadores.size();i++){
                if(jugadores.get(i).getColor().equals(tablero[fila][col-1].getColor())){
                   EvaluarColocarLosetaPos(jugadores.get(i), fila, col, tablero[fila][col-1].getDerecha());
                   break;
                }
            }
        }
        if(tablero[fila][col+1] != null){ // Derecha
            for(int i = 0; i < jugadores.size();i++){
                if(jugadores.get(i).getColor().equals(tablero[fila][col+1].getColor())){
                   EvaluarColocarLosetaPos(jugadores.get(i), fila, col, tablero[fila][col+1].getIzquierda());
                   break;
                }
            }
        }
    }
    
    public void validarLosetaRecolector(Jugadores j, int fila, int col){
        Losetas loseta = tablero[fila][col];
        List<String> orden = new ArrayList();
        System.out.println("x:"+fila + ", y:"+col);
        for(int i = 0; i < jugadores.size();i++){
            if(jugadores.get(i).getColor().equals(j.getColor())){
                j = jugadores.get(i);
                break;
            }
        }
        
        if(tablero[fila-1][col] != null){    //arriba
            if(tablero[fila-1][col].getTipo().equals("Cacao1") || tablero[fila-1][col].getTipo().equals("Cacao2")){
                orden.add("arriba");
            } 
        }
        if(tablero[fila+1][col] != null){    //abajo
            if(tablero[fila+1][col].getTipo().equals("Cacao1") || tablero[fila+1][col].getTipo().equals("Cacao2")){
                orden.add("abajo");
            }
        }
        if(tablero[fila][col-1] != null){    //Izquierda
            if(tablero[fila][col-1].getTipo().equals("Cacao1") || tablero[fila][col-1].getTipo().equals("Cacao2")){
                orden.add("izquierda");
            }
        }
        if(tablero[fila][col+1] != null){ // Derecha
            if(tablero[fila][col+1].getTipo().equals("Cacao1") || tablero[fila][col+1].getTipo().equals("Cacao2")){
                orden.add("derecha");
            }
        }
        
        if(!orden.contains("arriba")){
            System.out.println("no tiene arriba");
            orden.add("arriba");
        }
        if(!orden.contains("abajo")){
            System.out.println("no tiene abajo");
            orden.add("abajo");
        }
        if(!orden.contains("derecha")){
            System.out.println("no tiene derecha");
            orden.add("derecha");
        }
        if(!orden.contains("izquierda")){
            System.out.println("no tiene izquierda");
            orden.add("izquierda");
        }      
        
        while(orden.size()>0){
            if(orden.get(0).equals("arriba")){
                EvaluarColocarLosetaPos(j, fila-1, col, loseta.getArriba());
            }
            else if(orden.get(0).equals("abajo")){
                EvaluarColocarLosetaPos(j, fila+1, col, loseta.getAbajo());
            }
            else if(orden.get(0).equals("izquierda")){
                EvaluarColocarLosetaPos(j, fila, col-1, loseta.getIzquierda());
            }
            else if(orden.get(0).equals("derecha")){
                EvaluarColocarLosetaPos(j, fila, col+1, loseta.getDerecha());
            }
            orden.remove(0);
        }
    }
    
    private void EvaluarColocarLosetaPos(Jugadores jug, int i, int j, int cant){ 
        if(tablero[i][j] != null){ 
            for(int m=0;m<cant;m++){
                if(tablero[i][j].getTipo().equals("Cacao1")){
                    if(jug.getCacaos()<5){
                        jug.setCacaos(jug.getCacaos()+1);
                    }
                }
                else if(tablero[i][j].getTipo().equals("Cacao2")){
                    if(jug.getCacaos()<4){
                        jug.setCacaos(jug.getCacaos()+2);
                    }else if(jug.getCacaos()<5){
                        jug.setCacaos(jug.getCacaos()+1);
                    }
                }
                else if(tablero[i][j].getTipo().equals("Mercado2")){
                    if(jug.getCacaos()>0){
                       jug.setCacaos(jug.getCacaos()-1);
                       jug.setMonedas(jug.getMonedas()+2);
                    }
                }
                else if(tablero[i][j].getTipo().equals("Mercado3")){
                    if(jug.getCacaos()>0){
                       jug.setCacaos(jug.getCacaos()-1);
                       jug.setMonedas(jug.getMonedas()+3);
                    }
                }
                else if(tablero[i][j].getTipo().equals("Mercado4")){
                    if(jug.getCacaos()>0){
                       jug.setCacaos(jug.getCacaos()-1);
                       jug.setMonedas(jug.getMonedas()+4);
                    }
                }
                else if(tablero[i][j].getTipo().equals("Mina1")){
                    jug.setMonedas(jug.getMonedas()+1);
                }
                else if(tablero[i][j].getTipo().equals("Mina2")){
                    jug.setMonedas(jug.getMonedas()+2);
                }
                else if(tablero[i][j].getTipo().equals("Agua")){
                    if(jug.getPuntosRio()<17){
                        jug.setPuntosRio(jug.getPuntosRio()+4);
                    }
                }
                else if(tablero[i][j].getTipo().equals("Solar")){
                    if(jug.getSolares()<3){
                        jug.setSolares(jug.getSolares()+1);
                    }
                }
            }
        }
    }
}
