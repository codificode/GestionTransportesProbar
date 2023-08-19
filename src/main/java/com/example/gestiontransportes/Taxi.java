package com.example.gestiontransportes;

import java.time.LocalDate;

public class Taxi extends Vehiculo {

    public Taxi(String matricula, LocalDate fechaFabricacion){
        super(matricula, fechaFabricacion);
        this.setNumPlazas(4);
        this.tipo = Tipo.TAXI;
    }
}
