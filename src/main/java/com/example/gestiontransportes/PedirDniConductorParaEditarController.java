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

public class PedirDniConductorParaEditarController {
    @FXML
    public TextField textFieldDniPedirDniConductorParaEditar;
    @FXML
    public Button buttonVerEditarPedirDniConductorParaEditar;
    @FXML
    public Button buttonVolverPedirDniConductorParaEditar;
    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void editarConductorporDni(ActionEvent event) throws IOException {
        if (!gestion.existeConductor(textFieldDniPedirDniConductorParaEditar.getText())){
            controlPantallas.mostrarPopup1(textFieldDniPedirDniConductorParaEditar, "No existe un Conductor con ese dni");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarConductor.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Editar Conductor");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            EditarConductorController controller = loader.getController();
            controller.rellenarCampos(textFieldDniPedirDniConductorParaEditar.getText().toString());

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("conductores");
    }
}
