/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_cliente.controllers;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.cacao_cliente.clases.Globales;
import org.una.cacao_cliente.clases.Jugadores;
import org.una.cacao_cliente.clases.Transferencia;
import org.una.cacao_cliente.utility.FlowController;
import org.una.cacao_cliente.utility.Mensaje;

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
    private ComboBox<String> combo_color;
    @FXML
    private Button btn_jugar;
    @FXML
    private StackPane root;
    
    Mensaje msg = new Mensaje();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo_color.getItems().add("Morado");
        combo_color.getItems().add("Verde");
        combo_color.getItems().add("Azul");
        combo_color.getItems().add("Rojo");
        combo_color.getItems().add("Naranja");
        combo_color.getItems().add("Amarillo");
        combo_color.getItems().add("Blanco");
        combo_color.getItems().add("Gris");
        combo_color.getItems().add("Celeste");
        combo_color.getItems().add("Cafe");
        combo_color.getItems().add("Rosa");
        combo_color.getItems().add("Verde Claro");
        combo_color.getItems().add("Turquesa");
        
        Globales.getInstance().comunicacion.escucharServidor();
    }    

    @Override
    public void initialize() {
        
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @FXML
    private void btnJugarOnAction(ActionEvent event) {
        if(!txt_nombre.getText().equals("") && date_fecNac.getValue() != null && combo_color.getValue() != null){
            LocalDate localDate = date_fecNac.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date nacimiento = Date.from(instant);
            
            Globales.getInstance().jugador = new Jugadores(txt_nombre.getText(), nacimiento, combo_color.getValue(), new ArrayList<>(), 0, 0, -16, false);
            
            List<Object> lstObject = new ArrayList<>();
            lstObject.add(Globales.getInstance().jugador);
                                    

            Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("login", lstObject, null));
            
        }
        else{
                msg.alerta(root, "Alerta", "Por favor, ingrese los datos requeridos");
           
        }
        
    }
    
}
