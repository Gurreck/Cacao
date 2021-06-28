package org.una.cacao_cliente.utility;

import org.una.cacao_cliente.App;
import org.una.cacao_cliente.controllers.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.una.cacao_cliente.clases.Globales;
import org.una.cacao_cliente.clases.Transferencia;

public class FlowController {
    
    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();
    public boolean modoDesarrollo = false;
    //FMLLoader: interfaz y controlador de la interfaz grafica
    private FlowController() {
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

    public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage, ResourceBundle idioma) {
        getInstance();
        this.mainStage = stage;
        this.idioma = idioma;
    }
 
    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(App.class.getResource("/org/una/cacao_cliente/view/" + name + ".fxml"), this.idioma);
                        loader.load();
                        loaders.put(name, loader);
                    } catch (Exception ex) {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Creando loader [" + name + "].", ex);
                    }
                }
            }
        }
        return loader;
    }

    public void goMain() {
        try {
            this.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("/org/una/cacao_cliente/view/MenuIp.fxml"), this.idioma)));
            this.mainStage.show();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    public void goView(String viewName) {
        goView(viewName, "Center", null);
       
    }
    
    public void goViewLeft(String viewName) {
        goView(viewName, "Left", null);
    }
    
    public void goViewRight(String viewName) {
        goView(viewName, "Right", null);
    }
    
    public void goViewTop(String viewName) {
        goView(viewName, "Top", null);
    }
    
    public void goViewBottom(String viewName) {
        goView(viewName, "Bottom", null);
    }
    
    public void goView(String viewName, String accion) {
        goView(viewName, "Center", accion);
    }

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center":
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().clear();   
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().add(loader.getRoot()); 
                break;
            case "Top":
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getTop()).getChildren().clear();
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getTop()).getChildren().add(loader.getRoot());
                break;
            case "Bottom":
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getBottom()).getChildren().clear();
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getBottom()).getChildren().add(loader.getRoot());
                break;
            case "Right":
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getRight()).getChildren().clear();
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getRight()).getChildren().add(loader.getRoot());
                break;
            case "Left":
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getLeft()).getChildren().clear();
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getLeft()).getChildren().add(loader.getRoot());
                break;
                
            default:
                break;
        }
    }

    public void goViewInStage(String viewName, Stage stage) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
    }

    public void goViewInWindow(String viewName) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("org/una/cacao_cliente/img/cacao.png"));
        stage.setTitle("Cacao");
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setOnCloseRequest(e -> closeProgram());
    }

    public void goViewInWindowModal(String viewName, Stage parentStage, Boolean resizable) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("org/una/cacao_cliente/img/cacao.png"));
        stage.setTitle("Cacao");
        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentStage);
        stage.centerOnScreen();
        stage.showAndWait();

    }

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public static void setIdioma(ResourceBundle idioma) {
        FlowController.idioma = idioma;
    }
    
    public void initialize() {
        this.loaders.clear();
    }

    public void closeProgram(){
        
        if(Globales.getInstance().comunicacion != null){
            List<Object> lstObject = new ArrayList<>();
            lstObject.add(Globales.getInstance().jugador);
            Globales.getInstance().comunicacion.enviarMensajeServidor(new Transferencia("salir", lstObject, null));
            
        }
        
       // mainStage.close();
    }
    
    public void titulo(String titulo) {
        this.mainStage.setTitle(titulo);
    }
}
