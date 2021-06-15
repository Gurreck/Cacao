/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_cliente.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MenuController extends Controller implements Initializable {

    @FXML
    private Label lbl_nombre;
    @FXML
    private TextField txt_nombre;
    @FXML
    private Label lbl_fecNac;
    @FXML
    private DatePicker date_fecNac;
    @FXML
    private Label lbl_colores;
    @FXML
    private ComboBox<?> combo_color;
    @FXML
    private Button btn_jugar;
    @FXML
    private StackPane root;

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
    
}
