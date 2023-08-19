package com.example.gestiontransportes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConsultarVehiculoController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField textFieldMatriculaConsultarVehiculo;
    @FXML
    public ChoiceBox choiceBoxTipoConsultarVehiculo;
    @FXML
    public ChoiceBox choiceBoxClaseConsultarVehiculo;
    @FXML
    public TextField textFieldFechaFabricacionConsultarVehiculo;

    @FXML
    public ChoiceBox choiceBoxCoincidenciasConsultarVehiculo;


    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    String matricula;
    LocalDate localDateFabricacion;
    Vehiculo.Tipo tipo;
    Autobus.Clase clase;

    public void initialize() {
        choiceBoxTipoConsultarVehiculo.getItems().addAll(null, Vehiculo.Tipo.AUTOBUS, Vehiculo.Tipo.TAXI);
        choiceBoxClaseConsultarVehiculo.getItems().addAll(null, Autobus.Clase.SUPRA, Autobus.Clase.NORMAL);
    }

    @FXML
    public void verCoincidencias(){

            if (textFieldFechaFabricacionConsultarVehiculo.getText().equals("")) {
                localDateFabricacion = null;
            } else {
                localDateFabricacion = LocalDate.parse(textFieldFechaFabricacionConsultarVehiculo.getText(), formatter);
            }
            if (choiceBoxTipoConsultarVehiculo.getValue()==null) {
                tipo = null;
            } else {
                tipo = Vehiculo.Tipo.valueOf(choiceBoxTipoConsultarVehiculo.getValue().toString());
            }
            if (choiceBoxClaseConsultarVehiculo.getValue()==null) {
                clase = null;
            } else {
                clase = Autobus.Clase.valueOf(choiceBoxClaseConsultarVehiculo.getValue().toString());
            }
            ArrayList<String> coincidencias = gestion.buscadorVehiculo(textFieldMatriculaConsultarVehiculo.getText().toString(),
            tipo, clase, localDateFabricacion);

            choiceBoxCoincidenciasConsultarVehiculo.getItems().clear();
            for (String coincidencia: coincidencias){
                choiceBoxCoincidenciasConsultarVehiculo.getItems().add(coincidencia);
            }

            System.out.println(coincidencias);
/*
         catch (Exception e ){
            controlPantallas.mostrarPopup1(textFieldMatriculaConsultarVehiculo, "Errores al introducir los datos");
        }

 */
    }

    @FXML
    public void consultarVehiculo (ActionEvent event) throws IOException {
        ArrayList<Vehiculo> listadoVehiculos = gestion.getListaVehiculos();
        for (Vehiculo vehiculo:listadoVehiculos){
            System.out.println(vehiculo.toString());
        }


        if (gestion.comprobarTipoVehiculo(matricula)== Vehiculo.Tipo.AUTOBUS){
            System.out.println("consultar vehiculo tipo autobus");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("datosAutobus.fxml"));
            Parent root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Datos del autobús");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            DatosAutobusController controller = loader.getController();
            controller.mostrarDatos(choiceBoxCoincidenciasConsultarVehiculo.getValue().toString());
        } else {
            System.out.println("consultarvehiculotipotaxi");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("datosTaxi.fxml"));
            Parent root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Gestión pantallas - Datos del taxi");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            DatosTaxiController controller = loader.getController();
            controller.mostrarDatos(choiceBoxCoincidenciasConsultarVehiculo.getValue().toString());
        }
    }


    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("vehiculos");
    }
    
}
