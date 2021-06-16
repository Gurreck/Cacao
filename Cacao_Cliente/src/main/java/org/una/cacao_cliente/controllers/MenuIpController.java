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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.cacao_cliente.utility.FlowController;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MenuIpController extends Controller implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private Label lbl_direccion;
    @FXML
    private TextField txt_ip;
    @FXML
    private Button btn_siguiente;

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
    private void btnSiguienteOnAction(ActionEvent event) {
        FlowController.getInstance().goViewInStage("Menu",getStage());
    }

    
}
