package com.example.gestiontransportes;

import java.time.LocalDate;

public class Autobus extends Vehiculo{
    public enum Clase {SUPRA, NORMAL};
    private Clase clase;

    public Autobus (String matricula, LocalDate fechaFabricacion, Clase clase){
        super (matricula, fechaFabricacion);
        this.setNumPlazas(40);
        this.tipo = Tipo.AUTOBUS;
        this.clase = clase;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
