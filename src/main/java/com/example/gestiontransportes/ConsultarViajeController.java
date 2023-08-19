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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConsultarViajeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField textFieldIdViajeConsultarViaje;
    @FXML
    public TextField textFieldMatriculaConsultarViaje;
    @FXML
    public TextField textFieldTipoConsultarViaje;
    @FXML
    public TextField textFieldFechaConsultarViaje;
    @FXML
    public TextField textFieldOrigenConsultarViaje;
    @FXML
    public TextField textFieldDestinoConsultarViaje;
    @FXML
    public TextField textFieldKilometrosConsultarViaje;
    @FXML
    public TextField textFieldDniConductorConsultarViaje;
    @FXML
    public ChoiceBox choiceBoxEstadoConsultarViaje;
    @FXML
    public ChoiceBox choiceBoxCoincidenciasConsultarViaje;



    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    int idViaje;
    String dni;
    Vehiculo.Tipo tipo;
    LocalDate localDateFecha;
    Viaje.Estado estado;
    int kilometros;

    public void initialize() {
        choiceBoxEstadoConsultarViaje.getItems().addAll(null, Viaje.Estado.ABIERTO, Viaje.Estado.FINALIZADO,
                Viaje.Estado.CANCELADO);
    }

    @FXML
    public void verCoincidencias(){

        try{
            if (textFieldIdViajeConsultarViaje.getText().equals("")) {
                idViaje=0;
            } else {
                idViaje = Integer.valueOf(textFieldIdViajeConsultarViaje.getText());
            }
            if (textFieldKilometrosConsultarViaje.getText().equals("")) {
                kilometros=0;
            } else {
                kilometros = Integer.valueOf(textFieldKilometrosConsultarViaje.getText());
            }
            if (textFieldTipoConsultarViaje.getText().equals("")) {
                tipo = null;
            } else {
            tipo = Vehiculo.Tipo.valueOf(textFieldTipoConsultarViaje.getText().toString());
            }
            if (textFieldFechaConsultarViaje.getText().equals("")) {
                localDateFecha = null;
            } else {
                localDateFecha = LocalDate.parse(textFieldFechaConsultarViaje.getText(), formatter);
            }
            if (choiceBoxEstadoConsultarViaje.getValue()==null) {
                estado = null;
            } else {
                estado = Viaje.Estado.valueOf(choiceBoxEstadoConsultarViaje.getValue().toString());
            }
            ArrayList<Integer> coincidencias = gestion.buscadorViaje(idViaje, textFieldMatriculaConsultarViaje.getText().toString(),
                    tipo, localDateFecha,
                    textFieldOrigenConsultarViaje.getText().toString(), textFieldDestinoConsultarViaje.getText().toString(),
                    kilometros, textFieldDniConductorConsultarViaje.getText().toString(),
                    estado);

            choiceBoxCoincidenciasConsultarViaje.getItems().clear();
            for (Integer coincidencia: coincidencias){
                choiceBoxCoincidenciasConsultarViaje.getItems().add(coincidencia);
            }

            System.out.println(coincidencias);

        } catch (Exception e ){
            controlPantallas.mostrarPopup1(textFieldDestinoConsultarViaje, "Error al introducir los datos");
        }
    }

    @FXML
    public void consultarViaje (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("datosViaje.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Datos del Viaje");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        DatosViajeController controller = loader.getController();
        controller.mostrarDatosViaje(Integer.parseInt(choiceBoxCoincidenciasConsultarViaje.getValue().toString()));

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
