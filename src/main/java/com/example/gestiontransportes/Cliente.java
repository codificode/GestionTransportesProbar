package com.example.gestiontransportes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Cliente implements Serializable, Comparable<Cliente> {

    String dni;
    String nombre;
    LocalDate fechaNacimiento;

    public Cliente (String dni, String nombre, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
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

    public int getEdad (){
        Period diferencia = Period.between(fechaNacimiento, LocalDate.now());
        return diferencia.getYears();
    }

    @Override
    public String toString(){
        return dni + ", " + nombre + ", " + fechaNacimiento.toString();
    }


    @Override
    public int compareTo(Cliente obj){
        return dni.compareTo(obj.dni);
    }

}
