/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_cliente.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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

    @FXML
    private void btnSalirOnAction(ActionEvent event) {
        System.exit(0);
    }


    
}
