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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.una.cacao_cliente.clases.Globales;
import org.una.cacao_cliente.clases.Jugadores;
import org.una.cacao_cliente.clases.Transferencia;
import org.una.cacao_cliente.utility.FlowController;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class SalaEsperaController extends Controller implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private Label lbl_jugador1;
    @FXML
    private Button btn_listo;
    @FXML
    private Label lbl_jugador2;
    @FXML
    private Label lbl_jugador3;
    @FXML
    private Label lbl_jugador4;
    @FXML
    private HBox hb_jugador1;
    @FXML
    private HBox hb_jugador2;
    @FXML
    private HBox hb_jugador3;
    @FXML
    private HBox hb_jugador4;
    @FXML
    private ImageView img_jugador1;
    @FXML
    private ImageView img_jugador2;
    @FXML
    private ImageView img_jugador3;
    @FXML
    private ImageView img_jugador4;

    public void ActualizarVista(){
        valoresPorDefecto();
        
        for(int i = 0; i < Globales.getInstance().partida.getJugadores().size();i++){
            if(i == 0){
                actualizarValores(Globales.getInstance().partida.getJugadores().get(i),hb_jugador1, lbl_jugador1, img_jugador1);
            }
            else if(i==1){
                actualizarValores(Globales.getInstance().partida.getJugadores().get(i),hb_jugador2, lbl_jugador2, img_jugador2);
            }
            else if(i==2){
                actualizarValores(Globales.getInstance().partida.getJugadores().get(i),hb_jugador3, lbl_jugador3, img_jugador3);
            }
            else{
                actualizarValores(Globales.getInstance().partida.getJugadores().get(i),hb_jugador4, lbl_jugador4, img_jugador4);
            }
        }
        
    } 
    private void actualizarValores(Jugadores j, HBox h, Label l, ImageView i){
        h.setVisible(true);
        i.setVisible(true);
        l.setText(j.getNombre());
        if(j.isAceptarPartida()){
            i.setOpacity(1);
        }
    }
    
    private void valoresPorDefecto(){
        hb_jugador2.setVisible(false);
        hb_jugador3.setVisible(false);
        hb_jugador4.setVisible(false);
        img_jugador2.setVisible(false);
        img_jugador3.setVisible(false);
        img_jugador4.setVisible(false);
        
        img_jugador1.setOpacity(0.5);
        img_jugador2.setOpacity(0.5);
        img_jugador3.setOpacity(0.5);
        img_jugador4.setOpacity(0.5);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @Override
    public Node getRoot() {
        return root;
    }
    
    @FXML
    private void btnListoOnAction(ActionEvent event) {
        
        List<Object> lstObject = new ArrayList<>();
        lstObject.add(Globales.getInstance().jugador);

        Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("aceptarPartida", lstObject, null));
            
        //FlowController.getInstance().goViewInStage("Juego",getStage());
    }

    @FXML
    private void btnIniciarOnAction(ActionEvent event) {
        Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("iniciarPartida", new ArrayList<>(), null));
    }
    
}
