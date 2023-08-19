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

public class ViajesController {



    @FXML
    protected void agregarViajeAutobus() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("agregarViajeAutobus");

    }
    @FXML
    protected void agregarViajeTaxi() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("agregarViajeTaxi");
    }

    @FXML
    protected void consultarViaje() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("consultarViaje");
    }

    @FXML
    protected void editarViaje() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("pedirIdViajeParaEditar");
    }

    @FXML
    protected void cancelarViaje() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("pedirIdViajeParaCancelar");
    }

    @FXML
    protected void listarViajes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaViajes.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Listado de viajes");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        ListaViajesController controller = loader.getController();
        controller.listarViajes();
    }

    @FXML
    protected void volver() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("principal");
    }

    @FXML
    protected void agregarClienteaViaje() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("consultarViajesParaAgregarCliente");
    }

    @FXML
    protected void eliminarClientedeViaje() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("consultarViajesParaEliminarCliente");
    }

}

