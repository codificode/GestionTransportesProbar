
package com.example.gestiontransportes;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;


public class Viaje implements Serializable, Comparable<Viaje> {

    private int idViaje;
    LocalDate fecha;
    String origen;
    String destino;
    String matriculaVehiculo;
    String dniConductor;
    ArrayList<String> listaClientesViaje;
    int kilometros;
    double tarifa;
    enum Estado{ABIERTO, FINALIZADO, CANCELADO}
    Estado estado;

    public Viaje(int idViaje, LocalDate fecha, String origen, String destino, String matriculaVehiculo, String dniConductor, int kilometros) {
        //El idViaje me viene dado desde la clase gestión donde es calculado por ser autoincremental.
        this.idViaje = idViaje;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.matriculaVehiculo = matriculaVehiculo;
        this.dniConductor = dniConductor;
        this.listaClientesViaje = new ArrayList<>();
        this.kilometros = kilometros;
        this.calcularySetTarifa();
        this.tarifa = this.getTarifa();
        this.estado = Estado.ABIERTO;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getDniConductor() {
        return dniConductor;
    }

    public void setDniConductor(String dniConductor) {
        this.dniConductor = dniConductor;
    }

    public ArrayList<String> getListaClientesViaje() {
        return listaClientesViaje;
    }

    public void setListaClientesViaje(ArrayList<String> listaClientesViaje) {
        this.listaClientesViaje = listaClientesViaje;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void calcularySetTarifa(){
        Vehiculo.Tipo tipo = Gestion.getInstance().comprobarTipoVehiculo(matriculaVehiculo);

        if (tipo.equals(Vehiculo.Tipo.TAXI)){
            int clientesAnteriores=0;
            for (String dniCliente: listaClientesViaje){
                if(Gestion.getInstance().existeCliente(dniCliente)){
                    clientesAnteriores+=1;
                }
            }
            double tarifa = ((5 + this.kilometros) * (1 - 0.01*clientesAnteriores));
            double tarifaFormateada = Math.round(tarifa * 100.00) / 100.00;
            this.tarifa = tarifaFormateada;
        } else {
            double tarifaSupra = (1 + 0.1 * this.kilometros);
            Autobus autobus = (Autobus) Gestion.getInstance().obtenerVehiculo(matriculaVehiculo);

            if (autobus.getClase().equals("NORMAL")){

                Double tarifaNormal = tarifaSupra/2;
                double tarifaFormateada = Math.round(tarifaNormal * 100.00) / 100.00;
                this.tarifa = tarifaFormateada;
            }  else {
                double tarifaFormateada = Math.round(tarifaSupra * 100.00) / 100.00;
                this.tarifa = tarifaFormateada;
            }
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int agregarClienteaViaje (String dni){
        if (Gestion.getInstance().comprobarTipoVehiculo(matriculaVehiculo)== Vehiculo.Tipo.TAXI) {
            if (listaClientesViaje.size() == 4) {
                return 1;
            }
        } else {
            if (listaClientesViaje.size() == 40) {
                return 1;
            }
        }
        if (listaClientesViaje.contains(dni)){
            return 2;
        }
        listaClientesViaje.add(dni);
        if (Gestion.getInstance().comprobarTipoVehiculo(matriculaVehiculo)== Vehiculo.Tipo.TAXI) {
            this.calcularySetTarifa();
            }
        return 0;
    }

    public int eliminarClientedeViaje(String dniCliente){

            listaClientesViaje.remove(dniCliente);
            Vehiculo vehiculo = Gestion.getInstance().obtenerVehiculo(matriculaVehiculo);
            if (vehiculo.getTipo().equals("TAXI")){
                this.calcularySetTarifa();
            }
            return 0;
    }
    @Override
    public int compareTo(Viaje o) {
        return this.idViaje - o.idViaje;
    }
}
