package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.Main;
import com.jaimes.gestorclaves.implement.LoginImplement;
import com.jaimes.gestorclaves.implement.SceneImplement;
import com.jaimes.gestorclaves.models.UsuarioModel;
import com.jaimes.gestorclaves.repository.Conexion;
import com.jaimes.gestorclaves.services.LoginService;
import com.jaimes.gestorclaves.services.SceneService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private final LoginService loginService = new LoginImplement();
    private final SceneService sceneService = new SceneImplement();

    @FXML
    public TextField txtUsername, txtPassword;

    @FXML
    public Button btnLogin;

    @FXML
    public void onClickLoginAutenticacion() throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        UsuarioModel usuarioModel = new UsuarioModel(null, username, password);
        UsuarioModel usuarioAuten = loginService.autenticacion(usuarioModel);
        if (usuarioAuten != null){
            System.out.println(usuarioAuten.getId());
            sceneService.changePage("page-main.fxml", "Gestor", 800, 500, usuarioAuten.getId());
        } else {
            System.out.println("Usuario y contrasenha incorrectos");
        }
    }

    public void onClickSaveUser(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        UsuarioModel usuarioModel = new UsuarioModel(null, username, password);
        Conexion.saveUsers(usuarioModel);
    }

    public void onClickLogin() throws IOException {
        sceneService.changePage("login-view.fxml", "Iniciar sesion", 450, 500);
    }

    public void onClickCrear() throws IOException {
        sceneService.changePage("crear-view.fxml", "Crear usuario", 450, 500);
    }

}
