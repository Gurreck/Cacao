module org.una.cacao_cliente {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.una.cacao_cliente to javafx.fxml;
    exports org.una.cacao_cliente;
}
