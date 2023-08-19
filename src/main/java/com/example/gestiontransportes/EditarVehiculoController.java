package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarVehiculoController {
    @FXML
    public Button buttonVolverEditarVehiculo;
    @FXML

    public Button buttonEditarEditarVehiculo;
    @FXML
    public Label labelMatriculaEditarVehiculo;
    @FXML
    public TextField textFieldFechaFabricacionEditarVehiculo;
    @FXML
    public Label labelTipoEditarVehiculo;
    @FXML
    public ChoiceBox choiceBoxClaseEditarVehiculo;


    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void rellenarCampos(String Matricula){
        String[] datos = new String[9];
        datos = gestion.consultarDatosVehiculo(Matricula);
        labelMatriculaEditarVehiculo.setText(datos[0]);
        textFieldFechaFabricacionEditarVehiculo.setText(datos[1]);
        labelTipoEditarVehiculo.setText(datos[2]);
        choiceBoxClaseEditarVehiculo.getItems().addAll(Autobus.Clase.SUPRA, Autobus.Clase.NORMAL);
        choiceBoxClaseEditarVehiculo.setValue(datos[3]);
    }

    public void actualizarCampos (){
        LocalDate fechaFabricacion = LocalDate.parse(textFieldFechaFabricacionEditarVehiculo.getText(), formatter);
        Vehiculo.Tipo tipo = Vehiculo.Tipo.valueOf(labelTipoEditarVehiculo.getText().toString());
        Autobus.Clase clase = Autobus.Clase.valueOf(choiceBoxClaseEditarVehiculo.getValue().toString());
        int bandera = gestion.editarVehiculo(labelMatriculaEditarVehiculo.getText(),
                tipo, clase, fechaFabricacion);
        System.out.println("Nuevos datos2");
        System.out.println(""+labelMatriculaEditarVehiculo.getText()+tipo+clase+fechaFabricacion);
        if (bandera==0){
            controlPantallas.mostrarPopup1(textFieldFechaFabricacionEditarVehiculo, "Vehiculo editado");
        } else {
            controlPantallas.mostrarPopup1(textFieldFechaFabricacionEditarVehiculo, "Error en los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("vehiculos");
    }
}
