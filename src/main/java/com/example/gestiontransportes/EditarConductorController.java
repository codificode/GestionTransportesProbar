package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarConductorController {
    @FXML
    public Button buttonVolverEditarConductor;
    @FXML

    public Button buttonEditarEditarConductor;
    @FXML
    public TextField textFieldNombreEditarConductor;
    @FXML
    public TextField textFieldFechaNacimientoEditarConductor;
    @FXML
    public TextField textFieldFechaCarnetEditarConductor;
    @FXML
    public ChoiceBox choiceBoxTipoCarnetEditarConductor;
    @FXML
    public Label labelDniEditarConductor;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void rellenarCampos(String dni){
        String[] datos = new String[9];
        datos = gestion.consultarDatosConductor(dni);
        labelDniEditarConductor.setText(datos[0]);
        textFieldNombreEditarConductor.setText(datos[1]);
        textFieldFechaNacimientoEditarConductor.setText(datos[2]);
        textFieldFechaCarnetEditarConductor.setText(datos[3]);
        choiceBoxTipoCarnetEditarConductor.getItems().addAll(Conductor.TipoCarnet.B, Conductor.TipoCarnet.AB);
        choiceBoxTipoCarnetEditarConductor.setValue(datos[6]);
    }

    public void actualizarCampos (){

        LocalDate fechaNacimiento = LocalDate.parse(textFieldFechaNacimientoEditarConductor.getText(), formatter);
        LocalDate fechaCarnet = LocalDate.parse(textFieldFechaCarnetEditarConductor.getText(), formatter);
        Conductor.TipoCarnet tipoCarnet = Conductor.TipoCarnet.valueOf(choiceBoxTipoCarnetEditarConductor.getValue().toString());
        int bandera = gestion.editarConductor(labelDniEditarConductor.getText(), textFieldNombreEditarConductor.getText(),
                fechaNacimiento, fechaCarnet, tipoCarnet);
        if (bandera==0){
            controlPantallas.mostrarPopup1(textFieldFechaCarnetEditarConductor, "Conductor editado");
        } else {
            controlPantallas.mostrarPopup1(textFieldFechaCarnetEditarConductor, "Error en los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("conductores");
    }
}
