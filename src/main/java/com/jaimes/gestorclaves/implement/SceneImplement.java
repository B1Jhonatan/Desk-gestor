package com.jaimes.gestorclaves.implement;

import com.jaimes.gestorclaves.Main;
import com.jaimes.gestorclaves.controller.PageMainController;
import com.jaimes.gestorclaves.services.SceneService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneImplement implements SceneService {
    @Override
    public void changePage(String name, String title, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(name));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);
        Main.mainStage.setResizable(false);
        Main.mainStage.setTitle(title);
        Main.mainStage.setScene(scene);
    }

    @Override
    public void changePage(String name, String title, int ancho, int alto, Integer idUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(name));
        Parent root = fxmlLoader.load();
        PageMainController pc = fxmlLoader.getController();
        pc.setIdUser(idUser);
        Scene scene = new Scene(root, ancho, alto);
        Main.mainStage.setResizable(false);
        Main.mainStage.setTitle(title);
        Main.mainStage.setScene(scene);
    }
}
