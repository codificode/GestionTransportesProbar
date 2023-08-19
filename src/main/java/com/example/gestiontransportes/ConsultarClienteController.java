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

public class ConsultarClienteController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField textFieldNombreConsultarCliente;
    @FXML
    public TextField textFieldFechaNacimientoConsultarCliente;
    @FXML
    public ChoiceBox choiceBoxCoincidenciasConsultarCliente;

    @FXML
    TextField textFieldDniConsultarCliente;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    String dni;
    String nombre;
    LocalDate localDateNacimiento;

    @FXML
    public void verCoincidencias(){
        try{

            if (textFieldFechaNacimientoConsultarCliente.getText().equals("")) {
                localDateNacimiento = null;
            } else {
                localDateNacimiento = LocalDate.parse(textFieldFechaNacimientoConsultarCliente.getText(), formatter);
            }
            ArrayList<String> coincidencias = gestion.buscadorCliente(textFieldDniConsultarCliente.getText().toString(), textFieldNombreConsultarCliente.getText().toString(),
                    localDateNacimiento);

            choiceBoxCoincidenciasConsultarCliente.getItems().clear();
            for (String coincidencia: coincidencias){
                choiceBoxCoincidenciasConsultarCliente.getItems().add(coincidencia);
            }

            System.out.println(coincidencias);

        } catch (Exception e ){
            controlPantallas.mostrarPopup1(textFieldDniConsultarCliente, "Error al introducir los datos");
        }
    }

    @FXML
    public void consultarCliente (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("datosCliente.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Gestión pantallas - Datos del cliente");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        DatosClienteController controller = loader.getController();
        controller.mostrarDatosCliente(choiceBoxCoincidenciasConsultarCliente.getValue().toString());

    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("clientes");
    }
}
