/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_cliente.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
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
import org.una.cacao_cliente.clases.Comunicacion;
import org.una.cacao_cliente.clases.Globales;
import org.una.cacao_cliente.utility.FlowController;
import org.una.cacao_cliente.utility.Mensaje;

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

    Mensaje msg = new Mensaje();
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
        if(!txt_ip.getText().equals("")){
            try{
                Socket s = new Socket(txt_ip.getText(), 5056);
                Globales.getInstance().comunicacion = new Comunicacion(s, new DataInputStream(s.getInputStream()), new DataOutputStream(s.getOutputStream()));

                FlowController.getInstance().goViewInStage("Menu",getStage());

            }catch(Exception e){
                
                msg.alerta(root, "Alerta", "Error de conexion");
            }

        }else{
           msg.alerta(root, "Alerta", "Por favor complete los campos necesarios");
        }
    }

    
}
