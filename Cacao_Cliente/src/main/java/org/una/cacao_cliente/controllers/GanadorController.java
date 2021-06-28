/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_cliente.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.una.cacao_cliente.clases.Globales;
import org.una.cacao_cliente.utility.FlowController;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class GanadorController extends Controller implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private Label lbl_jugadorGanador;
    @FXML
    private HBox VBox_segundo;
    @FXML
    private Label lbl_segundo;
    @FXML
    private HBox vBox_tercero;
    @FXML
    private Label lbl_tercero;
    @FXML
    private HBox vBox_cuarto;
    @FXML
    private Label lbl_cuarto;
    @FXML
    private Button btn_salir;   

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
    
    public void ActualizarVista() {
        
        valoresPorDefecto();
        
        int Cont = 0;
        for (int i = 0; i < Globales.getInstance().partida.getJugadores().size(); i++) {
            if (Globales.getInstance().partida.getGanador().equals(Globales.getInstance().partida.getJugadores().get(i).getColor())) {
                lbl_jugadorGanador.setText(Globales.getInstance().partida.getJugadores().get(i).getNombre());
            }
            else {
                if (Cont == 0) {
                    lbl_segundo.setText(Globales.getInstance().partida.getJugadores().get(i).getNombre());
                    VBox_segundo.setVisible(true);
                }
                if (Cont == 1) {
                    lbl_tercero.setText(Globales.getInstance().partida.getJugadores().get(i).getNombre());
                    vBox_tercero.setVisible(true);
                }
                if (Cont == 2) {
                    lbl_cuarto.setText(Globales.getInstance().partida.getJugadores().get(i).getNombre());
                    vBox_cuarto.setVisible(true);
                    
                }
                Cont++;
            }
        }        
    }
    
    public void valoresPorDefecto(){
        VBox_segundo.setVisible(false);
        vBox_cuarto.setVisible(false);
        vBox_tercero.setVisible(false);
    }
    
    @FXML
    private void btnSalirOnAction(ActionEvent event) {
        FlowController.getInstance().closeProgram();
        System.exit(0);
    }

    
}
