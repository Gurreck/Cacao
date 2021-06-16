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
import javafx.scene.layout.StackPane;
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
        FlowController.getInstance().goViewInStage("Juego",getStage());
    }
    
}
