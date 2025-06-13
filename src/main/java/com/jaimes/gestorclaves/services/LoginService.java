package com.jaimes.gestorclaves.services;

import com.jaimes.gestorclaves.models.UsuarioModel;

public interface LoginService {
    boolean autenticacion(UsuarioModel usuarioModel);
}
