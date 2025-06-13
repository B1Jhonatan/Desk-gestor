package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.data_base.Conexion;
import com.jaimes.gestorclaves.models.EncodeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PageMainController {

    @FXML
    public TextField txtNombre, txtClave;

    @FXML
    public TableView<EncodeModel> tblClaves;

    @FXML
    public TableColumn<EncodeModel, String> tblNombre, tblClave;

    ObservableList<EncodeModel> observableList = FXCollections.observableArrayList();

    public void onClickAgregar(){
        String nombre = txtNombre.getText();
        String clave = txtClave.getText();
        EncodeModel encodeModel = new EncodeModel(nombre, clave);
        Conexion.savePassword(encodeModel);
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel());
    }

    @FXML
    public void initialize(){
        // Consultamos la base de datos y esta nos devuelve una "List<EncodeModel>"
        observableList.addAll(Conexion.getEncodeModel());
        // Agregamos la parte del "EncodeModel" que le corresponde a cada columna
        tblNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClave.setCellValueFactory(new PropertyValueFactory<>("encode"));
        // Agregamos la lista a los items
        tblClaves.setItems(observableList);
    }
}
