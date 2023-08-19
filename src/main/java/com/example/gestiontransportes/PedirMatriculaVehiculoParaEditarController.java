package com.example.gestiontransportes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PedirMatriculaVehiculoParaEditarController {
    @FXML
    public TextField textFieldMatriculaPedirMatriculaVehiculoParaEditar;
    @FXML
    public Button buttonVerEditarPedirMatriculaVehiculoParaEditar;
    @FXML
    public Button buttonVolverPedirMatriculaVehiculoParaEditar;
    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void editarVehiculoporMatricula(ActionEvent event) throws IOException {
        if (!gestion.existeVehiculo(textFieldMatriculaPedirMatriculaVehiculoParaEditar.getText())){
            controlPantallas.mostrarPopup1(textFieldMatriculaPedirMatriculaVehiculoParaEditar, "No existe un Vehiculo con ese Matricula");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarVehiculo.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Editar Vehiculo");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            EditarVehiculoController controller = loader.getController();
            controller.rellenarCampos(textFieldMatriculaPedirMatriculaVehiculoParaEditar.getText().toString());

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("vehiculos");
    }
}
