package com.example.proyecto.Controladores;

import com.example.proyecto.PokemonEntrenador.Entrenador;
import com.example.proyecto.PokemonEntrenador.EntrenadorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControladorEntrenador {

    private ObservableList<Entrenador> listaEntrenadores = FXCollections.observableArrayList();
    @FXML
    protected TextField nombre;
    @FXML
    protected TextField apodo;
    @FXML
    protected TextField id;
    Alert alert;
    private Stage dialogStage;
    private EntrenadorDao entrenador;

    @FXML
    private void initialize() {
        alert = crearAlerta();
    }

    public void setListaEntrenadores(ObservableList<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEntrenador(EntrenadorDao entrenador) {
        this.entrenador = entrenador;
    }

    // Función auxiliar para crear alert
    private Alert crearAlerta() {
        Alert auxAlert = new Alert(Alert.AlertType.ERROR);
        auxAlert.setTitle("Error");
        auxAlert.setHeaderText("Alert de error");
        auxAlert.setContentText("Formato de texto incorrecto");

        return auxAlert;
    }

    @FXML
    protected void agregarEntrenador() throws SQLException {
        if (isInputValid()) {
            Entrenador aux = new Entrenador(Integer.parseInt(id.getText())
                    , nombre.getText(), apodo.getText());
            entrenador.crearEntrenador(aux);
            listaEntrenadores.add(aux);
            dialogStage.close();
        }
    }

    /**
     * Valida los campos de texto del usuario
     *
     * @return true si los campos son validos
     */
    private boolean isInputValid() throws SQLException {
        StringBuilder errorMessage = new StringBuilder();

        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage.append("El campo Nombre está vacío\n");
        }
        if (apodo.getText() == null || apodo.getText().length() == 0) {
            errorMessage.append("El campo apodo está vacío\n");
        }
        if (id.getText() == null || id.getText().length() == 0) {
            errorMessage.append("El campo id está vacío\n");
        } else {
            for (Entrenador listaEntrenadores : listaEntrenadores) {
                if (listaEntrenadores.getId() == Integer.parseInt(id.getText())) {
                    errorMessage.append("El id está en uso\nUltimoID conocido: ").append(entrenador.ultimoID());
                }
            }
        }


        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Se muestra un alert si no se puede eliminar la fila
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            errorAlert.setTitle("Hay campos incorrectos");
            errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
            errorAlert.setContentText(errorMessage.toString());

            errorAlert.showAndWait();
            return false;
        }
    }
}
