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

public class ConsultarViajesParaAgregarClienteController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField textFieldIdViajeConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldMatriculaConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldTipoConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldFechaConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldOrigenConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldDestinoConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldKilometrosConsultarViajesParaAgregarCliente;
    @FXML
    public TextField textFieldDniConductorConsultarViajesParaAgregarCliente;
    @FXML
    public ChoiceBox choiceBoxEstadoConsultarViajesParaAgregarCliente;
    @FXML
    public ChoiceBox choiceBoxCoincidenciasConsultarViajesParaAgregarCliente;



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
        choiceBoxEstadoConsultarViajesParaAgregarCliente.getItems().addAll(null, Viaje.Estado.ABIERTO, Viaje.Estado.FINALIZADO,
                Viaje.Estado.CANCELADO);
    }

    @FXML
    public void verCoincidencias(){

        try{
            if (textFieldIdViajeConsultarViajesParaAgregarCliente.getText().equals("")) {
                idViaje=0;
            } else {
                idViaje = Integer.valueOf(textFieldIdViajeConsultarViajesParaAgregarCliente.getText());
            }
            if (textFieldKilometrosConsultarViajesParaAgregarCliente.getText().equals("")) {
                kilometros=0;
            } else {
                kilometros = Integer.valueOf(textFieldKilometrosConsultarViajesParaAgregarCliente.getText());
            }
            if (textFieldTipoConsultarViajesParaAgregarCliente.getText().equals("")) {
                tipo = null;
            } else {
                tipo = Vehiculo.Tipo.valueOf(textFieldTipoConsultarViajesParaAgregarCliente.getText().toString());
            }
            if (textFieldFechaConsultarViajesParaAgregarCliente.getText().equals("")) {
                localDateFecha = null;
            } else {
                localDateFecha = LocalDate.parse(textFieldFechaConsultarViajesParaAgregarCliente.getText(), formatter);
            }
            if (choiceBoxEstadoConsultarViajesParaAgregarCliente.getValue()==null) {
                estado = null;
            } else {
                estado = Viaje.Estado.valueOf(choiceBoxEstadoConsultarViajesParaAgregarCliente.getValue().toString());
            }
            ArrayList<Integer> coincidencias = gestion.buscadorViaje(idViaje, textFieldMatriculaConsultarViajesParaAgregarCliente.getText().toString(),
                    tipo, localDateFecha,
                    textFieldOrigenConsultarViajesParaAgregarCliente.getText().toString(), textFieldDestinoConsultarViajesParaAgregarCliente.getText().toString(),
                    kilometros, textFieldDniConductorConsultarViajesParaAgregarCliente.getText().toString(),
                    estado);

            choiceBoxCoincidenciasConsultarViajesParaAgregarCliente.getItems().clear();
            for (Integer coincidencia: coincidencias){
                choiceBoxCoincidenciasConsultarViajesParaAgregarCliente.getItems().add(coincidencia);
            }

            System.out.println(coincidencias);

        } catch (Exception e ){
            controlPantallas.mostrarPopup1(textFieldDestinoConsultarViajesParaAgregarCliente, "Error al introducir los datos");
        }
    }

    @FXML
    public void ConsultarViajesParaAgregarCliente (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarClienteaViaje.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Agregar cliente a viaje");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        AgregarClienteaViajeController controller = loader.getController();
        controller.mostrarAgregarClienteaViaje(Integer.parseInt(choiceBoxCoincidenciasConsultarViajesParaAgregarCliente.getValue().toString()));

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
