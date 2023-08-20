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

public class PedirMatriculaVehiculoParaBorrarController {
    @FXML
    public TextField textFieldMatriculaPedirMatriculaVehiculoParaBorrar;
    @FXML
    public Button buttonBorrarPedirMatriculaVehiculoParaBorrar;
    @FXML
    public Button buttonVolverPedirMatriculaVehiculoParaBorrar;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void borrarVehiculoporMatricula(ActionEvent event) throws IOException {
        if (!gestion.existeVehiculo(textFieldMatriculaPedirMatriculaVehiculoParaBorrar.getText())){
            controlPantallas.mostrarPopup1(textFieldMatriculaPedirMatriculaVehiculoParaBorrar, "No existe un Vehiculo con ese Matricula");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("borrarVehiculo.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Borrar Vehiculo");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            BorrarVehiculoController controller = loader.getController();
            controller.rellenarCampos(textFieldMatriculaPedirMatriculaVehiculoParaBorrar.getText().toString());

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("vehiculos");
    }
}
