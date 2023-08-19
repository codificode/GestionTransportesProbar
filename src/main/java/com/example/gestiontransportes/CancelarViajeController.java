package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CancelarViajeController {
    @FXML
    public Label labelIdViajeCancelarViaje;
    @FXML
    public Label labelDestinoCancelarViaje;
    @FXML
    public Label labelOrigenCancelarViaje;
    @FXML
    public Label labelFechaCancelarViaje;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void rellenarCampos(int idViaje){
        String[] datos = new String[11];
        datos = gestion.consultarDatosViaje(idViaje);
        labelIdViajeCancelarViaje.setText(datos[0]);
        labelFechaCancelarViaje.setText(datos[3]);
        labelOrigenCancelarViaje.setText(datos[4]);
        labelDestinoCancelarViaje.setText(datos[5]);
    }

    public void cancelarViaje (){

        int idViaje = Integer.parseInt(labelIdViajeCancelarViaje.getText());

        int bandera = gestion.cancelarViaje(idViaje);

        if (bandera==0){
            controlPantallas.mostrarPopup1(labelDestinoCancelarViaje, "Viaje cancelado");
        } else if (bandera == 1){
            controlPantallas.mostrarPopup1(labelDestinoCancelarViaje, "No existe un viaje con ese idViaje");
        } else if (bandera == 2){
            controlPantallas.mostrarPopup1(labelDestinoCancelarViaje, "El viaje ya estaba cancelado");
        } else if (bandera == 3){
            controlPantallas.mostrarPopup1(labelDestinoCancelarViaje, "El viaje tiene estado FINALIZADO");
        }
        this.rellenarCampos(idViaje);
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
    
}
