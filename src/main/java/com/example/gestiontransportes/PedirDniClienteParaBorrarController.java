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

public class PedirDniClienteParaBorrarController {

    @FXML
    public TextField textFieldDniPedirDniClienteParaBorrar;
    @FXML
    public Button buttonBorrarPedirDniClienteParaBorrar;
    @FXML
    public Button buttonVolverPedirDniClienteParaBorrar;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void borrarClienteporDni(ActionEvent event) throws IOException {
        if (!gestion.existeCliente(textFieldDniPedirDniClienteParaBorrar.getText())){
            controlPantallas.mostrarPopup1(textFieldDniPedirDniClienteParaBorrar, "No existe un cliente con ese dni");
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("borrarCliente.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Borrar cliente");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            BorrarClienteController controller = loader.getController();
            controller.rellenarCampos(textFieldDniPedirDniClienteParaBorrar.getText().toString());

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("clientes");
    }
}
