package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarViajeController {

    @FXML
    public TextField textFieldOrigenEditarViaje;
    @FXML
    public TextField textFieldFechaEditarViaje;
    @FXML
    public TextField textFieldKilometrosEditarViaje;
    @FXML
    public ChoiceBox choiceBoxEstadoEditarViaje;
    @FXML
    public Label labelIdViajeEditarViaje;
    @FXML
    public TextField textFieldDestinoEditarViaje;
    @FXML
    public TextFlow textFlowListadoPasarejosEditarViaje;
    @FXML
    public ChoiceBox choiceBoxMatriculaEditarViaje;
    @FXML
    public ChoiceBox choiceBoxDniConductorEditarViaje;
    @FXML
    public Label labelTarifaEditarViaje;
    @FXML
    public Label labelTipoEditarViaje;
    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void rellenarCampos(int idViaje){
        String[] datos = new String[11];
        datos = gestion.consultarDatosViaje(idViaje);
        labelIdViajeEditarViaje.setText(datos[0]);
        choiceBoxMatriculaEditarViaje.getItems().clear();
        for (Vehiculo vehiculo: gestion.getListaVehiculos()){
            if (String.valueOf(vehiculo.getTipo()).equals(datos[2])){
                choiceBoxMatriculaEditarViaje.getItems().add(vehiculo.getMatricula());
            }
        }
        choiceBoxMatriculaEditarViaje.setValue(datos[1]);
        labelTipoEditarViaje.setText(datos[2]);
        textFieldFechaEditarViaje.setText(datos[3]);
        textFieldOrigenEditarViaje.setText(datos[4]);
        textFieldDestinoEditarViaje.setText(datos[5]);
        textFieldKilometrosEditarViaje.setText(datos[6]);
        choiceBoxDniConductorEditarViaje.getItems().clear();
        if (datos[2].equals("TAXI")){
            for (Conductor conductor: gestion.getListaConductores()){
                choiceBoxDniConductorEditarViaje.getItems().add(conductor.getDni());
            }
        }
        if (datos[2].equals("AUTOBUS")){
            for (Conductor conductor: gestion.getListaConductores()){
                if (conductor.getTipoCarnet()== Conductor.TipoCarnet.AB){
                    choiceBoxDniConductorEditarViaje.getItems().add(conductor.getDni());
                }
            }
        }
        choiceBoxDniConductorEditarViaje.setValue(datos[7]);
        Text texto = new Text(datos[8].replace(",", "\n"));
        textFlowListadoPasarejosEditarViaje.getChildren().add(texto);
        labelTarifaEditarViaje.setText(datos[9]);
        choiceBoxEstadoEditarViaje.getItems().clear();
        choiceBoxEstadoEditarViaje.getItems().addAll(Viaje.Estado.ABIERTO, Viaje.Estado.FINALIZADO);
        choiceBoxEstadoEditarViaje.setValue(datos[10]);
    }

    public void actualizarCampos (){

        int idViaje = Integer.parseInt(labelIdViajeEditarViaje.getText());
        LocalDate fecha = LocalDate.parse(textFieldFechaEditarViaje.getText(), formatter);
        Viaje.Estado estado = Viaje.Estado.valueOf(choiceBoxEstadoEditarViaje.getValue().toString());
        int kilometros = Integer.parseInt(textFieldKilometrosEditarViaje.getText());

        int bandera = gestion.editarViaje(idViaje, choiceBoxMatriculaEditarViaje.getValue().toString(),
                fecha, textFieldOrigenEditarViaje.getText(), textFieldDestinoEditarViaje.getText(), kilometros,
                choiceBoxDniConductorEditarViaje.getValue().toString(), estado);
        if (bandera==0){
            controlPantallas.mostrarPopup1(textFieldDestinoEditarViaje, "Viaje editado");
        } else {
            controlPantallas.mostrarPopup1(textFieldDestinoEditarViaje, "Error en los datos");
        }
        this.rellenarCampos(idViaje);
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("viajes");
    }
}
