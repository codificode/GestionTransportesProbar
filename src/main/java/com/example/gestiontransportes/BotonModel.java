package com.example.gestiontransportes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BotonModel {
    private StringProperty textoBoton = new SimpleStringProperty();

    public StringProperty textoBotonProperty() {
        return textoBoton;
    }

    public String getTextoBoton() {
        return textoBoton.get();
    }

    public void setTextoBoton(String texto) {
        textoBoton.set(texto);
    }
}
