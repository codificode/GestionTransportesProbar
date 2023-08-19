package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;

public class ListaVehiculosController {
    @FXML
    public TextFlow textFlowListadoVehiculosListaVehiculos;
    @FXML
    public Button buttonVolverListaVehiculos;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    @FXML
    public void listarVehiculos(){
        ArrayList<String> listaVehiculos = gestion.listarVehiculos();
        String lista1 = "";
        for (String vehiculo: listaVehiculos){

            lista1 = lista1.concat(vehiculo+"\n");
        }
        Text texto = new Text(lista1);
        textFlowListadoVehiculosListaVehiculos.getChildren().add(texto);

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("Vehiculos");
    }
}
