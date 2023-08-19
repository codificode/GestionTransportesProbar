package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class BorrarVehiculoController {
    @FXML
    public Button buttonVolverBorrarVehiculo;
    @FXML
    public Button buttonBorrarBorrarVehiculo;
    @FXML
    public Label labelMatriculaBorrarVehiculo;
    @FXML
    public Label labelTipoBorrarVehiculo;
    @FXML
    public Label labelFechaBorrarVehiculo;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void rellenarCampos(String Matricula) {
        String[] datos = new String[9];
        datos = gestion.consultarDatosVehiculo(Matricula);
        labelMatriculaBorrarVehiculo.setText(datos[0]);
        labelFechaBorrarVehiculo.setText(datos[1]);
        labelTipoBorrarVehiculo.setText(datos[2]);
    }

    public void borrarVehiculo(){
        int bandera = gestion.borrarVehiculo(labelMatriculaBorrarVehiculo.getText());
        if (bandera==0){
            controlPantallas.mostrarPopup1(labelMatriculaBorrarVehiculo, "Vehiculo borrado");
        } else {
            controlPantallas.mostrarPopup1(labelMatriculaBorrarVehiculo, "Error en los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        System.out.println("Volver de borrar Vehiculo");
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("vehiculos");
    }
}
