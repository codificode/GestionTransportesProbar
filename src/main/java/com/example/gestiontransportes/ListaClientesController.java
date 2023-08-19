package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;

public class ListaClientesController {

    @FXML
    public TextFlow textFlowListadoClientesListaClientes;
    @FXML
    public Button buttonVolverListaClientes;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    @FXML
    public void listarClientes(){
        ArrayList<String> listaClientes = gestion.listarClientes();
        String lista1 = "";
        for (String cliente: listaClientes){

            lista1 = lista1.concat(cliente+"\n");
        }
        Text texto = new Text(lista1);
        textFlowListadoClientesListaClientes.getChildren().add(texto);

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("clientes");
    }

}
