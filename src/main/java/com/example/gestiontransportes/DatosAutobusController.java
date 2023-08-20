package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class DatosAutobusController {
    @FXML
    public TextFlow textFlowViajesDatosAutobus;
    @FXML
    public TextFlow textFlowConductoresDatosAutobus;
    @FXML
    public TextFlow textFlowClientesDatosAutobus;
    @FXML
    public Label labelMatriculaDatosAutobus;
    @FXML
    public Label labelFechaFabricacionDatosAutobus;
    @FXML
    public Label labelAntiguedadDatosAutobus;
    @FXML
    public Label labelTipoDatosAutobus;
    @FXML
    public Label labelClaseDatosAutobus;

    @FXML
    public Button buttonVolverDatosAutobus;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();


    public void mostrarDatos (String matricula){

        String[] datos = new String[9];
        datos = gestion.consultarDatosVehiculo(matricula);

        labelMatriculaDatosAutobus.setText(datos[0]);
        labelFechaFabricacionDatosAutobus.setText(datos[1]);
        labelClaseDatosAutobus.setText(datos[3]);
        labelAntiguedadDatosAutobus.setText(datos[4]);

        Text textoViajes = new Text(datos[5].replace(",","\n"));
        textFlowViajesDatosAutobus.getChildren().add(textoViajes);
        Text textoClientes = new Text(datos[6].replace(",","\n"));
        textFlowClientesDatosAutobus.getChildren().add(textoClientes);
        Text textoConductores = new Text(datos[7].replace(",","\n"));
        textFlowClientesDatosAutobus.getChildren().add(textoConductores);
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarVehiculo");
    }
}
