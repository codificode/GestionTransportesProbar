package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgregarClienteController {


    @FXML
    TextField textFieldDniAgregarCliente;

    @FXML
    TextField textFieldNombreAgregarCliente;

    @FXML
    TextField textFieldFechaNacimientoAgregarCliente;

    ControlPantallas control = ControlPantallas.getInstance();

    // Crear un DateTimeFormatter con el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @FXML
    public void confirmar() throws IOException {

        try {
            // Parsear la cadena y obtener un objeto LocalDate
            LocalDate localDateNacimiento = LocalDate.parse(textFieldFechaNacimientoAgregarCliente.getText(), formatter);
            
            int bandera = Gestion.getInstance().crearCliente(textFieldDniAgregarCliente.getText(), textFieldNombreAgregarCliente.getText(),
                    localDateNacimiento);

            if (bandera == 0) {
                control.mostrarPopup1(textFieldDniAgregarCliente, "Cliente agregado");
            } else if (bandera == 1) {
                control.mostrarPopup1(textFieldDniAgregarCliente, "El Cliente ya existe");
            } else {
                control.mostrarPopup1(textFieldDniAgregarCliente, "Error de datos");
            }
        } catch (Exception e) {
            control.mostrarPopup1(textFieldDniAgregarCliente, "Seguramente has introducido mal los datos");
        }
    }
    @FXML
    public void volver() throws IOException {
        control.cambioRootyTitulo("clientes");
    }    
    
}
