package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class DatosViajeController {

    @FXML
    public TextFlow textFlowClientesDatosViaje;
    @FXML
    public Label labelIdViajeDatosViaje;
    @FXML
    public Label labelAntiguedadDatosViaje;
    @FXML
    public Label labelDestinoDatosViaje;
    @FXML
    public Label labelFechaDatosViaje;
    @FXML
    public Label labelMatriculaDatosViaje;
    @FXML
    public Label labelKilometrosDatosViaje;
    @FXML
    public Label labelDniViajeDatosViaje;
    @FXML
    public Label labelEstadoDatosViaje;
    @FXML
    public Label labelTarifaDatosViaje;
    @FXML
    public Label labelDniConductorDatosViaje;
    @FXML
    public Label labelTipoDatosViaje;
    @FXML
    public Label labelOrigenDatosViaje;
    public Label labelNumeroPasajerosDatosViaje;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();


    public void mostrarDatosViaje (int idViaje){

        String[] datos = new String[12];
        datos = gestion.consultarDatosViaje(idViaje);

        labelIdViajeDatosViaje.setText(datos[0]);
        labelMatriculaDatosViaje.setText(datos[1]);
        labelTipoDatosViaje.setText(datos[2]);
        labelFechaDatosViaje.setText(datos[3]);
        labelOrigenDatosViaje.setText(datos[4]);
        labelDestinoDatosViaje.setText(datos[5]);
        labelKilometrosDatosViaje.setText(datos[6]);
        labelDniConductorDatosViaje.setText(datos[7]);
        Text textoClientes = new Text(datos[8].replace(",","\n"));
        textFlowClientesDatosViaje.getChildren().add(textoClientes);
        labelTarifaDatosViaje.setText(datos[9]);
        labelEstadoDatosViaje.setText(datos[10]);
        labelNumeroPasajerosDatosViaje.setText(datos[11]);
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarViaje");
    }
    
}
