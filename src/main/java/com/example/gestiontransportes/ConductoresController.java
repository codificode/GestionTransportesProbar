package com.example.gestiontransportes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.BreakIterator;

public class ConductoresController {
/*
    @FXML
    Button agregarConductor;

    @FXML
    Button editarConductor;
*/
    @FXML
    public void agregarConductor() throws IOException {
       ControlPantallas controlPantallas = ControlPantallas.getInstance();
       controlPantallas.cambioRootyTitulo("agregarConductor");
    }

    @FXML
    protected void consultarConductor() throws IOException{
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("consultarConductor");
    }

    @FXML
    protected void editarConductor() throws IOException{
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("pedirDniConductorParaEditar");
    }

    @FXML
    protected void borrarConductor() throws IOException{
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("pedirDniConductorParaBorrar");
    }

    @FXML
    protected void listarConductor(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaConductores.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Listado de conductores");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        ListaConductoresController controller = loader.getController();
        controller.listarConductores();

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("principal");
    }
}