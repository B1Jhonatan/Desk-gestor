package com.jaimes.gestorclaves.controller;

import com.jaimes.gestorclaves.Main;
import com.jaimes.gestorclaves.implement.SceneImplement;
import com.jaimes.gestorclaves.repository.Conexion;
import com.jaimes.gestorclaves.models.EncodeModel;
import com.jaimes.gestorclaves.services.SceneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class PageMainController {

    private final SceneService sceneService = new SceneImplement();

    private Integer idUser;

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

        // Agregamos la parte del "EncodeModel" que le corresponde a cada columna
        tblNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClave.setCellValueFactory(new PropertyValueFactory<>("encode"));
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        // Agregamos la lista a los items
        tblClaves.setItems(observableList);
        updateUserLabel();
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
        updateUserLabel(); // Actualizar cuando se establece el ID
    }

    private void updateUserLabel() {
        if(lblIdUsuario != null && idUser != null) {
            lblIdUsuario.setText(idUser.toString());
            observableList.addAll(Conexion.getEncodeModel(idUser));
        }
    }

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
        observableList.addAll(Conexion.getEncodeModel(idUser));
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
        observableList.addAll(Conexion.getEncodeModel(idUser));
    }

    @FXML
    public void onClickEliminar(){
        EncodeModel encodeModel = tblClaves.getSelectionModel().getSelectedItem();
        Conexion.deletePassword(encodeModel.getId());
        observableList.clear();
        observableList.addAll(Conexion.getEncodeModel(idUser));
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
        sceneService.changePage("login-view.fxml", "Iniciar sesion", 450, 500);
    }
}
