package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class AgregarClienteaViajeController {
    @FXML
    public TextFlow textFlowClientesAgregarClienteaViaje;
    @FXML
    public Label labelIdViajeAgregarClienteaViaje;
    @FXML
    public Label labelAntiguedadAgregarClienteaViaje;
    @FXML
    public Label labelDestinoAgregarClienteaViaje;
    @FXML
    public Label labelFechaAgregarClienteaViaje;
    @FXML
    public Label labelMatriculaAgregarClienteaViaje;
    @FXML
    public Label labelKilometrosAgregarClienteaViaje;
    @FXML
    public Label labelDniViajeAgregarClienteaViaje;
    @FXML
    public Label labelEstadoAgregarClienteaViaje;
    @FXML
    public Label labelTarifaAgregarClienteaViaje;
    @FXML
    public Label labelDniConductorAgregarClienteaViaje;
    @FXML
    public Label labelTipoAgregarClienteaViaje;
    @FXML
    public Label labelOrigenAgregarClienteaViaje;
    @FXML
    public Label labelNumeroPasajerosAgregarClienteaViaje;
    @FXML
    public TextField textFieldDniClienteAgregarClienteaViaje;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    int idViaje;
    String dniCliente;


    public void mostrarAgregarClienteaViaje (int idViaje){

        String[] datos = new String[12];
        datos = gestion.consultarDatosViaje(idViaje);

        this.idViaje = Integer.valueOf(datos[0].toString());

        labelIdViajeAgregarClienteaViaje.setText(datos[0]);
        labelMatriculaAgregarClienteaViaje.setText(datos[1]);
        labelTipoAgregarClienteaViaje.setText(datos[2]);
        labelFechaAgregarClienteaViaje.setText(datos[3]);
        labelOrigenAgregarClienteaViaje.setText(datos[4]);
        labelDestinoAgregarClienteaViaje.setText(datos[5]);
        labelKilometrosAgregarClienteaViaje.setText(datos[6]);
        labelDniConductorAgregarClienteaViaje.setText(datos[7]);
        Text textoClientes = new Text(datos[8].replace(",","\n"));
        textFlowClientesAgregarClienteaViaje.getChildren().add(textoClientes);
        labelTarifaAgregarClienteaViaje.setText(datos[9]);
        labelEstadoAgregarClienteaViaje.setText(datos[10]);
        labelNumeroPasajerosAgregarClienteaViaje.setText(datos[11]);
    }

    public void agregarClienteaViaje(){
        int bandera = gestion.agregarClienteaViaje(idViaje, textFieldDniClienteAgregarClienteaViaje.getText());

        if (bandera==3){
            controlPantallas.mostrarPopup1(labelIdViajeAgregarClienteaViaje, "No hay cliente con ese dni");
        }
        else if (bandera==1){
            controlPantallas.mostrarPopup1(labelIdViajeAgregarClienteaViaje, "No quedan plazas");
        }
        else if (bandera==2){
            controlPantallas.mostrarPopup1(labelIdViajeAgregarClienteaViaje, "El cliente ya está en el viaje");
        }
        else if (bandera==0){
            controlPantallas.mostrarPopup1(labelIdViajeAgregarClienteaViaje, "Cliente agregado a viaje");
            textFlowClientesAgregarClienteaViaje.getChildren().clear();
            this.mostrarAgregarClienteaViaje(this.idViaje);
        } else {
            controlPantallas.mostrarPopup1(labelIdViajeAgregarClienteaViaje, "Error al agregar cliente");
        }
    }

    @FXML
    public void volver() throws IOException {
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("consultarViaje");
    }
}
