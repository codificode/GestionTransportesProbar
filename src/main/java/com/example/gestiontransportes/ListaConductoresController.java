package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;

public class ListaConductoresController {
    @FXML
    public TextFlow textFlowListadoConductoresListaConductores;
    @FXML
    public Button buttonVolverListaConductores;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    @FXML
    public void listarConductores(){
        ArrayList<String> listaConductores = gestion.listarConductores();
        String lista1 = "";
        for (String conductor: listaConductores){

            lista1 = lista1.concat(conductor+"\n");
        }
        Text texto = new Text(lista1);
        textFlowListadoConductoresListaConductores.getChildren().add(texto);

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("conductores");
    }

}
