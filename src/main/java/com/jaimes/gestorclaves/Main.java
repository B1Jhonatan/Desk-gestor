package com.jaimes.gestorclaves;

import com.jaimes.gestorclaves.implement.SceneImplement;
import com.jaimes.gestorclaves.services.SceneService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage mainStage;

    private final SceneService sceneService = new SceneImplement();

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        sceneService.changePage("login-view.fxml", "Iniciar sesion", 450, 500);
        mainStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}