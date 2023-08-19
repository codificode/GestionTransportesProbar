package com.example.gestiontransportes;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;


public final class Gestion {

    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private ArrayList<Conductor> listaConductores = new ArrayList<>();
    private ArrayList<Cliente> listaClientes =  new ArrayList<>();
    private ArrayList<Viaje> listaViajes = new ArrayList<>();

    private static final Gestion INSTANCE = new Gestion();

    public static Gestion getInstance() {
        return INSTANCE;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public ArrayList<Conductor> getListaConductores() {
        return listaConductores;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Viaje> getListaViajes() {
        return listaViajes;
    }

    public int crearConductor(String dni, String nombre, LocalDate fechaNacimiento, LocalDate fechaCarnet, Conductor.TipoCarnet tipoCarnet){
        if (this.existeConductor(dni)==true){
            return 1;
        }
        try{
            this.listaConductores.add(new Conductor(dni, nombre, fechaNacimiento, fechaCarnet, tipoCarnet));
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    public ArrayList<String> buscadorConductor(String dni, String nombre, LocalDate fechaNacimiento, LocalDate fechaCarnet, Conductor.TipoCarnet tipoCarnet){

        System.out.println(dni);
        System.out.println(nombre);
        System.out.println(fechaNacimiento);
        System.out.println(fechaCarnet);
        System.out.println(tipoCarnet);


        ArrayList<String> listaCoincidencias = new ArrayList<>();
        boolean coincideDni = false;
        boolean coincideNombre = false;
        boolean coincideFechaNacimiento = false;
        boolean coincideFechaCarnet = false;
        boolean coincideTipoCarnet = false;

        if (dni.equals("")){
            coincideDni=true;
        }
        if (nombre.equals("")){
            coincideNombre=true;
        }
        if (fechaNacimiento==null){
            coincideFechaNacimiento=true;
        }
        if (fechaCarnet==null){
            coincideFechaCarnet=true;
        }
        if (tipoCarnet==null){
            coincideTipoCarnet=true;
        }
        for (Conductor conductor: listaConductores){
            boolean coincideDni1 = false;
            boolean coincideNombre1 = false;
            boolean coincideFechaNacimiento1 = false;
            boolean coincideFechaCarnet1 = false;
            boolean coincideTipoCarnet1 = false;

            if (coincideDni == true){
                coincideDni1 = true;
            }
            if (coincideNombre==true){
                coincideNombre1=true;
            }
            if(coincideFechaNacimiento==true){
                coincideFechaNacimiento1=true;
            }
            if(coincideFechaCarnet==true){
                coincideFechaCarnet1=true;
            }
            if(coincideTipoCarnet==true){
                coincideTipoCarnet1=true;
            }
            if (conductor.getDni().equals(dni)){
                coincideDni1=true;
            }
            if (conductor.getNombre().equals(nombre)){
                coincideNombre1=true;
            }
            if(conductor.getFechaNacimiento()==fechaNacimiento){
                coincideFechaNacimiento1=true;
            }
            if(conductor.getFechaCarnet()==fechaCarnet){
                coincideFechaCarnet1=true;
            }
            if(conductor.getTipoCarnet()==tipoCarnet){
                coincideTipoCarnet1=true;
            }

            if (coincideDni1 && coincideNombre1 && coincideFechaNacimiento1 && coincideFechaCarnet1 && coincideTipoCarnet1){
                listaCoincidencias.add(conductor.getDni());
            }
        }
        return listaCoincidencias;
    }

    public String[] consultarDatosConductor (String dni){
        String[] datosConductor = new String[9];
        ArrayList<String> vehiculosConducidos = new ArrayList<>();
        ArrayList<String> viajesRealizados = new ArrayList<>();
        Conductor conductor = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Conductor conductor1: listaConductores){
            if (conductor1.getDni().equals(dni)){
                conductor=conductor1;
            }
        }
        datosConductor[0]=conductor.getDni();
        datosConductor[1]=conductor.getNombre();
        String fechaN = conductor.getFechaNacimiento().format(formatter);
        datosConductor[2]=fechaN;
        datosConductor[3]=conductor.getFechaCarnet().format(formatter);
        datosConductor[4]= String.valueOf(conductor.getEdad());
        datosConductor[5]=String.valueOf(conductor.getExperiencia());
        datosConductor[6]=conductor.getTipoCarnet().toString();

        for (Viaje viaje: listaViajes){
            if (viaje.getDniConductor()==dni){
                viajesRealizados.add(String.valueOf(viaje.getIdViaje()));
                vehiculosConducidos.add(String.valueOf(viaje.getMatriculaVehiculo()));
            }
        }
        datosConductor[7]=String.join(",", vehiculosConducidos);
        datosConductor[8]=String.join(",",viajesRealizados);

        return datosConductor;
    }

    public int editarConductor(String dni, String nombre, LocalDate fechaNacimiento, LocalDate fechaCarnet, Conductor.TipoCarnet tipoCarnet){
        try{
            for (Conductor conductor: listaConductores){
                if (conductor.getDni().equals(dni)){
                    conductor.setNombre(nombre);
                    conductor.setFechaNacimiento(fechaNacimiento);
                    conductor.setFechaCarnet(fechaCarnet);
                    conductor.setTipoCarnet(tipoCarnet);
                }
            }
            return 0;
        } catch (Exception e) {
            return 100;
        }
    }

    public int borrarConductor(String dni){
        try{
            for (Conductor conductor: listaConductores){
                if (conductor.getDni().equals(dni)){
                    listaConductores.remove(conductor);
                    //Uso el break porque estoy usando un bucle para borrar un elemento
                    break;
                }
            }
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    public ArrayList<String> listarConductores(){
        ArrayList<String> listadoConductores = new ArrayList<>();
        for (Conductor conductor: listaConductores){
            String stringConductor = conductor.getDni() + ", " + conductor.getNombre();
            listadoConductores.add(stringConductor);
        }
        return listadoConductores;
    }

    public int crearCliente(String dni, String nombre, LocalDate fechaNacimiento){
        if (this.existeCliente(dni)==true){
            return 1;
        }
        try{
            this.listaClientes.add(new Cliente(dni, nombre, fechaNacimiento));
            System.out.println("cliente agregado");
            return 0;

        } catch (Exception e){
            return 100;
        }
    }


    public ArrayList<String> buscadorCliente(String dni, String nombre, LocalDate fechaNacimiento){
        System.out.println("Estoy en buscadorcliente");

        ArrayList<String> listaCoincidencias = new ArrayList<>();

        boolean coincideDni = false;
        boolean coincideNombre = false;
        boolean coincideFechaNacimiento = false;

        if (dni.equals("")){
            coincideDni=true;
        }
        if (nombre.equals("")){
            coincideNombre=true;
        }
        if (fechaNacimiento==null){
            coincideFechaNacimiento=true;
        }

        for (Cliente cliente: listaClientes){
            boolean coincideDni1 = false;
            boolean coincideNombre1 = false;
            boolean coincideFechaNacimiento1 = false;


            if (coincideDni ==true){
                coincideDni1=true;
            }
            if (coincideNombre==true){
                coincideNombre1=true;
            }
            if(coincideFechaNacimiento==true){
                coincideFechaNacimiento1=true;
            }
            if (cliente.getDni().equals(dni)){
                coincideDni1=true;
            }
            if (cliente.getNombre().equals(nombre)){
                coincideNombre1=true;
            }
            if(cliente.getFechaNacimiento()==fechaNacimiento){
                coincideFechaNacimiento1=true;
            }
            if (coincideDni1 && coincideNombre1 && coincideFechaNacimiento1){
                listaCoincidencias.add(cliente.getDni());
            }
        }
        return listaCoincidencias;
    }

    public String[] consultarDatosCliente (String dni){
        String[] datosCliente = new String[6];
        ArrayList<String> vehiculosUsados= new ArrayList<>();
        ArrayList<String> viajesRealizados = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Cliente cliente = null;
        for (Cliente cliente1: listaClientes){
            if (cliente1.getDni().equals(dni)){
                cliente=cliente1;
            }
        }
        datosCliente[0]=cliente.getDni();
        datosCliente[1]=cliente.getNombre();
        datosCliente[2]= cliente.getFechaNacimiento().format(formatter);
        datosCliente[3]= String.valueOf(cliente.getEdad());

        for (Viaje viaje: listaViajes){
            for (String dniViajero: viaje.getListaClientesViaje())
                if (dniViajero==dni){
                    viajesRealizados.add(String.valueOf(viaje.getIdViaje()));
                    vehiculosUsados.add(String.valueOf(viaje.getMatriculaVehiculo()));
            }
        }
        datosCliente[4]=String.join(",", vehiculosUsados);
        datosCliente[5]=String.join(",",viajesRealizados);

        return datosCliente;
    }

    public int editarCliente(String dni, String nombre, LocalDate fechaNacimiento){
        try{
            for (Cliente cliente: listaClientes){
                if (cliente.getDni().equals(dni)){
                    cliente.setNombre(nombre);
                    cliente.setFechaNacimiento(fechaNacimiento);
                }
            }
            return 0;
        } catch (Exception e) {
            return 100;
        }
    }

    public int borrarCliente(String dni){
        try{
            for (Cliente cliente: listaClientes){
                if (cliente.getDni().equals(dni)){
                    listaClientes.remove(cliente);
                }
            }
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    public ArrayList<String> listarClientes(){
        ArrayList<String> listadoClientes = new ArrayList<>();
        for (Cliente cliente: listaClientes){
            String stringCliente = cliente.getDni() + ", " + cliente.getNombre();
            listadoClientes.add(stringCliente);
        }
        return listadoClientes;
    }

    //Este método sobrecargado es solo para Autobuses
    public int crearVehiculo(String matricula, LocalDate fechaFabricacion, Autobus.Clase clase){
        System.out.println("Entro en crearvehiculo autobus");
        if (this.existeVehiculo(matricula)==true){
            return 1;
        }
        try{
            this.listaVehiculos.add(new Autobus(matricula, fechaFabricacion, clase));
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    //Este método sobrecargado es solo para taxis
    public int crearVehiculo(String matricula, LocalDate fechaFabricacion){
        System.out.println("Entro en crearvehiculo taxi");
        if (this.existeVehiculo(matricula)==true){
            return 1;
        }
        try{
            this.listaVehiculos.add(new Taxi(matricula, fechaFabricacion));
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    //Este método es su puta madre. Porque en listaVehiculos hay tanto autobuses como taxis con distinto
    //número de parámetros, entonces tengo que filtrar repetidamente según qué parámetros de búsqueda se hayan metido
    //para que al iterar no se busquen clases en los taxis y salte un error.
    public ArrayList<String> buscadorVehiculo(String matricula, Vehiculo.Tipo tipo, Autobus.Clase clase, LocalDate fechaFabricacion){
        ArrayList<String> listaCoincidencias = new ArrayList<>();

        boolean coincideMatricula = false;
        boolean coincideTipo = false;
        boolean coincideClase = false;
        boolean coincideFechaFabricacion = false;

        if (matricula.equals("")){
            coincideMatricula=true;
        }
        if (tipo == null) {
            coincideTipo = true;
        }
        if (clase == null) {
            coincideClase = true;
        }
        if (fechaFabricacion==null){
            coincideFechaFabricacion=true;
        }
System.out.println(", "+coincideMatricula+coincideTipo+coincideClase+coincideFechaFabricacion);
        // Si el tipo es null (no se ha introducido)
        if (tipo == null){
            try {
                for (Vehiculo vehiculo: listaVehiculos){
                    boolean coincideMatricula1 = false;
                    boolean coincideFechaFabricacion1 = false;
                    if (matricula.equals("")){
                        coincideMatricula1=true;
                    }
                    if (fechaFabricacion==null){
                        coincideFechaFabricacion1=true;
                    }
                    if(matricula.equals(vehiculo.getMatricula())){
                        coincideMatricula1=true;
                    }
                    if (fechaFabricacion==vehiculo.getFechaFabricacion()){
                        coincideFechaFabricacion1=true;
                    }
                    if (coincideMatricula1 && coincideFechaFabricacion1){
                        listaCoincidencias.add(vehiculo.getMatricula());
                    }
                }
            } catch (Exception e){
                listaCoincidencias.add("Error de datos");
                return listaCoincidencias;
            }
        }

        // Si el tipo es taxi
        if (tipo== Vehiculo.Tipo.TAXI){
            try {
                for (Vehiculo vehiculo: listaVehiculos){
                    boolean coincideMatricula1 = false;
                    boolean coincideFechaFabricacion1 = false;

                    if (matricula.equals("")){
                        coincideMatricula1=true;
                    }
                    if (fechaFabricacion==null){
                        coincideFechaFabricacion1=true;
                    }
                    if(matricula.equals(vehiculo.getMatricula())){
                        coincideMatricula1=true;
                    }
                    if (fechaFabricacion==vehiculo.getFechaFabricacion()){
                        coincideFechaFabricacion1=true;
                    }
                    if (coincideMatricula1 && coincideFechaFabricacion1 && vehiculo.getTipo()== Vehiculo.Tipo.TAXI){
                        listaCoincidencias.add(vehiculo.getMatricula());
                    }
                }
            } catch (Exception e){
                listaCoincidencias.add("Error de datos");
                return listaCoincidencias;
            }
        }

        // Si el tipo es autobus
        if (tipo== Vehiculo.Tipo.AUTOBUS){
            try {
                for (Vehiculo vehiculo: listaVehiculos){
                    boolean coincideMatricula1 = false;
                    boolean coincideFechaFabricacion1 = false;
                    boolean coincideClase1 = false;

                    if (matricula.equals("")){
                        coincideMatricula1=true;
                    }
                    if (fechaFabricacion==null){
                        coincideFechaFabricacion1=true;
                    }
                    if(matricula.equals(vehiculo.getMatricula())){
                        coincideMatricula1=true;
                    }
                    if (fechaFabricacion==vehiculo.getFechaFabricacion()){
                        coincideFechaFabricacion1=true;
                    }
                    if (vehiculo.getTipo()== Vehiculo.Tipo.AUTOBUS){
                        Autobus autobus = (Autobus)vehiculo;
                        if (autobus.getClase()==clase){
                            coincideClase1=true;
                        }
                    }
                    if (coincideMatricula1 && coincideFechaFabricacion1 && coincideClase1){
                        listaCoincidencias.add(vehiculo.getMatricula());
                    }
                }
            } catch (Exception e){
                listaCoincidencias.add("Error de datos");
                return listaCoincidencias;
            }
        }
        return listaCoincidencias;
    }

    public String[] consultarDatosVehiculo(String matricula){
        String[] datosVehiculo = new String[8];
        ArrayList<String> clientesTransportados = new ArrayList<>();
        ArrayList<String> conductoresVehiculo = new ArrayList<>();
        ArrayList<String> viajesRealizados = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (comprobarTipoVehiculo(matricula)==null){
            return datosVehiculo;
        }

        for (Vehiculo vehiculo: listaVehiculos){
            if (vehiculo.getMatricula().equals(matricula)){
                datosVehiculo[0]=vehiculo.getMatricula();
                datosVehiculo[1]=vehiculo.getFechaFabricacion().format(formatter);
                datosVehiculo[2]=vehiculo.getTipo().toString();
                if (datosVehiculo[2].equals("AUTOBUS")){
                    Autobus vehiculo1 = (Autobus) vehiculo;
                    datosVehiculo[3]=vehiculo1.getClase().toString();
                } else {
                    datosVehiculo[3]=null;
                }
                int antiguedad = Period.between(LocalDate.now(), vehiculo.getFechaFabricacion()).getYears();
                datosVehiculo[4] = String.valueOf(antiguedad);
            }
        }

        for (Viaje viaje:listaViajes){
            if (viaje.getMatriculaVehiculo().equals(matricula)){
                viajesRealizados.add(String.valueOf((viaje.getIdViaje())));
                if (!conductoresVehiculo.contains(viaje.getDniConductor())){
                    conductoresVehiculo.add(viaje.getDniConductor());
                }
                for (String dnicliente: viaje.getListaClientesViaje()){
                    if (!clientesTransportados.contains(dnicliente)){
                        clientesTransportados.add(dnicliente);
                    }
                }
            }
        }
        datosVehiculo[5]=String.join(",", viajesRealizados);
        datosVehiculo[6]=String.join(",", clientesTransportados);
        datosVehiculo[7]=String.join(",", conductoresVehiculo);
        return datosVehiculo;
    }

    public int editarVehiculo(String matricula, Vehiculo.Tipo tipo, Autobus.Clase clase, LocalDate fechaFabricacion){
        System.out.println("Llega esto a editarVehiculo en gestión");
        System.out.println(matricula + ", " + tipo + ", "+ clase);
        try{
            for (Vehiculo vehiculo: listaVehiculos){
                if (vehiculo.getMatricula().equals(matricula)){
                    vehiculo.setTipo(tipo);
                    if (tipo== Vehiculo.Tipo.AUTOBUS){
                        Autobus vehiculo1 = (Autobus) vehiculo;
                        vehiculo1.setClase(clase);
                    }
                    vehiculo.setFechaFabricacion(fechaFabricacion);
                }
            }
            return 0;
        } catch (Exception e) {
            return 100;
        }
    }

    public int borrarVehiculo(String matricula){
        try{
            for (Vehiculo vehiculo: listaVehiculos){
                if (vehiculo.getMatricula().equals(matricula)){
                    listaVehiculos.remove(vehiculo);
                    //Pongo un break porque estoy usando el bucle normal para borrar un elemento
                    break;
                }
            }
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    public ArrayList<String> listarVehiculos(){
        ArrayList<String> listadoVehiculos = new ArrayList<>();
        for (Vehiculo vehiculo: listaVehiculos){
            String stringVehiculo = vehiculo.getMatricula() + ", " + vehiculo.getTipo().toString();
            listadoVehiculos.add(stringVehiculo);
        }
        return listadoVehiculos;
    }

    public int crearViajeAutobus(LocalDate fecha, String origen, String destino, String matriculaVehiculo, String dniConductor,
                                 int kilometros) {

        System.out.println("Entro en crear viaje autobus");

        try{
            if (comprobarTipoVehiculo(matriculaVehiculo) == null) {
                return 1;
            }
            System.out.println("Sigo");
            if (comprobarTipoVehiculo(matriculaVehiculo) == Vehiculo.Tipo.TAXI) {
                return 2;
            }
            System.out.println("y sigo");
            if (!existeConductor(dniConductor)) {
                return 3;
            }
            System.out.println("paso el 3");
            if (!carnetValido(dniConductor, matriculaVehiculo)) {
                return 4;
            }
            System.out.println("paso el 4");

            int idViaje = listaViajes.size() + 1;
            listaViajes.add(new Viaje(idViaje, fecha, origen, destino, matriculaVehiculo, dniConductor, kilometros));
            System.out.println("retorno 0");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("retorno 100");
            return 100;
        }

    }
    public int crearViajeTaxi(LocalDate fecha, String origen, String destino, String matriculaVehiculo, String dniConductor,
                              int kilometros) {


            if (comprobarTipoVehiculo(matriculaVehiculo) == null) {
                return 1;
            }
            if (comprobarTipoVehiculo(matriculaVehiculo) == Vehiculo.Tipo.AUTOBUS) {
                return 2;
            }
            if (!existeConductor(dniConductor)) {
                return 3;
            }
            if (!carnetValido(dniConductor, matriculaVehiculo)) {
                return 4;
            }

            int idViaje = listaViajes.size() + 1;
            listaViajes.add(new Viaje(idViaje, fecha, origen, destino, matriculaVehiculo, dniConductor, kilometros));

            return 0;
    }


    public ArrayList<Integer> buscadorViaje(int idViaje, String matricula, Vehiculo.Tipo tipo, LocalDate fecha, String origen,
                                           String destino, int kilometros, String dniConductor, Viaje.Estado estado){

        ArrayList<Integer> listaCoincidencias = new ArrayList<>();

        boolean coincideIdViaje = false;
        boolean coincideMatricula = false;
        boolean coincideTipo = false;
        boolean coincideFecha = false;
        boolean coincideOrigen= false;
        boolean coincideDestino = false;
        boolean coincideKilometros = false;
        boolean coincideDniConductor = false;
        boolean coincideEstado = false;

        if (idViaje == 0){
            coincideIdViaje = true;
        }
        if (matricula.equals("")){
            coincideMatricula = true;
        }
        if (tipo == null){
            coincideTipo = true;
        }
        if (fecha == null){
            coincideFecha  =true;
        }
        if (origen.equals("")){
            coincideOrigen =true;
        }
        if (destino.equals("")){
            coincideDestino = true;
        }
        if (kilometros == 0){
            coincideKilometros = true;
        }
        if (dniConductor.equals("")){
            coincideDniConductor =true;
        }
        if (estado == null){
            coincideEstado =true;
        }

        for (Viaje viaje: listaViajes){
            boolean coincideIdViaje1 = false;
            boolean coincideMatricula1 = false;
            boolean coincideTipo1 = false;
            boolean coincideFecha1 = false;
            boolean coincideOrigen1 = false;
            boolean coincideDestino1 = false;
            boolean coincideKilometros1 = false;
            boolean coincideDniConductor1 = false;
            boolean coincideEstado1 = false;

            if (coincideIdViaje){
                coincideIdViaje1 = true;
            }
            if (coincideMatricula){
                coincideMatricula1=true;
            }
            if(coincideTipo){
                coincideTipo1=true;
            }
            if(coincideFecha){
                coincideFecha1=true;
            }
            if(coincideOrigen){
                coincideOrigen1=true;
            }
            if (coincideDestino){
                coincideDestino1=true;
            }
            if(coincideKilometros){
                coincideKilometros1=true;
            }
            if(coincideDniConductor){
                coincideDniConductor1=true;
            }
            if(coincideEstado){
                coincideEstado1=true;
            }

            if (viaje.getIdViaje() == idViaje){
                coincideIdViaje1=true;
            }
            if (viaje.getMatriculaVehiculo().equals(matricula)){
                coincideMatricula1=true;
            }
            if(comprobarTipoVehiculo(viaje.getMatriculaVehiculo())==tipo){
                coincideTipo1=true;
            }
            if(viaje.getFecha()==fecha){
                coincideFecha1=true;
            }
            if(viaje.getOrigen().equals(origen)){
                coincideOrigen1=true;
            }
            if (viaje.getDestino().equals(destino)){
                coincideDestino1=true;
            }
            if(viaje.getKilometros()==kilometros){
                coincideKilometros1=true;
            }
            if(viaje.getDniConductor()==dniConductor){
                coincideDniConductor1=true;
            }
            if(viaje.getEstado()==estado){
                coincideEstado1=true;
            }

            if (coincideIdViaje1 && coincideMatricula1 && coincideTipo1 && coincideFecha1 && coincideOrigen1 && coincideDestino1 && coincideKilometros1 && coincideDniConductor1 && coincideEstado1){
                listaCoincidencias.add(viaje.getIdViaje());
            }
        }
        return listaCoincidencias;
    }

    public String[] consultarDatosViaje (int idViaje){
        String[] datosViaje = new String[12];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Viaje viaje = null;
        for (Viaje viaje1: listaViajes){
            if (viaje1.getIdViaje()==idViaje){
                viaje=viaje1;
            }
        }
        datosViaje[0]= String.valueOf(viaje.getIdViaje());
        datosViaje[1]=viaje.getMatriculaVehiculo();
        datosViaje[2]= String.valueOf(comprobarTipoVehiculo(viaje.getMatriculaVehiculo()));
        datosViaje[3]= String.valueOf(viaje.getFecha().format(formatter));
        datosViaje[4]=viaje.getOrigen();
        datosViaje[5]=viaje.getDestino();
        datosViaje[6]= String.valueOf(viaje.getKilometros());
        datosViaje[7]=viaje.getDniConductor();
        datosViaje[8]= String.valueOf(viaje.getListaClientesViaje());
        datosViaje[9]= String.valueOf(viaje.getTarifa());
        datosViaje[10]= String.valueOf(viaje.getEstado());
        datosViaje[11] = String.valueOf(viaje.getListaClientesViaje().size());

        return datosViaje;
    }

    public int editarViaje (int idViaje, String matriculaVehiculo, LocalDate fecha, String origen,
                            String destino, int kilometros, String dniConductor, Viaje.Estado estado) {
        try{
            for (Viaje viaje: listaViajes){
                if (viaje.getIdViaje()  == idViaje){
                    if (!carnetValido(dniConductor, matriculaVehiculo))
                    {
                        return 1;
                    }
                    viaje.setMatriculaVehiculo(matriculaVehiculo);
                    viaje.setFecha(fecha);
                    viaje.setOrigen(origen);
                    viaje.setDestino(destino);
                    viaje.setKilometros(kilometros);
                    viaje.setDniConductor(dniConductor);
                    viaje.setEstado(estado);
                    viaje.calcularySetTarifa();
                }
            }
            return 0;
        } catch (Exception e) {
            return 100;
        }
    }

    public int cancelarViaje (int idViaje){
        try{
            if (!existeViaje(idViaje)){
                return 1;
            }
            Viaje viaje = null;
            for (Viaje viaje1: listaViajes){
                if(viaje1.getIdViaje()==idViaje){
                    viaje = viaje1;
                }
            }
            if (viaje.getEstado()== Viaje.Estado.CANCELADO){
                return 2;
            }
            if (viaje.getEstado()== Viaje.Estado.FINALIZADO){
                return 3;
            }
            viaje.setEstado(Viaje.Estado.CANCELADO);
            return 0;
        } catch (Exception e){
            return 100;
        }
    }

    public int eliminarClientedeViaje (int idViaje, String dniCliente){
        if (!existeCliente(dniCliente)){
            return 1;
        }
        if (!existeViaje(idViaje)){
            return 2;
        }

        Viaje viaje = null;
        for (Viaje viaje1: listaViajes){
            if (viaje1.getIdViaje()==idViaje){
                viaje = viaje1;
            }
        }
        int numClientesenViajeAntes = viaje.getListaClientesViaje().size();
        if (numClientesenViajeAntes==0){
            return 4;
        }
        boolean clienteEnViaje = false;
        for (String cliente: viaje.getListaClientesViaje()){
            if (cliente.equals(dniCliente)){
                clienteEnViaje=true;
            }
        }
        if (clienteEnViaje==false){
            return 3;
        }
        viaje.eliminarClientedeViaje(dniCliente);
        return 0;
    }

    public int agregarClienteaViaje (int idViaje, String dniCliente) {
        if (!existeCliente(dniCliente)){
            return 3;
        }
        if (!existeViaje(idViaje)){
            return 4;
        }

        Viaje viaje = null;
        for (Viaje viaje1: listaViajes){
            if (viaje1.getIdViaje() == idViaje){
                viaje = viaje1;
            }
        }
        return viaje.agregarClienteaViaje(dniCliente);
    }

    public ArrayList<String> listarViajes(){
        ArrayList<String> listadoViajes = new ArrayList<>();
        for (Viaje viaje: listaViajes){
            String stringViaje = viaje.getIdViaje() + ", " + viaje.getOrigen() +", " + viaje.getDestino() + ", " + viaje.getFecha();
            listadoViajes.add(stringViaje);
        }
        return listadoViajes;
    }

    public boolean carnetValido(String dni, String matricula){
        Conductor conductor = null;
        Vehiculo vehiculo = null;
        for (Conductor i: listaConductores){
            if (i.getDni().equals(dni)){
                conductor=i;
            }
        }
        for (Vehiculo j: listaVehiculos){
            if (j.getMatricula().equals(matricula)){
                vehiculo=j;
            }
        }
        if (conductor.getTipoCarnet()==Conductor.TipoCarnet.B && vehiculo.getTipo()== Vehiculo.Tipo.AUTOBUS){
            return false;
        } else{
            return true;
        }
    }

    public Vehiculo.Tipo comprobarTipoVehiculo(String matricula){
        try{
            for (Vehiculo vehiculo: listaVehiculos) {
                if (vehiculo.getMatricula().equals(matricula)){
                    return vehiculo.getTipo();
                }
            }
        } catch (Exception e){
                return null;
            }
        return null;
    }


    public boolean existeConductor(String dni){
        boolean existe = false;
        for (Conductor conductor: listaConductores){
            if(conductor.getDni().equals(dni)){
                existe=true;
            }
        }
        return existe;
    }

    public boolean existeCliente(String dni){
        boolean existe = false;
        for (Cliente cliente: listaClientes){
            if(cliente.getDni().equals(dni)){
                existe=true;
            }
        }
        return existe;
    }

    public boolean existeVehiculo(String matricula){
        boolean existe = false;
        for (Vehiculo vehiculo: listaVehiculos){
            if(vehiculo.getMatricula().equals(matricula)){
                existe=true;
            }
        }
        return existe;
    }

    public boolean existeViaje(int idViaje){
        boolean existe = false;
        for (Viaje viaje: listaViajes){
            if(viaje.getIdViaje()==idViaje){
                existe=true;
            }
        }
        return existe;
    }

    public Vehiculo obtenerVehiculo(String matricula){
        Vehiculo vehiculo = null;
        for (Vehiculo vehiculo1: listaVehiculos){
            if (vehiculo1.getMatricula().equals(matricula)){
                vehiculo = vehiculo1;
            }
        }
        return vehiculo;
    }

    public Viaje obtenerViaje(int idViaje){
        Viaje viaje = null;
        for (Viaje viaje1: listaViajes){
            if (viaje1.getIdViaje()==idViaje){
                viaje = viaje1;
            }
        }
        return viaje;
    }

    public boolean comprobarViajesVacios (int idViaje){
        Viaje viaje = null;
        for (Viaje viaje1: listaViajes){
            if (viaje1.getIdViaje()==idViaje){
                viaje=viaje1;
            }
        }
        if (viaje.getListaClientesViaje().isEmpty()){
            return true;
        }
        return false;
    }

    public void cargarDatos() {
        try{
            ObjectInputStream deserializador = new ObjectInputStream(new FileInputStream("datos.bin"));
            listaVehiculos = (ArrayList<Vehiculo>) deserializador.readObject();
            listaConductores = (ArrayList<Conductor>) deserializador.readObject();
            listaClientes = (ArrayList<Cliente>) deserializador.readObject();
            listaViajes = (ArrayList<Viaje>) deserializador.readObject();
            deserializador.close();
            System.out.println("cargado datos.bin");

        }catch(IOException e){
            try{
                System.out.println("No cargado datos.bin y crea uno nuevo");
                File binFile = new File("datos.bin");
                if (!binFile.exists()) {
                    binFile.createNewFile();
                    listaVehiculos = new ArrayList<Vehiculo>();
                    listaConductores = new ArrayList<Conductor>();
                    listaClientes = new ArrayList<Cliente>();
                    listaViajes = new ArrayList<Viaje>();
                }
            } catch(IOException ex){
                e.printStackTrace();
            }
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void guardarDatos() {
        try {
            ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream("datos.bin"));
            serializador.writeObject(listaVehiculos);
            serializador.writeObject(listaConductores);
            serializador.writeObject(listaClientes);
            serializador.writeObject(listaViajes);
            serializador.close();
            System.out.println("Datos guardados");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
