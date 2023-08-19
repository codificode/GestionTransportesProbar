package com.example.gestiontransportes;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public final class ControlPantallas {
    public String tituloGeneral = "Gestión de transporte";
    public String titulo = tituloGeneral;

    public Scene scene;
    public Stage stage;
    private static final ControlPantallas INSTANCE = new ControlPantallas();

    public static ControlPantallas getInstance() {
        return INSTANCE;
    }

    public void cambioRootyTitulo(String fxml) throws IOException {

/*
        if (fxml.equals("principal")){
            titulo = tituloGeneral + " - Principal";
        } else if (fxml.equals("conductores")) {
            titulo = tituloGeneral + " - Conductores";
        } else if (fxml.equals("clientes")) {
            titulo = tituloGeneral + " - Clientes";
        }
 */

        titulo = tituloGeneral + " - " + fxml.substring(0, 1).toUpperCase() + fxml.substring(1);
        stage = Main.getStage();
        stage.setTitle(titulo);
        //Stage stage = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        Parent root = fxmlLoader2.load();

        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();


    }


    public void mostrarPopup1(Control owner, String mensaje) {
        Popup popup = new Popup();

        StackPane popupContent = new StackPane();
        popupContent.setStyle("-fx-background-color: lightgray;");

        Label messageLabel = new Label(mensaje);
        messageLabel.setStyle("-fx-font-size: 14pt; -fx-padding: 10;");

        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(event -> popup.hide());  // Cierra el popup al hacer clic en el botón

        StackPane.setAlignment(closeButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(closeButton, new Insets(10));

        popupContent.getChildren().addAll(messageLabel, closeButton);

        popup.getContent().add(popupContent);
        popupContent.setPrefSize(300, 150);

        // Muestra el popup cuando se carga la pantalla
        popup.show(owner.getScene().getWindow());
    }


}


/*
    private ConductoresController cargarControlador(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        principalid.getChildren().setAll(root);

        ConductoresController controller = loader.getController();
        return controller;
    }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loader.load();

        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.displayName(username);

     */


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