package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;

public class ListaViajesController {
    @FXML
    public TextFlow textFlowListadoViajesListaViajes;
    @FXML
    public Button buttonVolverListaViajes;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    @FXML
    public void listarViajes(){
        ArrayList<String> listaViajes = gestion.listarViajes();
        String lista1 = "";
        for (String viaje: listaViajes){
            lista1 = lista1.concat(viaje+"\n");
        }
        Text texto = new Text(lista1);
        textFlowListadoViajesListaViajes.getChildren().add(texto);

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
