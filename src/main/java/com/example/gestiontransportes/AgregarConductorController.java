package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgregarConductorController {

    @FXML
    TextField textFieldDniAgregarConductor;

    @FXML
    TextField textFieldNombreAgregarConductor;

    @FXML
    TextField textFieldFechaNacimientoAgregarConductor;

    @FXML
    TextField textFieldFechaCarnetAgregarConductor;

    @FXML
    ChoiceBox choiceBoxTipoCarnetAgregarConductor;

    ControlPantallas control = ControlPantallas.getInstance();

    // Crear un DateTimeFormatter con el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void initialize() {
        choiceBoxTipoCarnetAgregarConductor.getItems().addAll("B", "AB");
    }

    @FXML
    public void confirmar() throws IOException {

        System.out.println("Entro en el método");
        try {
        // Parsear la cadena y obtener un objeto LocalDate
        LocalDate localDateNacimiento = LocalDate.parse(textFieldFechaNacimientoAgregarConductor.getText(), formatter);
        LocalDate localDateCarnet = LocalDate.parse(textFieldFechaNacimientoAgregarConductor.getText(), formatter);
        //Paso la captura del choiceBox a TipoCarnet
        Conductor.TipoCarnet carnet = Conductor.TipoCarnet.valueOf((String) choiceBoxTipoCarnetAgregarConductor.getValue().toString());


            int bandera = Gestion.getInstance().crearConductor(textFieldDniAgregarConductor.getText(), textFieldNombreAgregarConductor.getText(),
                    localDateNacimiento, localDateCarnet, carnet);

            if (bandera == 0) {
                System.out.println("He guardado el tío");
                control.mostrarPopup1(textFieldDniAgregarConductor, "Conductor agregado");
            } else if (bandera == 1) {
                System.out.println("Ya existía");
                control.mostrarPopup1(textFieldDniAgregarConductor, "El conductor ya existe");
            } else {
                System.out.println("Error de datos");
                control.mostrarPopup1(textFieldDniAgregarConductor, "Error de datos");
            }
        } catch (Exception e) {
            System.out.println("Seguramente has introducido mal los datos");
            control.mostrarPopup1(textFieldDniAgregarConductor, "Seguramente has introducido mal los datos");
        }
    }
    @FXML
    public void volver() throws IOException {
        control.cambioRootyTitulo("conductores");
    }
}
