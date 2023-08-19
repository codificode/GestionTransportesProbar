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

public class PedirIdViajeParaEditarController {
    @FXML
    public TextField textFieldIdViajePedirIdViajeParaEditar;
    @FXML
    public Button buttonVerEditarPedirIdViajeViajeParaEditar;
    @FXML
    public Button buttonVolverPedirIdViajeViajeParaEditar;
    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void editarViaje(ActionEvent event) throws IOException {
        if (!gestion.existeViaje(Integer.valueOf(textFieldIdViajePedirIdViajeParaEditar.getText()))){
            controlPantallas.mostrarPopup1(textFieldIdViajePedirIdViajeParaEditar, "No existe un Viaje con ese idViaje");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarViaje.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Editar Viaje");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            EditarViajeController controller = loader.getController();
            controller.rellenarCampos(Integer.valueOf(textFieldIdViajePedirIdViajeParaEditar.getText().toString()));

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
