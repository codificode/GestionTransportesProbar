package com.example.gestiontransportes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlPantallas {
    public String tituloGeneral = "Gestión de transporte";
    public String titulo = tituloGeneral;

    public Scene scene;
    public Stage stage;


    public void cambioRootyTitulo (String fxml) throws IOException {


        if (fxml.equals("principal")){
            titulo = tituloGeneral + " - Principal";
        } else if (fxml.equals("conductores")) {
            titulo = tituloGeneral + " - Conductores";
        } else if (fxml.equals("clientes")) {
            titulo = tituloGeneral + " - Clientes";
        }
        stage = Main.getStage();
        stage.setTitle(titulo);
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("conductores.fxml"));
        Parent root = fxmlLoader2.load();

        ConductoresController conductoresController = fxmlLoader2.getController();
        conductoresController.cambiartextoprueba("hola");


        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("Vete a la mierda");
    }
}
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loader.load();

        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.displayName(username);

        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 */