package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class DatosConductorController {
    @FXML
    public TextFlow textFlowViajesRealizadosDatosConductor;
    @FXML
    public TextFlow textFlowVehiculosConducidosDatosConductor;
    @FXML
    public Label labelNombreDatosConductor;
    @FXML
    public Label labelFechaNacimientoDatosConductor;
    @FXML
    public Label labelExperienciaDatosConductor;
    @FXML
    public Label labelFechaCarnetDatosConductor;
    @FXML
    public Label labelCarnetDatosConductor;
    @FXML
    public Label labelDniDatosConductor;
    @FXML
    public Label labelEdadDatosConductor;
    @FXML
    public Button buttonVolverDatosConductor;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();


    public void mostrarDatosConductor (String dni){

        String[] datos = new String[9];
        datos = gestion.consultarDatosConductor(dni);

        labelDniDatosConductor.setText(datos[0]);
        labelNombreDatosConductor.setText(datos[1]);
        labelFechaNacimientoDatosConductor.setText(datos[2]);
        labelFechaCarnetDatosConductor.setText(datos[3]);
        labelEdadDatosConductor.setText(datos[4]);
        labelExperienciaDatosConductor.setText(datos[5]);
        labelCarnetDatosConductor.setText(datos[6]);
        Text textoVehiculos = new Text(datos[7].replace(",","\n"));
        textFlowVehiculosConducidosDatosConductor.getChildren().add(textoVehiculos);
        Text textoViajes = new Text(datos[8].replace(",","\n"));
        textFlowViajesRealizadosDatosConductor.getChildren().add(textoViajes);
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarConductor");
    }
}
