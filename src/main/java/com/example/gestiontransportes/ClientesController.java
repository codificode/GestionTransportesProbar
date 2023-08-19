package com.example.gestiontransportes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientesController {

    @FXML
    protected void agregarCliente() throws IOException{
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("agregarCliente");
    }
    @FXML
    protected void consultarCliente() throws IOException{
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarCliente");
    }
    @FXML
    protected void listarCliente(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaClientes.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Listado de clientes");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        ListaClientesController controller = loader.getController();
        controller.listarClientes();


    }
    @FXML
    protected void editarCliente() throws IOException{
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("pedirDniClienteParaEditar");
    }
    @FXML
    protected void borrarCliente() throws IOException{
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("pedirDniClienteParaBorrar");
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("principal");
    }

}