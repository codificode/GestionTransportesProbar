package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgregarVehiculoController {

    @FXML
    TextField textFieldMatriculaAgregarVehiculo;
    @FXML
    ChoiceBox choiceBoxTipoAgregarVehiculo;
    @FXML
    ChoiceBox choiceBoxClaseAgregarVehiculo;
    @FXML
    TextField textFieldFechaFabricacionAgregarVehiculo;


    ControlPantallas control = ControlPantallas.getInstance();

    // Crear un DateTimeFormatter con el formato deseado
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void initialize() {
        choiceBoxClaseAgregarVehiculo.getItems().addAll("SUPRA", "NORMAL");
        choiceBoxTipoAgregarVehiculo.getItems().addAll("AUTOBUS", "TAXI");
    }

    @FXML
    public void confirmar() throws IOException {

        try{

            // Parsear la cadena y obtener un objeto LocalDate
            LocalDate localDateFabricacion = LocalDate.parse(textFieldFechaFabricacionAgregarVehiculo.getText(), formatter);
            //Paso la captura del choiceBox a TipoCarnet
            Vehiculo.Tipo tipo = Vehiculo.Tipo.valueOf((String) choiceBoxTipoAgregarVehiculo.getValue().toString());


            int bandera;
            if (tipo== Vehiculo.Tipo.AUTOBUS){
                Autobus.Clase clase = Autobus.Clase.valueOf((String) choiceBoxClaseAgregarVehiculo.getValue().toString());
                bandera = Gestion.getInstance().crearVehiculo(textFieldMatriculaAgregarVehiculo.getText(),
                    localDateFabricacion, clase);
            } else {
                bandera = Gestion.getInstance().crearVehiculo(textFieldMatriculaAgregarVehiculo.getText(),
                        localDateFabricacion);
            }
            if (bandera == 0) {
                control.mostrarPopup1(textFieldMatriculaAgregarVehiculo, "Vehiculo agregado");
            } else if (bandera == 1) {
                control.mostrarPopup1(textFieldMatriculaAgregarVehiculo, "El Vehiculo ya existe");
            } else {
                control.mostrarPopup1(textFieldMatriculaAgregarVehiculo, "Error de datos");
            }

        } catch (Exception e) {
            System.out.println("Seguramente has introducido mal los datos");
            control.mostrarPopup1(textFieldMatriculaAgregarVehiculo, "Seguramente has introducido mal los datos");
        }
    }
    @FXML
    public void volver() throws IOException {
        control.cambioRootyTitulo("vehiculos");
    }
}
