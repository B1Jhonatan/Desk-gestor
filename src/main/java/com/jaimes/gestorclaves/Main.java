package com.jaimes.gestorclaves;

import com.jaimes.gestorclaves.data_base.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        changeScene("login-view.fxml", "Iniciar sesion", 450, 500);
        mainStage.show();
    }

    public static void changeScene(String name, String title, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(name));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);
        mainStage.setResizable(false);
        mainStage.setTitle(title);
        mainStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}