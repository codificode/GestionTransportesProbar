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

public class AgregarViajeAutobusController {


    public TextField textFieldFechaAgregarViajeAutobus;
    public TextField textFieldOrigenAgregarViajeAutobus;
    public TextField textFieldDestinoAgregarViajeAutobus;
    public ChoiceBox choiceBoxDniConductorAgregarViajeAutobus;
    public Button botonConfirmarAgregarViajeAutobus;
    public Button botonVolverAgregarViajeAutobus;
    public ChoiceBox choiceBoxMatriculaAutobusAgregarViajeAutobus;
    public TextField textFieldKilometrosAgregarViajeAutobus;
    public Label labelTarifaAgregarViajeAutobus;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas control = ControlPantallas.getInstance();

    // Crear un DateTimeFormatter con el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void initialize(){
        for (Conductor conductor: gestion.getListaConductores()){
            if (conductor.getTipoCarnet()== Conductor.TipoCarnet.AB) {
                choiceBoxDniConductorAgregarViajeAutobus.getItems().add(conductor.getDni());
            }
        }
        for (Vehiculo vehiculo: gestion.getListaVehiculos()){
            if (vehiculo.getTipo()== Vehiculo.Tipo.AUTOBUS){
                choiceBoxMatriculaAutobusAgregarViajeAutobus.getItems().add(vehiculo.getMatricula());
            }
        }
    }

    @FXML
    public void confirmar() throws IOException {
        try{
            // Parsear la cadena y obtener un objeto LocalDate
            LocalDate localDateFecha = LocalDate.parse(textFieldFechaAgregarViajeAutobus.getText(), formatter);


            int bandera = Gestion.getInstance().crearViajeAutobus(localDateFecha, textFieldOrigenAgregarViajeAutobus.getText(),
                    textFieldDestinoAgregarViajeAutobus.getText(), choiceBoxMatriculaAutobusAgregarViajeAutobus.getValue().toString(),
                    choiceBoxDniConductorAgregarViajeAutobus.getValue().toString(),
                    Integer.parseInt(textFieldKilometrosAgregarViajeAutobus.getText()));

            if (bandera == 0) {
                control.mostrarPopup1(textFieldDestinoAgregarViajeAutobus, "Viaje agregado");
                DecimalFormat df = new DecimalFormat("#.##");
                labelTarifaAgregarViajeAutobus.setText(df.format(gestion.obtenerViaje(gestion.getListaViajes().size()).getTarifa())+" €");
            } else if (bandera == 1) {
                control.mostrarPopup1(textFieldDestinoAgregarViajeAutobus, "El vehículo no existe");
            } else if (bandera == 2){
                control.mostrarPopup1(textFieldDestinoAgregarViajeAutobus, "El vehículo es un taxi");
            } else if (bandera == 3){
            control.mostrarPopup1(textFieldDestinoAgregarViajeAutobus, "No existe el conductor");
            } else if (bandera == 4){
            control.mostrarPopup1(textFieldDestinoAgregarViajeAutobus, "El conductor no tiene carnet válido");
            }

        } catch (Exception e) {
            System.out.println("Seguramente has introducido mal los datos");
            control.mostrarPopup1(textFieldDestinoAgregarViajeAutobus, "Seguramente has introducido mal los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        control.cambioRootyTitulo("viajes");
    }
}
