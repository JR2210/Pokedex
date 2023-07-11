module com.example.proyectocongui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyecto to javafx.fxml;
    exports com.example.proyecto;
    exports com.example.proyecto.Servidor;
    opens com.example.proyecto.Servidor to javafx.fxml;
    exports com.example.proyecto.PokemonEntrenador;
    opens com.example.proyecto.PokemonEntrenador to javafx.fxml;
    exports com.example.proyecto.Controladores;
    opens com.example.proyecto.Controladores to javafx.fxml;
}