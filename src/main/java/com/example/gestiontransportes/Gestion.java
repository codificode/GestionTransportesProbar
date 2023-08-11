package com.example.gestiontransportes;

import java.io.*;
import java.util.ArrayList;

public class Gestion {

    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private ArrayList<Conductor> listaConductores = new ArrayList<>();
    private ArrayList<Cliente> listaClientes =  new ArrayList<>();
    private ArrayList<Viaje> listaViajes = new ArrayList<>();


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
