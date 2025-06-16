package com.jaimes.gestorclaves.services;

import com.jaimes.gestorclaves.models.UsuarioModel;

public interface LoginService {
    UsuarioModel autenticacion(UsuarioModel usuarioModel);
}
