package com.jaimes.gestorclaves.services;

import com.jaimes.gestorclaves.models.UsuarioModel;

import java.io.IOException;

public interface LoginService {
    UsuarioModel autenticacion(UsuarioModel usuarioModel);
}
