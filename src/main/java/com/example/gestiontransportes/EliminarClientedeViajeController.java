package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class EliminarClientedeViajeController {
    @FXML
    public TextFlow textFlowClientesEliminarClientedeViaje;
    @FXML
    public Label labelIdViajeEliminarClientedeViaje;
    @FXML
    public Label labelAntiguedadEliminarClientedeViaje;
    @FXML
    public Label labelDestinoEliminarClientedeViaje;
    @FXML
    public Label labelFechaEliminarClientedeViaje;
    @FXML
    public Label labelMatriculaEliminarClientedeViaje;
    @FXML
    public Label labelKilometrosEliminarClientedeViaje;
    @FXML
    public Label labelDniViajeEliminarClientedeViaje;
    @FXML
    public Label labelEstadoEliminarClientedeViaje;
    @FXML
    public Label labelTarifaEliminarClientedeViaje;
    @FXML
    public Label labelDniConductorEliminarClientedeViaje;
    @FXML
    public Label labelTipoEliminarClientedeViaje;
    @FXML
    public Label labelOrigenEliminarClientedeViaje;
    @FXML
    public Label labelNumeroPasajerosEliminarClientedeViaje;
    @FXML
    public TextField textFieldDniClienteEliminarClientedeViaje;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    int idViaje;
    String dniCliente;


    public void mostrarDatosViaje (int idViaje){

        String[] datos = new String[12];
        datos = gestion.consultarDatosViaje(idViaje);

        this.idViaje = Integer.valueOf(datos[0].toString());

        labelIdViajeEliminarClientedeViaje.setText(datos[0]);
        labelMatriculaEliminarClientedeViaje.setText(datos[1]);
        labelTipoEliminarClientedeViaje.setText(datos[2]);
        labelFechaEliminarClientedeViaje.setText(datos[3]);
        labelOrigenEliminarClientedeViaje.setText(datos[4]);
        labelDestinoEliminarClientedeViaje.setText(datos[5]);
        labelKilometrosEliminarClientedeViaje.setText(datos[6]);
        labelDniConductorEliminarClientedeViaje.setText(datos[7]);
        Text textoClientes = new Text(datos[8].replace(",","\n"));
        textFlowClientesEliminarClientedeViaje.getChildren().add(textoClientes);
        labelTarifaEliminarClientedeViaje.setText(datos[9]);
        labelEstadoEliminarClientedeViaje.setText(datos[10]);
        labelNumeroPasajerosEliminarClientedeViaje.setText(datos[11]);
    }

    public void eliminarClientedeViaje(){
        int bandera = gestion.eliminarClientedeViaje(idViaje, textFieldDniClienteEliminarClientedeViaje.getText());

        if (bandera==1){
            controlPantallas.mostrarPopup1(labelIdViajeEliminarClientedeViaje, "No hay cliente con ese dni");
        }
        else if (bandera==4){
            controlPantallas.mostrarPopup1(labelIdViajeEliminarClientedeViaje, "El viaje está vacío");
        }
        else if (bandera==3){
            controlPantallas.mostrarPopup1(labelIdViajeEliminarClientedeViaje, "El cliente no está en el viaje");
        }
        else if (bandera==0){
            controlPantallas.mostrarPopup1(labelIdViajeEliminarClientedeViaje, "Cliente eliminado viaje");
            textFlowClientesEliminarClientedeViaje.getChildren().clear();
            this.mostrarDatosViaje(this.idViaje);
        } else {
            controlPantallas.mostrarPopup1(labelIdViajeEliminarClientedeViaje, "Error al eliminar cliente");

        }
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarViaje");
    }
}
