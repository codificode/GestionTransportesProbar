package com.example.gestiontransportes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.BreakIterator;

public class ConductoresController {

    @FXML
    String textoBoton;

    @FXML
    Button botonPrueba;

    @FXML
    Button botonPrueba2;

    @FXML
    Label etiquetaprueba;

    BotonModel botonModel;

    public void setBotonModel(BotonModel botonModel) {
        this.botonModel = botonModel;
    }


    @FXML
    public void cambiartextoprueba(String texto) {
        etiquetaprueba.setText("cambio 1");
        botonPrueba.setText("cambio 1");
        botonPrueba2.setText("cambio 1");
        System.out.println("Se ejecutó cambiartextoprueba");
    }

    @FXML
    protected void agregarConductor(){
       //ControlPantallas.cambioRootyTitulo("agregarConductor");
        //botonPrueba.setText("cambio 2");
        etiquetaprueba.setText("cambio 1");
    }

    @FXML
    protected void consultarConductor() {
        //botonPrueba.setText("cambio 3");

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("principal");
    }
}