package com.example.proyecto.Controladores;

import com.example.proyecto.PokemonEntrenador.Pokemon;
import com.example.proyecto.PokemonEntrenador.PokemonDao;
import com.example.proyecto.Servidor.Servidor;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControladorEliminarPokemon {
    @FXML
    private TextField id;
    private PokemonDao pokemon;
    private Stage dialogStage;
    Alert alert;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void initialize() {
        Servidor servidor = Servidor.obtenerInstancia();
        pokemon = new PokemonDao(servidor);
        alert = crearAlerta();
    }

    @FXML
    public void eliminar() {
        if (isInputValid()) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmaciónn para eliminar");
            confirm.setContentText("¿Está seguro de eliminar la fila actual?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        this.pokemon.eliminarPokemon(Integer.parseInt(id.getText()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            dialogStage.close();
        } else {
            System.out.println("Error al eliminar");
        }
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();
        if (id.getText() == null || id.getText().length() == 0) {
            errorMessage.append("El campo id está vacío\n");
        } else if (!existeId(Integer.parseInt(id.getText()))) {
            errorMessage.append("No existe un pokemon con ese id\n");
        }
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            errorAlert.setTitle("Hay campos incorrectos");
            errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
            errorAlert.setContentText(errorMessage.toString());

            errorAlert.showAndWait();
            return false;
        }
    }


    private boolean existeId(int id) {
        try {
            ObservableList<Pokemon> listaPokemon = pokemon.obtenerTodosPokemons();
            for (Pokemon pokemons : listaPokemon) {
                if (id == pokemons.getPokemonId()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    private Alert crearAlerta() {
        Alert auxAlert = new Alert(Alert.AlertType.ERROR);
        auxAlert.setTitle("Error");
        auxAlert.setHeaderText("Alert de error");
        auxAlert.setContentText("Formato de texto incorrecto");

        return auxAlert;
    }
}
