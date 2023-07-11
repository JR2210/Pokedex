package com.example.proyecto.Controladores;

import com.example.proyecto.PokemonEntrenador.*;
import com.example.proyecto.Servidor.Servidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControladorModificarPokemon {
    @FXML
    private TextField nombre;
    @FXML
    private ChoiceBox<Tipo> tipo;
    @FXML
    private ChoiceBox<Entrenador> entrenadorId;
    @FXML
    private ChoiceBox<Pokemon> pokemonId;
    Alert alert;
    private Stage dialogStage;

    private PokemonDao pokemon;
    private ObservableList<Pokemon> listaPokemons;

    public void setListaPokemons(ObservableList<Pokemon> listaPokemons) {
        this.listaPokemons = listaPokemons;
    }

    public void setPokemon (PokemonDao pokemon) {
        this.pokemon = pokemon;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void initialize() throws SQLException {
        Servidor servidor = Servidor.obtenerInstancia();
        pokemon = new PokemonDao(servidor);
        EntrenadorDao entrenador = new EntrenadorDao(servidor);
        alert = crearAlerta();
        entrenadorId.setItems(entrenador.obtenerTodosLosEntrenadores());
        pokemonId.setItems(pokemon.obtenerTodosPokemons());
        tipo.setItems(FXCollections.observableArrayList(Tipo.values()));
    }

    @FXML
    protected void modificarPokemon() throws SQLException {
        if (isInputValid()) {
            Pokemon pokemonAux = new Pokemon(pokemonId.getSelectionModel().getSelectedItem().getPokemonId(),
                    nombre.getText(), Tipo.valueOf(tipo.getSelectionModel().getSelectedItem().toString()),entrenadorId.getSelectionModel().getSelectedItem().getId());
            this.pokemon.actualizarPokemon(pokemonAux);
            listaPokemons = FXCollections.observableArrayList(listaPokemons);
            dialogStage.close();
        }
    }

    /**
     * Valida los campos de texto del usuario
     *
     * @return true si los campos son validos
     */
    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();
        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage.append("El campo nombre está vacío\n");
        }
        if (tipo.getSelectionModel().isEmpty()) {
            errorMessage.append("No se ha seleccionado el campo tipo\n");
        }
        if(entrenadorId.getSelectionModel().isEmpty()) {
            errorMessage.append("El campo entrenador no está seleccionado\n");
        }
        if(pokemonId.getSelectionModel().isEmpty()) {
            errorMessage.append("No se ha seleccionado el pokemon que quieres modificar\n");
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

    private Alert crearAlerta() {
        Alert auxAlert = new Alert(Alert.AlertType.ERROR);
        auxAlert.setTitle("Error");
        auxAlert.setHeaderText("Alert de error");
        auxAlert.setContentText("Formato de texto incorrecto");

        return auxAlert;
    }
}
