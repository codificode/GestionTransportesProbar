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

public class PedirIdViajeParaCancelarController {
    @FXML
    public TextField textFieldIdViajePedirIdViajeParaCancelar;
    @FXML
    public Button buttonVerCancelarPedirIdViajeViajeParaCancelar;
    @FXML
    public Button buttonVolverPedirIdViajeViajeParaCancelar;
    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void cancelarViaje(ActionEvent event) throws IOException {
        if (!gestion.existeViaje(Integer.valueOf(textFieldIdViajePedirIdViajeParaCancelar.getText()))){
            controlPantallas.mostrarPopup1(textFieldIdViajePedirIdViajeParaCancelar, "No existe un Viaje con ese idViaje");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("cancelarViaje.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Cancelar Viaje");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            CancelarViajeController controller = loader.getController();
            controller.rellenarCampos(Integer.valueOf(textFieldIdViajePedirIdViajeParaCancelar.getText().toString()));

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
