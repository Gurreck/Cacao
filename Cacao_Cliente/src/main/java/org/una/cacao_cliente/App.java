package org.una.cacao_cliente;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.una.cacao_cliente.utility.FlowController;


/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("Cacao");
        FlowController.getInstance().goViewInWindow("MenuIp");
    } 

    public static void main(String[] args) {
        launch();
    }
}