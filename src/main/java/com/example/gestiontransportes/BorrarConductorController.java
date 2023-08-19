package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class BorrarConductorController {
    @FXML
    public Button buttonVolverBorrarConductor;
    @FXML
    public Button buttonBorrarBorrarConductor;
    @FXML
    public Label labelDniBorrarConductor;
    @FXML
    public Label labelNombreBorrarConductor;
    @FXML
    public Label labelFechaNacimientoBorrarConductor;
    @FXML
    public Label labelFechaCarnetBorrarConductor;
    @FXML
    public Label labelTipoCarnetBorrarConductor;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void rellenarCampos(String dni) {
        String[] datos = new String[9];
        datos = gestion.consultarDatosConductor(dni);
        labelDniBorrarConductor.setText(datos[0]);
        labelNombreBorrarConductor.setText(datos[1]);
        labelFechaNacimientoBorrarConductor.setText(datos[2]);
        labelFechaCarnetBorrarConductor.setText(datos[3]);
        labelTipoCarnetBorrarConductor.setText(datos[6]);
    }

    public void borrarConductor(){
        int bandera = gestion.borrarConductor(labelDniBorrarConductor.getText());
        if (bandera==0){
            controlPantallas.mostrarPopup1(labelDniBorrarConductor, "Conductor borrado");
        } else {
            controlPantallas.mostrarPopup1(labelDniBorrarConductor, "Error en los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        System.out.println("Volver de borrar conductor");
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("conductores");
    }
}
