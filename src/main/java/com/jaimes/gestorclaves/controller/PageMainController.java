package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.repository.Conexion;
import com.jaimes.gestorclaves.models.EncodeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PageMainController {

    @FXML
    public TextField txtNombre, txtClave;

    @FXML
    public Label lblId;

    @FXML
    public TableView<EncodeModel> tblClaves;

    @FXML
    public TableColumn<EncodeModel, String> tblNombre, tblClave, tblId;

    ObservableList<EncodeModel> observableList = FXCollections.observableArrayList();

    public void onClickAgregar(){
        String nombre = txtNombre.getText();
        String clave = txtClave.getText();
        EncodeModel encodeModel = new EncodeModel(clave, nombre,null);
        Conexion.savePassword(encodeModel);
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel());
        txtNombre.clear();
        txtClave.clear();
    }

    @FXML
    public void initialize(){
        // Consultamos la base de datos y esta nos devuelve una "List<EncodeModel>"
        observableList.addAll(Conexion.getEncodeModel());
        // Agregamos la parte del "EncodeModel" que le corresponde a cada columna
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClave.setCellValueFactory(new PropertyValueFactory<>("encode"));
        // Agregamos la lista a los items
        tblClaves.setItems(observableList);
    }

    @FXML
    public void onClickEliminar(){
        EncodeModel encodeModel = tblClaves.getSelectionModel().getSelectedItem();
        Conexion.deletePassword(encodeModel.getId());
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel());
    }

    @FXML
    public void onClickModificar(){
        EncodeModel encodeModel = tblClaves.getSelectionModel().getSelectedItem();
        txtClave.setText(encodeModel.getEncode());
        txtNombre.setText(encodeModel.getName());
        lblId.setText(encodeModel.getId().toString());
    }

    @FXML
    public void onClickActualizar(){
        String nombre = txtNombre.getText();
        String clave = txtClave.getText();
        Integer id = Integer.parseInt(lblId.getText());
        EncodeModel encodeModel = new EncodeModel(clave, nombre, id);
        Conexion.updatePassword(encodeModel);
        txtNombre.clear();
        txtClave.clear();
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel());
    }
}
