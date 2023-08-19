package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class DatosTaxiController {
    @FXML
    public TextFlow textFlowViajesDatosTaxi;
    @FXML
    public TextFlow textFlowConductoresDatosTaxi;
    @FXML
    public TextFlow textFlowClientesDatosTaxi;
    @FXML
    public Label labelMatriculaDatosTaxi;
    @FXML
    public Label labelFechaFabricacionDatosTaxi;
    @FXML
    public Label labelAntiguedadDatosTaxi;
    @FXML
    public Label labelTipoDatosTaxi;

    @FXML
    public Button buttonVolverDatosTaxi;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();


    public void mostrarDatos (String dni){

        String[] datos = new String[9];
        datos = gestion.consultarDatosVehiculo(dni);

        labelMatriculaDatosTaxi.setText(datos[0]);
        labelFechaFabricacionDatosTaxi.setText(datos[1]);
        //No hay 3 porque es la clase del autobus ni 2 porque es label fija indicando que es taxi
        labelAntiguedadDatosTaxi.setText(datos[4]);

        Text textoViajes = new Text(datos[5].replace(",","\n"));
        textFlowViajesDatosTaxi.getChildren().add(textoViajes);
        Text textoClientes = new Text(datos[6].replace(",","\n"));
        textFlowClientesDatosTaxi.getChildren().add(textoClientes);
        Text textoConductores = new Text(datos[7].replace(",","\n"));
        textFlowClientesDatosTaxi.getChildren().add(textoConductores);
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarVehiculo");
    }
}
