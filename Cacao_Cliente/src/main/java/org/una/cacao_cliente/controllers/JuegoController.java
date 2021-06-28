/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_cliente.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.una.cacao_cliente.clases.Globales;
import org.una.cacao_cliente.clases.Jugadores;
import org.una.cacao_cliente.clases.Losetas;
import org.una.cacao_cliente.clases.Transferencia;
import org.una.cacao_cliente.utility.FlowController;
import org.una.cacao_cliente.utility.Mensaje;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class JuegoController extends Controller implements Initializable {

    @FXML
    private GridPane grid_juego;

    private int columnCount = 0;
    private int rowCount = 0;
    
    private int xSelva=0 , ySelva=0, xRecolector=0,Yrecolector=0;
    @FXML
    private StackPane root;
    @FXML
    private VBox vBoxJugador1;
    @FXML
    private Label lbl_jugador1;
    @FXML
    private Label lbl_jugador1_coins;
    @FXML
    private Label lbl_jugador1_river;
    @FXML
    private Label lbl_jugador1_cacaos;
    @FXML
    private Label lbl_jugador1_sol;
    @FXML
    private VBox vBoxJugador2;
    @FXML
    private Label lbl_jugador2;
    @FXML
    private Label lbl_jugador2_coins;
    @FXML
    private Label lbl_jugador2_river;
    @FXML
    private Label lbl_jugador2_cacaos;
    @FXML
    private Label lbl_jugador2_sol;
    @FXML
    private VBox vBoxJugador3;
    @FXML
    private Label lbl_jugador3;
    @FXML
    private Label lbl_jugador3_coins;
    @FXML
    private Label lbl_jugador3_river;
    @FXML
    private Label lbl_jugador3_cacaos;
    @FXML
    private Label lbl_jugador3_sol;
    @FXML
    private VBox vBoxJugador4;
    @FXML
    private Label lbl_jugador4;
    @FXML
    private Label lbl_jugador4_coins;
    @FXML
    private Label lbl_jugador4_river;
    @FXML
    private Label lbl_jugador4_cacaos;
    @FXML
    private Label lbl_jugador4_sol;
    @FXML
    private Label lbl_turno;
    @FXML
    private HBox hBox_Loseta1Jugador;
    @FXML
    private HBox hBox_Loseta2Jugador;
    @FXML
    private HBox hBox_Loseta3Jugador;
    @FXML
    private HBox hBox_LosetaSelva1;
    @FXML
    private HBox hBox_LosetaSelva2;
    @FXML
    private HBox hBox_LosetaSelva3;
    @FXML
    private Button btn_colocar_loseta;
    
    
    private ImageView losetaTemporal;
    private boolean rotarLoseta;
    private boolean losetaColocada;
    private boolean recolectorColocado;
    private String nombreLosetaColocada = "";
    private Losetas losetaTemporalTablero = new Losetas();
    Mensaje msg = new Mensaje();
    /**
     * Initializes the controller class.
     */
    public void ActualizarVista() {
        valoresPorDefecto();
        actualizarLosetasJugadores();
        actualizarLosetasSelva();
        actualizarTablero();
        
        for(int i = 0; i < Globales.getInstance().partida.getJugadores().size();i++){
            if(i == 0){
                actualizarValoresCajasJugadores(Globales.getInstance().partida.getJugadores().get(i),vBoxJugador1,
                lbl_jugador1,lbl_jugador1_coins,lbl_jugador1_river,lbl_jugador1_cacaos,lbl_jugador1_sol);
            }
            else if(i==1){
                actualizarValoresCajasJugadores(Globales.getInstance().partida.getJugadores().get(i),vBoxJugador2,
                lbl_jugador2,lbl_jugador2_coins,lbl_jugador2_river,lbl_jugador2_cacaos,lbl_jugador2_sol);
            }
            else if(i==2){
                actualizarValoresCajasJugadores(Globales.getInstance().partida.getJugadores().get(i),vBoxJugador3,
                lbl_jugador3,lbl_jugador3_coins,lbl_jugador3_river,lbl_jugador3_cacaos,lbl_jugador3_sol);
            }
            else{
                actualizarValoresCajasJugadores(Globales.getInstance().partida.getJugadores().get(i),vBoxJugador4,
                lbl_jugador4,lbl_jugador4_coins,lbl_jugador4_river,lbl_jugador4_cacaos,lbl_jugador4_sol);
            }
        }
    }
    
    private void valoresPorDefecto(){
        vBoxJugador1.setVisible(false);
        vBoxJugador2.setVisible(false);
        vBoxJugador3.setVisible(false);
        vBoxJugador4.setVisible(false);
        hBox_Loseta1Jugador.getChildren().clear();
        hBox_Loseta2Jugador.getChildren().clear();
        hBox_Loseta3Jugador.getChildren().clear();
        hBox_LosetaSelva1.getChildren().clear();
        hBox_LosetaSelva2.getChildren().clear();
        hBox_LosetaSelva3.getChildren().clear();
        
        for (int i = 0; i < Globales.getInstance().partida.getJugadores().size(); i++) { //Establece el label del turno con el nombre del jugador correspondiente            
            if (Globales.getInstance().partida.getJugadores().get(i).getColor().equals(Globales.getInstance().partida.getTurno())) {
                lbl_turno.setText(Globales.getInstance().partida.getJugadores().get(i).getNombre());                
                break;
            }            
        }
        
        if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
            btn_colocar_loseta.setVisible(true);
        }
        else {
            btn_colocar_loseta.setVisible(false);
        }
        
        limpiarTablero();
    }
    
    private void actualizarTablero(){
        for(int i=0;i<25;i++){
            for(int j=0;j<25;j++){
                if(Globales.getInstance().partida.getTablero()[i][j] != null){
                    ImageView imageView = new ImageView(new Image("/org/una/cacao_cliente/img/"+Globales.getInstance().partida.getTablero()[i][j].getTipo()+".png"));
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    imageView.setRotate(Globales.getInstance().partida.getTablero()[i][j].getAngulo());
                    VBox source = (VBox) ObtenerNodoPanel(i, j);
                    if(Globales.getInstance().partida.getTablero()[i][j].getClasificacion().equals("Recolector")){
                        for(Jugadores jugador : Globales.getInstance().partida.getJugadores()){
                            if(jugador.getColor().equals(Globales.getInstance().partida.getTablero()[i][j].getColor())){

                               source.setStyle("-fx-background-color : "+asignarColor(Globales.getInstance().partida.getTablero()[i][j].getColor()));
                            }
                        }
                    }
                    source.getChildren().add(imageView);
                    GridPane.setRowIndex(source, i);
                    GridPane.setColumnIndex(source, j);
                }
            }
        }
    }
    
    private void actualizarLosetasJugadores(){
        for(Jugadores j : Globales.getInstance().partida.getJugadores()){
            if(Globales.getInstance().jugador.getColor().equals(j.getColor())){
                hBox_Loseta1Jugador.setStyle("-fx-background-color : "+asignarColor(j.getColor()));
                hBox_Loseta2Jugador.setStyle("-fx-background-color : "+asignarColor(j.getColor()));
                hBox_Loseta3Jugador.setStyle("-fx-background-color : "+asignarColor(j.getColor()));
               for(int i = 0; i<j.getLosetasRecolectores().size();i++){
                    ImageView imageView = new ImageView(new Image("/org/una/cacao_cliente/img/"+j.getLosetasRecolectores().get(i).getTipo()+".png"));
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    
                    if(i==0){
                        hBox_Loseta1Jugador.getChildren().add(imageView); 
                    }
                    else if(i==1){
                        hBox_Loseta2Jugador.getChildren().add(imageView);
                    }
                    else if(i==2){
                        hBox_Loseta3Jugador.getChildren().add(imageView);
                        break;
                    }
                }
               break;
            }
        }
    }
    
    private void actualizarLosetasSelva(){
        for(int i = 0; i<Globales.getInstance().partida.getBaraja_losetasSelva().size();i++){
            ImageView imageView = new ImageView(new Image("/org/una/cacao_cliente/img/"+Globales.getInstance().partida.getBaraja_losetasSelva().get(i).getTipo()+".png"));
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            if(i==0){
                hBox_LosetaSelva1.getChildren().add(imageView);        
            }
            else if(i==1){
                hBox_LosetaSelva2.getChildren().add(imageView);        
            }
            else if(i==2){
                hBox_LosetaSelva3.getChildren().add(imageView); 
                break;
            }
        }
    }
    
    private void actualizarValoresCajasJugadores(Jugadores j, VBox vb, Label lbl_nombre,
    Label lbl_monedas, Label lbl_rio, Label lbl_cacaos, Label lbl_soles){
        vb.setVisible(true);
        vb.setStyle("-fx-border-color : "+asignarColor(j.getColor()));
        lbl_nombre.setText(j.getNombre());
        lbl_monedas.setText(j.getMonedas()+"");
        lbl_rio.setText(j.getPuntosRio()+"");
        lbl_cacaos.setText(j.getCacaos()+"");
        //lbl_soles.setText(j.getSoles()+"");
    }
    
    
    private String asignarColor(String valor){
        String color = "";
        switch (valor) {
            case "Morado": color = "Purple"; break;
            case "Verde": color = "Green"; break;
            case "Azul": color = "Blue"; break;
            case "Rojo": color = "Red"; break;
            case "Naranja": color = "Orange"; break;
            case "Amarillo": color = "Yellow"; break;
            case "Blanco": color = "White"; break;
            case "Gris": color = "Gray"; break;
            case "Celeste": color = "LIGHTSKYBLUE"; break;
            case "Cafe": color = "Brown"; break;
            case "Rosa": color = "Pink"; break;
            case "Verde Claro": color = "LIGHTGREEN"; break;
            case "Turquesa": color = "TURQUOISE"; break;
            default: break;
        }
        return color;
    }
   
    /*Recibe una columna y una fila para hacer una busqueda del nodo,
    que se encuentre en las posicion correspondiente y de esta manera retornarlo*/
    public Node ObtenerNodoPanel (final int row, final int column) {
        Node resultado = null;
        ObservableList<Node> childrens = grid_juego.getChildren();
        
        for (Node node : childrens) {
            if(node instanceof VBox && grid_juego.getRowIndex(node) == row && grid_juego.getColumnIndex(node) == column) {
                resultado = node;
                break;
            }
        }
        return resultado;
    }
    
    public void limpiarTablero() {

        ObservableList<Node> childrens = grid_juego.getChildren();
        
        for (int i = 0; i < childrens.size(); i++) {
            if(i != 0){
                VBox source = (VBox) childrens.get(i);
            source.getChildren().clear();
            }
        }
    }
    
    private boolean EvaluarColocarLoseta(int i, int j, String clasificacion, int cant){
        Losetas[][] tablero = Globales.getInstance().partida.getTablero();
        int[] posibilidad = new int[2];
        if(tablero[i][j] == null){
            if(tablero[i-1][j] != null){
                posibilidad = EvaluarColocarLosetaPos(tablero, i-1, j, clasificacion, posibilidad);
            }
            if(tablero[i+1][j] != null){
                posibilidad = EvaluarColocarLosetaPos(tablero, i+1, j, clasificacion, posibilidad);
            }
            if(tablero[i][j-1] != null){
                posibilidad = EvaluarColocarLosetaPos(tablero, i, j-1, clasificacion, posibilidad);
            }
            if(tablero[i][j+1] != null){
                posibilidad = EvaluarColocarLosetaPos(tablero, i, j+1, clasificacion, posibilidad);
            }
        }
        return posibilidad[0] >= cant && posibilidad[1] == 0;
    }
    
    private int[] EvaluarColocarLosetaPos(Losetas[][] tablero, int i, int j, String clasificacion, int[] posibilidad){
        if(tablero[i][j] != null){
            if(tablero[i][j].getClasificacion().equals(clasificacion)){
                posibilidad[0]+=1;
                return posibilidad;
            }
            else{
                posibilidad[1]+=1;
                return posibilidad;
            }
        }
        return posibilidad;
    }
    
    private String ValidarCamposSelva(){ //Valida si hay campos posibles donde poner una loseta de selva
        
        Losetas[][] tablero = Globales.getInstance().partida.getTablero();
        int Contador = 0; //Lleva la cuenta de recolectores adyacentes al espacio en blanco, para verificar si se puede colocar una loseta de selva
        
        for (int i = 0; i < 25 ; i++) {
            for (int j = 0; j < 25 ; j++) {
                Contador = 0;
                if (tablero[i][j] == null) {                    
                    if (i < 24 && tablero[i+1][j] != null && tablero[i+1][j].getClasificacion().equals("Recolector")) {//Abajo
                        Contador+=1;
                    }
                    if (i > 0 && tablero[i-1][j] != null && tablero[i-1][j].getClasificacion().equals("Recolector")) {//Arriba
                        Contador+=1;
                    }
                    if (j < 24 && tablero[i][j+1] != null && tablero[i][j+1].getClasificacion().equals("Recolector")) {//Derecha
                        Contador+=1;
                    }
                    if (j > 0 && tablero[i][j-1] != null && tablero[i][j-1].getClasificacion().equals("Recolector")) {//Izquierda
                        Contador+=1;
                    }
                    
                    if (Contador >= 2) {
                        return "No pasar turno";
                    }
                }
            }
        }
        return "Pasar turno";
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(int i = 0; i< 25; i++){
            for(int j = 0; j< 25; j++){
                VBox b = new VBox();
                b.setAlignment(Pos.CENTER);
                b.setPrefSize(100, 100);
                grid_juego.add(new VBox(), i, j);
                
            }
        }
    }    

    @Override
    public void initialize() {
        
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @FXML
    private void btnSalirOnAction(ActionEvent event) {
        FlowController.getInstance().closeProgram();
        System.exit(0);
    }

    @FXML
    private void colocarLoseta(MouseEvent event) {
        if(event.getTarget() instanceof VBox ){
            VBox source = (VBox)event.getTarget();
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            String clasificacion;
            int cant;
            if(nombreLosetaColocada.equals("losetaSelva1") || nombreLosetaColocada.equals("losetaSelva2") || nombreLosetaColocada.equals("losetaSelva3")){
                clasificacion = "Recolector";
                cant = 2;
            }else{
                 clasificacion = "Selva";
                cant = 1;
            }
            
            if(source.getChildren().isEmpty() && losetaTemporal!=null && EvaluarColocarLoseta(rowIndex, colIndex, clasificacion, cant)){
                source.getChildren().add(losetaTemporal);
                GridPane.setColumnIndex(source, colIndex);
                GridPane.setRowIndex(source, rowIndex);
                columnCount = colIndex.intValue();
                rowCount = rowIndex.intValue();
                losetaColocada = true;
            }
           // System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
        }
        else if(losetaTemporal == event.getTarget() && rotarLoseta) {
            ImageView source = (ImageView)event.getTarget();
            source.setRotate(source.getRotate() + 90); 
            losetaTemporalTablero.setAngulo(source.getRotate());
            int arriba = losetaTemporalTablero.getArriba(), abajo = losetaTemporalTablero.getAbajo(), der = losetaTemporalTablero.getDerecha(), izq = losetaTemporalTablero.getIzquierda();
            //System.out.println("Antes: "+arriba + " | " + abajo + " | " + der+ " | " + izq);
            losetaTemporalTablero.setArriba(izq);
            losetaTemporalTablero.setAbajo(der);
            losetaTemporalTablero.setIzquierda(abajo);
            losetaTemporalTablero.setDerecha(arriba);
            //System.out.println("Despues: "+losetaTemporalTablero.getArriba() +" | "+ losetaTemporalTablero.getAbajo() + " | " + losetaTemporalTablero.getDerecha() + " | " +losetaTemporalTablero.getIzquierda() );
        }
    }
        
    @FXML
    private void escogerLoseta1Jugador(MouseEvent event) {
        if(!hBox_Loseta1Jugador.getChildren().isEmpty() && !losetaColocada){
            if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
                
                if (!recolectorColocado) {
                    rotarLoseta = true;
                    losetaTemporal = (ImageView) hBox_Loseta1Jugador.getChildren().get(0);
                    nombreLosetaColocada = "loseta1Jugador";
                    List<Losetas> losetasJugador = null;
                    for(Jugadores j : Globales.getInstance().partida.getJugadores()){
                        if(j.getColor().equals(Globales.getInstance().jugador.getColor())){
                            losetasJugador = j.getLosetasRecolectores();
                            break;
                        }
                    }
                    losetaTemporalTablero = losetasJugador.get(0);
                }
                else{
                    msg.alerta(root, "Alerta", "Ya se ha colocado una loseta de recolector en este turno, por favor coloque una loseta de selva");
                }                
            }
            else {                
                msg.alerta(root, "Alerta", "No es su turno, por favor espere");
            }            
            
        }
    }

    @FXML
    private void escogerLoseta2Jugador(MouseEvent event) {
        if(!hBox_Loseta2Jugador.getChildren().isEmpty() && !losetaColocada){
            if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
                if (!recolectorColocado) {
                    rotarLoseta = true;
                    losetaTemporal = (ImageView) hBox_Loseta2Jugador.getChildren().get(0);
                    nombreLosetaColocada = "loseta2Jugador";
                    List<Losetas> losetasJugador = null;
                    for(Jugadores j : Globales.getInstance().partida.getJugadores()){
                        if(j.getColor().equals(Globales.getInstance().jugador.getColor())){
                            losetasJugador = j.getLosetasRecolectores();
                            break;
                        }
                    }
                    losetaTemporalTablero = losetasJugador.get(1);
                }
                else{
                    msg.alerta(root, "Alerta", "Ya se ha colocado una loseta de recolector en este turno, por favor coloque una loseta de selva");
                }
                
            }
            else {                
                msg.alerta(root, "Alerta", "No es su turno, por favor espere");
            }                        
        }
    }

    @FXML
    private void escogerLoseta3Jugador(MouseEvent event) {
        if(!hBox_Loseta3Jugador.getChildren().isEmpty() && !losetaColocada){
            if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
                if (!recolectorColocado) {
                    rotarLoseta = true;
                    losetaTemporal = (ImageView) hBox_Loseta3Jugador.getChildren().get(0);
                    nombreLosetaColocada = "loseta3Jugador";
                    List<Losetas> losetasJugador = null;
                    for(Jugadores j : Globales.getInstance().partida.getJugadores()){
                        if(j.getColor().equals(Globales.getInstance().jugador.getColor())){
                            losetasJugador = j.getLosetasRecolectores();
                            break;
                        }
                    }
                    losetaTemporalTablero = losetasJugador.get(2);
                }
                else{
                    msg.alerta(root, "Alerta", "Ya se ha colocado una loseta de recolector en este turno, por favor coloque una loseta de selva");
                }                
            }
            else {                
                msg.alerta(root, "Alerta", "No es su turno, por favor espere");
            }            
            
        }
    }

    @FXML
    private void escogerLosetaSelva1(MouseEvent event) {
        if(!hBox_LosetaSelva1.getChildren().isEmpty() && !losetaColocada){
            if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
                if(recolectorColocado){
                    rotarLoseta = false;
                    losetaTemporal = (ImageView) hBox_LosetaSelva1.getChildren().get(0);
                    nombreLosetaColocada = "losetaSelva1";
                }
                else{
                   msg.alerta(root, "Alerta", "Coloque una loseta de recolector antes"); 
                }                
            }
            else {                
                msg.alerta(root, "Alerta", "No es su turno, por favor espere");
            }            
            
        }
    }

    @FXML
    private void escogerLosetaSelva2(MouseEvent event) {
        if(!hBox_LosetaSelva2.getChildren().isEmpty() && !losetaColocada){
            if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
                if(recolectorColocado){
                    rotarLoseta = false;
                    losetaTemporal = (ImageView) hBox_LosetaSelva2.getChildren().get(0);
                    nombreLosetaColocada = "losetaSelva2";
                }
                else{
                    msg.alerta(root, "Alerta", "Coloque una loseta de recolector antes");
                }                
            }
            else {                
                msg.alerta(root, "Alerta", "No es su turno, por favor espere");
            }                        
        }
    }

    @FXML
    private void escogerLosetaSelva3(MouseEvent event) {
        if(!hBox_LosetaSelva3.getChildren().isEmpty() && !losetaColocada ){
            if (Globales.getInstance().partida.getTurno().equals(Globales.getInstance().jugador.getColor())) {
                if(recolectorColocado){
                    rotarLoseta = false;
                    losetaTemporal = (ImageView) hBox_LosetaSelva3.getChildren().get(0);
                    nombreLosetaColocada = "losetaSelva3";
                }
                else{
                    msg.alerta(root, "Alerta", "Coloque una loseta de recolector antes");
                }                
            }
            else {                
                msg.alerta(root, "Alerta", "No es su turno, por favor espere");
            }            
        }
    }

    @FXML
    private void btnColocarOnAction(ActionEvent event) {
        
        String PasarTurno = "No pasar turno"; 
        if(losetaTemporal != null && losetaColocada){
            
            Losetas[][] tablero = Globales.getInstance().partida.getTablero();
            List<Losetas> losetasJugador = null;
            Jugadores jugador = null;
            List<Losetas> losetasSelva = Globales.getInstance().partida.getBaraja_losetasSelva();
            for(Jugadores j : Globales.getInstance().partida.getJugadores()){
                if(j.getColor().equals(Globales.getInstance().jugador.getColor())){
                    losetasJugador = j.getLosetasRecolectores();
                    jugador = j;
                    break;
                }
            }

            if(nombreLosetaColocada.equals("loseta1Jugador")){
                tablero[rowCount][columnCount] = losetasJugador.get(0);
                xRecolector = rowCount; Yrecolector = columnCount;
                losetasJugador.remove(losetasJugador.get(0));
                recolectorColocado = true;
                PasarTurno = ValidarCamposSelva();                
            }
            else if(nombreLosetaColocada.equals("loseta2Jugador")){
                tablero[rowCount][columnCount] = losetasJugador.get(1);
                xRecolector = rowCount; Yrecolector = columnCount;
                losetasJugador.remove(losetasJugador.get(1));
                recolectorColocado = true;
                PasarTurno = ValidarCamposSelva();
            }
            else if(nombreLosetaColocada.equals("loseta3Jugador")){
                tablero[rowCount][columnCount] = losetasJugador.get(2);
                xRecolector = rowCount; Yrecolector = columnCount;
                losetasJugador.remove(losetasJugador.get(2));
                recolectorColocado = true;
                PasarTurno = ValidarCamposSelva();
            }
            else if(nombreLosetaColocada.equals("losetaSelva1")){
                tablero[rowCount][columnCount] = losetasSelva.get(0);
                xSelva = rowCount; ySelva = columnCount;
                losetasSelva.remove(losetasSelva.get(0));
                recolectorColocado = false;
            }
            else if(nombreLosetaColocada.equals("losetaSelva2")){
                tablero[rowCount][columnCount] = losetasSelva.get(1);
                xSelva = rowCount; ySelva = columnCount;
                losetasSelva.remove(losetasSelva.get(1));
                recolectorColocado = false;
            }
            else{
                tablero[rowCount][columnCount] = losetasSelva.get(2);
                xSelva = rowCount; ySelva = columnCount;
                losetasSelva.remove(losetasSelva.get(2));
                recolectorColocado = false;
            }
            
            tablero[rowCount][columnCount].setAngulo(losetaTemporalTablero.getAngulo());
            tablero[rowCount][columnCount].setArriba(losetaTemporalTablero.getArriba());
            tablero[rowCount][columnCount].setAbajo(losetaTemporalTablero.getAbajo());
            tablero[rowCount][columnCount].setIzquierda(losetaTemporalTablero.getIzquierda());
            tablero[rowCount][columnCount].setDerecha(losetaTemporalTablero.getDerecha());
            
            Globales.getInstance().partida.setTablero(tablero);
            Globales.getInstance().partida.setBaraja_losetasSelva(losetasSelva);
            int pos = Globales.getInstance().partida.getJugadores().indexOf(jugador);
            Globales.getInstance().partida.getJugadores().get(pos).setLosetasRecolectores(losetasJugador);
            
            if (PasarTurno.equals("Pasar turno")) {
                List<Object> lstObject = new ArrayList<>();
                recolectorColocado = false;
                lstObject.add(Globales.getInstance().jugador);
                lstObject.add(xRecolector);
                lstObject.add(Yrecolector);

                Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("colocarLosetaRecolector", lstObject, Globales.getInstance().partida));
            }
            else if(tablero[rowCount][columnCount].getClasificacion().equals("Selva")){
                List<Object> lstObject = new ArrayList<>();
                recolectorColocado = false;
                lstObject.add(xSelva);
                lstObject.add(ySelva);

                Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("colocarLosetaSelva", lstObject, Globales.getInstance().partida));
                
                List<Object> lstObject2 = new ArrayList<>();
                recolectorColocado = false;
                lstObject2.add(Globales.getInstance().jugador);
                lstObject2.add(xRecolector);
                lstObject2.add(Yrecolector);

                Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("colocarLosetaRecolector", lstObject2, Globales.getInstance().partida));
            }
            
            losetaTemporal = null;
            losetaTemporalTablero = new Losetas();
            losetaColocada = false;
            nombreLosetaColocada = "";
        }
    }
}
