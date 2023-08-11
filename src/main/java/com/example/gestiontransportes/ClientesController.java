package com.example.gestiontransportes;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientesController {

    @FXML
    protected void agregarClienter(){
    }

    ControlPantallas control = new ControlPantallas();

    @FXML
    protected void volver() throws IOException {
        control.cambioRootyTitulo("principal");
    }

}