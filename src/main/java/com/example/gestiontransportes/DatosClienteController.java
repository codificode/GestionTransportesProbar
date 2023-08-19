package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class DatosClienteController {
        @FXML
        public TextFlow textFlowViajesRealizadosDatosCliente;
        @FXML
        public TextFlow textFlowVehiculosViajadosDatosCliente;
        @FXML
        public Label labelNombreDatosCliente;
        @FXML
        public Label labelFechaNacimientoDatosCliente;
        @FXML
        public Label labelDniDatosCliente;
        @FXML
        public Label labelEdadDatosCliente;
        @FXML
        public Button buttonVolverDatosCliente;

        Gestion gestion = Gestion.getInstance();
        ControlPantallas controlPantallas = ControlPantallas.getInstance();


        public void mostrarDatosCliente (String dni){

            String[] datos = new String[9];
            datos = gestion.consultarDatosCliente(dni);

            labelDniDatosCliente.setText(datos[0]);
            labelNombreDatosCliente.setText(datos[1]);
            labelFechaNacimientoDatosCliente.setText(datos[2]);
            labelEdadDatosCliente.setText(datos[3]);
            Text textoVehiculos = new Text(datos[4].replace(",","\n"));
            textFlowVehiculosViajadosDatosCliente.getChildren().add(textoVehiculos);
            Text textoViajes = new Text(datos[5].replace(",","\n"));
            textFlowViajesRealizadosDatosCliente.getChildren().add(textoViajes);
        }

        @FXML
        public void volver() throws IOException {
            ControlPantallas control = new ControlPantallas();
            control.cambioRootyTitulo("consultarCliente");
        }

}
