package com.example.gestiontransportes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Vehiculo implements Serializable, Comparable<Vehiculo> {
    private String matricula;
    private int numPlazas;
    private LocalDate fechaFabricacion;
    enum Tipo {AUTOBUS, TAXI}
    protected Tipo tipo;

    public Vehiculo(String matricula, LocalDate fechaFabricacion){
        this.matricula = matricula;
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {

        this.tipo = tipo;
        System.out.println("Tipo cambiado");
    }

    @Override
    public String toString(){
        return matricula + ", " + fechaFabricacion + ", " + tipo;
    }

    @Override
    public int compareTo(Vehiculo obj) {
        return matricula.compareTo(obj.matricula);
    }
}
