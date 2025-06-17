package com.jaimes.gestorclaves.services;

import java.io.IOException;

public interface SceneService {
    void changePage(String name, String title, int ancho, int alto) throws IOException;
    void changePage(String name, String title, int ancho, int alto, Integer idUser) throws IOException;
}
