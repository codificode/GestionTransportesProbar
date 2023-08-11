package com.example.gestiontransportes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("principal.fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Gestión de transporte - Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    static void cambioTitulo(String titulo){
       stage.setTitle(titulo);
    }


    public static void main(String[] args) {
        Gestion gestion = new Gestion();
        gestion.cargarDatos();
        launch();
        gestion.guardarDatos();
        System.out.println("Ha finalizado el programa");
    }
}
