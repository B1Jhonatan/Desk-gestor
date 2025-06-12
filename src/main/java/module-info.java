module com.jaimes.gestorclaves {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.jaimes.gestorclaves to javafx.fxml;
    exports com.jaimes.gestorclaves;
    exports com.jaimes.gestorclaves.controller;
}