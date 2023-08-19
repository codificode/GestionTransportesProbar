package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class BorrarClienteController {

    @FXML
    public Button buttonVolverBorrarCliente;
    @FXML
    public Button buttonBorrarBorrarCliente;
    @FXML
    public Label labelDniBorrarCliente;
    @FXML
    public Label labelNombreBorrarCliente;
    @FXML
    public Label labelFechaNacimientoBorrarCliente;

    Gestion gestion = Gestion.getInstance();
    ControlPantallas controlPantallas = ControlPantallas.getInstance();

    public void rellenarCampos(String dni) {
        String[] datos = new String[6];
        datos = gestion.consultarDatosCliente(dni);
        labelDniBorrarCliente.setText(datos[0]);
        labelNombreBorrarCliente.setText(datos[1]);
        labelFechaNacimientoBorrarCliente.setText(datos[2]);
    }

    public void borrarCliente(){
        System.out.println("Estoy en borrarCliente()");
        int bandera = gestion.borrarCliente(labelDniBorrarCliente.getText());
        System.out.println(bandera);
        if (bandera==0){
            controlPantallas.mostrarPopup1(labelDniBorrarCliente, "Cliente borrado");
        } else {
            controlPantallas.mostrarPopup1(labelDniBorrarCliente, "Error en los datos");
        }
    }

    @FXML
    public void volver() throws IOException {
        System.out.println("Volver de borrar Cliente");
        ControlPantallas control = new ControlPantallas();
        control.cambioRootyTitulo("clientes");
    }
    
}
