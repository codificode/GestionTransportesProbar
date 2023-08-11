module com.example.gestiontransportes {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.gestiontransportes to javafx.fxml;
    exports com.example.gestiontransportes;
}