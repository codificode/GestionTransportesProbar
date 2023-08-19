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

public class PedirDniClienteParaEditarController {
    @FXML
    public TextField textFieldDniPedirDniClienteParaEditar;
    @FXML
    public Button buttonVerEditarPedirDniClienteParaEditar;
    @FXML
    public Button buttonVolverPedirDniClienteParaEditar;
    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void editarClienteporDni(ActionEvent event) throws IOException {
        if (!gestion.existeCliente(textFieldDniPedirDniClienteParaEditar.getText())){
            controlPantallas.mostrarPopup1(textFieldDniPedirDniClienteParaEditar, "No existe un cliente con ese dni");
        } else {
            //controlPantallas.cambioRootyTitulo("editarCliente");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarCliente.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Editar cliente");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            EditarClienteController controller = loader.getController();
            controller.rellenarCampos(textFieldDniPedirDniClienteParaEditar.getText().toString());

        }
    }
    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("clientes");
    }
}
