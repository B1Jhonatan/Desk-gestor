package com.jaimes.gestorclaves.implement;

import com.jaimes.gestorclaves.models.UsuarioModel;
import com.jaimes.gestorclaves.repository.Conexion;
import com.jaimes.gestorclaves.services.LoginService;

import java.util.List;


public class LoginImplement implements LoginService {
    @Override
    public UsuarioModel autenticacion(UsuarioModel usuarioModel) {
        List<UsuarioModel> usuarioModels = Conexion.getUsuariosModel();
        for(UsuarioModel um : usuarioModels) {
            if (um.getUsername().equals(usuarioModel.getUsername()) &&
                    um.getPassword().equals(usuarioModel.getPassword())) {
                return um;
            }
        }
        return null;
    }

}