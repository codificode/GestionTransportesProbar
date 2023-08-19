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

public class PedirDniConductorParaBorrarController {

    @FXML
    public TextField textFieldDniPedirDniConductorParaBorrar;
    @FXML
    public Button buttonBorrarPedirDniConductorParaBorrar;
    @FXML
    public Button buttonVolverPedirDniConductorParaBorrar;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void borrarConductorporDni(ActionEvent event) throws IOException {
        if (!gestion.existeConductor(textFieldDniPedirDniConductorParaBorrar.getText())){
            controlPantallas.mostrarPopup1(textFieldDniPedirDniConductorParaBorrar, "No existe un conductor con ese dni");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("borrarConductor.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Borrar conductor");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            BorrarConductorController controller = loader.getController();
            controller.rellenarCampos(textFieldDniPedirDniConductorParaBorrar.getText().toString());

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("conductores");
    }
}
