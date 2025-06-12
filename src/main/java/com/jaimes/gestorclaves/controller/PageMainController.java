package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.data_base.Conexion;
import com.jaimes.gestorclaves.models.EncodeModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PageMainController {

    @FXML
    public TextField txtNombre, txtClave;

    public void onClickAgregar(){
        String nombre = txtNombre.getText();
        String clave = txtClave.getText();
        EncodeModel encodeModel = new EncodeModel(nombre, clave);
        Conexion.savePassword(encodeModel);
    }

}
