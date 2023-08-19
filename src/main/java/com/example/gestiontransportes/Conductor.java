package com.example.gestiontransportes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Conductor implements Serializable, Comparable<Conductor> {
    String dni;
    String nombre;
    LocalDate fechaNacimiento;
    LocalDate fechaCarnet;
    enum TipoCarnet {B, AB}
    TipoCarnet tipoCarnet;


    public Conductor (String dni, String nombre, LocalDate fechaNacimiento, LocalDate fechaCarnet, TipoCarnet tipoCarnet) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCarnet = fechaCarnet;
        this.tipoCarnet = tipoCarnet;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaCarnet() {
        return fechaCarnet;
    }

    public void setFechaCarnet(LocalDate fechaCarnet) {
        this.fechaCarnet = fechaCarnet;
    }

    public int getEdad (){
        Period diferencia = Period.between(fechaNacimiento, LocalDate.now());
        return diferencia.getYears();
    }

    public int getExperiencia (){
        Period diferencia = Period.between(fechaCarnet, LocalDate.now());
        return diferencia.getYears();
    }

    public TipoCarnet getTipoCarnet() {
        return tipoCarnet;
    }

    public void setTipoCarnet(TipoCarnet tipoCarnet) {
        this.tipoCarnet = tipoCarnet;
    }

    @Override
    public String toString(){
        return dni + ", " + "nombre";
    }


    @Override
    public int compareTo(Conductor obj){
        return dni.compareTo(obj.dni);
    }
}
