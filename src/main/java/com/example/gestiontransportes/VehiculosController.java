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

public class VehiculosController {

    @FXML
    Button agregarVehiculo;

    @FXML
    Button editarVehiculo;

    @FXML
    public void agregarVehiculo() throws IOException {
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("agregarVehiculo");
    }

    @FXML
    protected void consultarVehiculo() throws IOException{
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("consultarVehiculo");
    }

    @FXML
    protected void editarVehiculo() throws IOException{
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("pedirMatriculaVehiculoParaEditar");
    }

    @FXML
    protected void borrarVehiculo() throws IOException{
        ControlPantallas controlPantallas = ControlPantallas.getInstance();
        controlPantallas.cambioRootyTitulo("pedirMatriculaVehiculoParaBorrar");
    }

    @FXML
    protected void listarVehiculo(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaVehiculos.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Listado de vehículos");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        ListaVehiculosController controller = loader.getController();
        controller.listarVehiculos();
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("principal");
    }
}
