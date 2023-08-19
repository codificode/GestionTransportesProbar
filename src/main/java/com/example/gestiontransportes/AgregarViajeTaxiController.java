package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgregarViajeTaxiController {
    @FXML
    public TextField textFieldFechaAgregarViajeTaxi;
    public TextField textFieldOrigenAgregarViajeTaxi;
    public TextField textFieldDestinoAgregarViajeTaxi;
    public ChoiceBox choiceBoxDniConductorAgregarViajeTaxi;
    public Button botonConfirmarAgregarViajeTaxi;
    public Button botonVolverAgregarViajeTaxi;
    public ChoiceBox choiceBoxMatriculaTaxiAgregarViajeTaxi;
    public TextField textFieldKilometrosAgregarViajeTaxi;
    @FXML
    public Label labelTarifaAgregarViajeTaxi;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas control = ControlPantallas.getInstance();

    // Crear un DateTimeFormatter con el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void initialize(){
        for (Conductor conductor: gestion.getListaConductores()){
            choiceBoxDniConductorAgregarViajeTaxi.getItems().add(conductor.getDni());
        }
        for (Vehiculo vehiculo: gestion.getListaVehiculos()){
            if (vehiculo.getTipo()== Vehiculo.Tipo.TAXI) {
                choiceBoxMatriculaTaxiAgregarViajeTaxi.getItems().add(vehiculo.getMatricula());
            }
        }
    }

    @FXML
    public void confirmar() throws IOException {
        try{
            // Parsear la cadena y obtener un objeto LocalDate
            LocalDate localDateFecha = LocalDate.parse(textFieldFechaAgregarViajeTaxi.getText(), formatter);


            int bandera = Gestion.getInstance().crearViajeTaxi(localDateFecha, textFieldOrigenAgregarViajeTaxi.getText(),
                    textFieldDestinoAgregarViajeTaxi.getText(), choiceBoxMatriculaTaxiAgregarViajeTaxi.getValue().toString(),
                    choiceBoxDniConductorAgregarViajeTaxi.getValue().toString(),
                    Integer.parseInt(textFieldKilometrosAgregarViajeTaxi.getText()));

            if (bandera == 0) {
                control.mostrarPopup1(textFieldDestinoAgregarViajeTaxi, "Viaje agregado");
                DecimalFormat df = new DecimalFormat("#.##");
                labelTarifaAgregarViajeTaxi.setText(df.format(gestion.obtenerViaje(gestion.getListaViajes().size()).getTarifa())+" €");
            } else if (bandera == 1) {
                control.mostrarPopup1(textFieldDestinoAgregarViajeTaxi, "El vehículo no existe");
            } else if (bandera == 2){
                control.mostrarPopup1(textFieldDestinoAgregarViajeTaxi, "El vehículo es un autobús");
            } else if (bandera == 3){
                control.mostrarPopup1(textFieldDestinoAgregarViajeTaxi, "No existe el conductor");
            } else if (bandera == 100){
                control.mostrarPopup1(textFieldDestinoAgregarViajeTaxi, "Error en el método crearViajeTaxi");
            }

        } catch (Exception e) {
            System.out.println("Seguramente has introducido mal los datos");
            control.mostrarPopup1(textFieldDestinoAgregarViajeTaxi, "Seguramente has introducido mal los datos");
        }


    }

    @FXML
    public void volver() throws IOException {
        control.cambioRootyTitulo("viajes");
    }
}
