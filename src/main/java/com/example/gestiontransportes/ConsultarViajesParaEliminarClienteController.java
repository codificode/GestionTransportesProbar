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

public class ConsultarViajesParaEliminarClienteController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField textFieldIdViajeConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldMatriculaConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldTipoConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldFechaConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldOrigenConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldDestinoConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldKilometrosConsultarViajesParaEliminarCliente;
    @FXML
    public TextField textFieldDniConductorConsultarViajesParaEliminarCliente;
    @FXML
    public ChoiceBox choiceBoxEstadoConsultarViajesParaEliminarCliente;
    @FXML
    public ChoiceBox choiceBoxCoincidenciasConsultarViajesParaEliminarCliente;



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
        choiceBoxEstadoConsultarViajesParaEliminarCliente.getItems().addAll(null, Viaje.Estado.ABIERTO, Viaje.Estado.FINALIZADO,
                Viaje.Estado.CANCELADO);
    }

    @FXML
    public void verCoincidencias(){

        try{
            if (textFieldIdViajeConsultarViajesParaEliminarCliente.getText().equals("")) {
                idViaje=0;
            } else {
                idViaje = Integer.valueOf(textFieldIdViajeConsultarViajesParaEliminarCliente.getText());
            }
            if (textFieldKilometrosConsultarViajesParaEliminarCliente.getText().equals("")) {
                kilometros=0;
            } else {
                kilometros = Integer.valueOf(textFieldKilometrosConsultarViajesParaEliminarCliente.getText());
            }
            if (textFieldTipoConsultarViajesParaEliminarCliente.getText().equals("")) {
                tipo = null;
            } else {
                tipo = Vehiculo.Tipo.valueOf(textFieldTipoConsultarViajesParaEliminarCliente.getText().toString());
            }
            if (textFieldFechaConsultarViajesParaEliminarCliente.getText().equals("")) {
                localDateFecha = null;
            } else {
                localDateFecha = LocalDate.parse(textFieldFechaConsultarViajesParaEliminarCliente.getText(), formatter);
            }
            if (choiceBoxEstadoConsultarViajesParaEliminarCliente.getValue()==null) {
                estado = null;
            } else {
                estado = Viaje.Estado.valueOf(choiceBoxEstadoConsultarViajesParaEliminarCliente.getValue().toString());
            }
            ArrayList<Integer> coincidencias = gestion.buscadorViaje(idViaje, textFieldMatriculaConsultarViajesParaEliminarCliente.getText().toString(),
                    tipo, localDateFecha,
                    textFieldOrigenConsultarViajesParaEliminarCliente.getText().toString(), textFieldDestinoConsultarViajesParaEliminarCliente.getText().toString(),
                    kilometros, textFieldDniConductorConsultarViajesParaEliminarCliente.getText().toString(),
                    estado);

            choiceBoxCoincidenciasConsultarViajesParaEliminarCliente.getItems().clear();
            for (Integer coincidencia: coincidencias){
                choiceBoxCoincidenciasConsultarViajesParaEliminarCliente.getItems().add(coincidencia);
            }

            System.out.println(coincidencias);

        } catch (Exception e ){
            controlPantallas.mostrarPopup1(textFieldDestinoConsultarViajesParaEliminarCliente, "Error al introducir los datos");
        }
    }

    @FXML
    public void consultarViajesParaEliminarCliente (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("eliminarClientedeViaje.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Eliminar cliente de viaje");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        EliminarClientedeViajeController controller = loader.getController();
        controller.mostrarDatosViaje(Integer.parseInt(choiceBoxCoincidenciasConsultarViajesParaEliminarCliente.getValue().toString()));

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
