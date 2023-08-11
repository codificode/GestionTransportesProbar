package com.example.gestiontransportes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private AnchorPane principalid;

    ControlPantallas control = new ControlPantallas();

    @FXML
    protected void enviaraPantallaConductores() throws IOException {
        control.cambioRootyTitulo("conductores");
    }


    protected void metodoParaCambiarTexto() throws IOException{
        ConductoresController conductoresController = cargarControlador("Conductores.fxml");
        Platform.runLater(() -> {
            conductoresController.cambiartextoprueba("SOY LA PRUEBA");
        });
    }

    @FXML
    protected void enviaraPantallaClientes() throws IOException {
        control.cambioRootyTitulo("clientes");
    }

    @FXML
    protected void enviaraPantallaVehiculos() throws IOException {
        control.cambioRootyTitulo("vehiculos");
    }

    @FXML
    protected void enviaraPantallaViajes() throws IOException {
        control.cambioRootyTitulo("viajes");
    }

    @FXML
    protected void cerrarPrograma() throws IOException {
        Platform.exit();
    }

    private ConductoresController cargarControlador(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        principalid.getChildren().setAll(root);

        ConductoresController controller = loader.getController();
        return controller;
    }
}
