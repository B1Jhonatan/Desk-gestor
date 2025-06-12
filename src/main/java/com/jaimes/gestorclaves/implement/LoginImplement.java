package com.jaimes.gestorclaves.implement;

import com.jaimes.gestorclaves.models.UsuarioModel;
import com.jaimes.gestorclaves.services.LoginService;

public class LoginImplement implements LoginService {
    @Override
    public boolean autenticacion(String username, String password) {
        UsuarioModel usuarioModel = new UsuarioModel("Jhonatan", "1234");
        if (usuarioModel.getUsername().equals(username) && usuarioModel.getPassword().equals(password)){
            return true;
        } else {
            return false;
        }
    }
}
