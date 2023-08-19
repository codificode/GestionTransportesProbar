package com.example.gestiontransportes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConsultarConductorController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField textFieldDniConsultarConductor;
    @FXML
    public TextField textFieldNombreConsultarConductor;
    @FXML
    public TextField textFieldNacimientoConsultarConductor;
    @FXML
    public TextField textFieldFechaCarnetConsultarConductor;
    @FXML
    public ChoiceBox choiceBoxTipoCarnetConsultarConductor;
    @FXML
    public ChoiceBox choiceBoxCoincidenciasConsultarConductor;



    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    String dni;
    String nombre;
    LocalDate localDateNacimiento;
    LocalDate localDateCarnet;
    Conductor.TipoCarnet tipoCarnet;

    public void initialize() {
        choiceBoxTipoCarnetConsultarConductor.getItems().addAll(null, Conductor.TipoCarnet.AB, Conductor.TipoCarnet.B);
    }

    @FXML
    public void verCoincidencias(){
        try{;
            System.out.println(choiceBoxTipoCarnetConsultarConductor.getValue());

            if (textFieldNacimientoConsultarConductor.getText().equals("")) {
                localDateNacimiento = null;
            } else {
                localDateNacimiento = LocalDate.parse(textFieldNacimientoConsultarConductor.getText(), formatter);
            }
            if (textFieldFechaCarnetConsultarConductor.getText().equals("")) {
                localDateCarnet = null;
            } else {
                localDateCarnet = LocalDate.parse(textFieldFechaCarnetConsultarConductor.getText(), formatter);
            }
            if (choiceBoxTipoCarnetConsultarConductor.getValue()==null) {
                tipoCarnet = null;
            } else {
                tipoCarnet = Conductor.TipoCarnet.valueOf(choiceBoxTipoCarnetConsultarConductor.getValue().toString());
            }
            ArrayList<String> coincidencias = gestion.buscadorConductor(textFieldDniConsultarConductor.getText().toString(), textFieldNombreConsultarConductor.getText().toString(),
                    localDateNacimiento, localDateCarnet, tipoCarnet);

            choiceBoxCoincidenciasConsultarConductor.getItems().clear();
            for (String coincidencia: coincidencias){
                choiceBoxCoincidenciasConsultarConductor.getItems().add(coincidencia);
            }

            System.out.println(coincidencias);

                    } catch (Exception e ){
            controlPantallas.mostrarPopup1(textFieldDniConsultarConductor, "Error al introducir los datos");
        }
    }

    @FXML
    public void consultarConductor (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("datosConductor.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Consultar conductor");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        DatosConductorController controller = loader.getController();
        controller.mostrarDatosConductor(choiceBoxCoincidenciasConsultarConductor.getValue().toString());

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("conductores");
    }

}
