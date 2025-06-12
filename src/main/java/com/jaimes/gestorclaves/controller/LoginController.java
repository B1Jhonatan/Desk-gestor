package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.Main;
import com.jaimes.gestorclaves.implement.LoginImplement;
import com.jaimes.gestorclaves.models.UsuarioModel;
import com.jaimes.gestorclaves.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private final LoginService loginService = new LoginImplement();

    @FXML
    public TextField txtUsername, txtPassword;

    @FXML
    public Button btnLogin;

    public void onClickLoginAutenticacion() throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        UsuarioModel usuarioModel = new UsuarioModel(1,"Jhonatan", "1234");
        if (loginService.autenticacion(username, password)){
            Main.changeScene("page-main.fxml", "Gestor", 800, 500);
        } else {
            System.out.println("Usuario y contrasenha incorrectos");
        }
    }

}
