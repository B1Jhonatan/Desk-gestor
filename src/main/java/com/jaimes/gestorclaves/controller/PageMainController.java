package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.Main;
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

import java.io.IOException;
import java.util.List;

public class PageMainController {

    public static Integer idUser;

    @FXML
    public TextField txtNombre, txtClave;

    @FXML
    public TableView<EncodeModel> tblClaves;

    @FXML
    public TableColumn<EncodeModel, String> tblNombre, tblClave, tblId;

    @FXML
    public Label lblIdClave, lblIdUsuario;

    ObservableList<EncodeModel> observableList = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        // Consultamos la base de datos y esta nos devuelve una "List<EncodeModel>"
        observableList.addAll(Conexion.getEncodeModel());
        // Agregamos la parte del "EncodeModel" que le corresponde a cada columna
        tblNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClave.setCellValueFactory(new PropertyValueFactory<>("encode"));
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        // Agregamos la lista a los items
        tblClaves.setItems(observableList);
    }

    /*public void idUser(Integer idUser){
        this.lblIdUsuario.setText(idUser.toString());
    }*/

    @FXML
    public void onClickAgregar(){
        String nombre = txtNombre.getText();
        String clave = txtClave.getText();
        Integer idUser = Integer.parseInt(lblIdUsuario.getText());
        EncodeModel encodeModel = new EncodeModel(null, nombre, clave, idUser);
        Conexion.savePassword(encodeModel);
        txtNombre.clear();
        txtClave.clear();
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel());
    }

    @FXML
    public void onClickActualizar(){
        String nombre = txtNombre.getText();
        String clave = txtClave.getText();
        Integer idUser = Integer.parseInt(lblIdUsuario.getText());
        Integer id = Integer.parseInt(lblIdClave.getText());
        EncodeModel encodeModel = new EncodeModel(id, nombre, clave, idUser);
        Conexion.updatePassword(encodeModel);
        txtNombre.clear();
        txtClave.clear();
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel());
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
        lblIdClave.setText(encodeModel.getId().toString());
    }

    @FXML
    public void onClickCerrarSecion() throws IOException {
        Main.changeScene("login-view.fxml", "Iniciar sesion", 450, 500);
    }
}
