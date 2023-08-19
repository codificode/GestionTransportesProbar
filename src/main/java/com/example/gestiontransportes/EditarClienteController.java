package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarClienteController {
    @FXML
    public Button buttonVolverEditarCliente;
    @FXML

    public Button buttonEditarEditarCliente;
    @FXML
    public TextField textFieldNombreEditarCliente;
    @FXML
    public TextField textFieldFechaNacimientoEditarCliente;
    @FXML

    public Label labelDniEditarCliente;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void rellenarCampos(String dni){
        String[] datos = new String[9];
        datos = gestion.consultarDatosCliente(dni);
        labelDniEditarCliente.setText(datos[0]);
        textFieldNombreEditarCliente.setText(datos[1]);
        textFieldFechaNacimientoEditarCliente.setText(datos[2]);
    }

    public void actualizarCampos (){

        LocalDate fechaNacimiento = LocalDate.parse(textFieldFechaNacimientoEditarCliente.getText(), formatter);
        int bandera = gestion.editarCliente(labelDniEditarCliente.getText(), textFieldNombreEditarCliente.getText(),
                fechaNacimiento);
        if (bandera==0){
            controlPantallas.mostrarPopup1(textFieldFechaNacimientoEditarCliente, "Cliente editado");
        } else {
            controlPantallas.mostrarPopup1(textFieldFechaNacimientoEditarCliente, "Error en los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("clientes");
    }
}
