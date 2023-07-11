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

public class ControladorAnadirPokemon {
    @FXML
    private TextField nombre;
    @FXML
    private ChoiceBox<Tipo> tipo;
    @FXML
    private ChoiceBox<Entrenador> entrenadorId;
    @FXML
    private TextField pokemonId;

    private ObservableList<Pokemon> listaPokemons;
    Alert alert;
    private Stage dialogStage;

    private PokemonDao pokemon;

    public void setPokemon (PokemonDao pokemon) {this.pokemon = pokemon;}
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setListaPokemons(ObservableList<Pokemon> listaPokemons) {
        this.listaPokemons = listaPokemons;
    }

    @FXML
    public void initialize() throws SQLException {
        Servidor servidor = Servidor.obtenerInstancia();
        pokemon = new PokemonDao(servidor);
        EntrenadorDao entrenador = new EntrenadorDao(servidor);
        alert = crearAlerta();
        entrenadorId.setItems(entrenador.obtenerTodosLosEntrenadores());
        tipo.setItems(FXCollections.observableArrayList(Tipo.values()));
    }

    @FXML
    protected void anadirPokemon() throws SQLException {
        if (isInputValid()) {
            Pokemon pokemonAux = new Pokemon(Integer.parseInt(pokemonId.getText()),
                    nombre.getText(), Tipo.valueOf(tipo.getSelectionModel().getSelectedItem().toString()),entrenadorId.getSelectionModel().getSelectedItem().getId());
            this.pokemon.crearPokemon(pokemonAux);
            listaPokemons.add(pokemonAux);
            dialogStage.close();
        } else {
            System.out.println("Error al agregar");
        }
    }

    /**
     * Valida los campos de texto del usuario
     *
     * @return true si los campos son validos
     */
    private boolean isInputValid() throws SQLException {
        StringBuilder errorMessage = new StringBuilder();
        if (pokemonId.getText() == null || pokemonId.getText().length() == 0) {
            errorMessage.append("El campo id está vacío\n");
        } else {
            for (Pokemon listaPokemon : listaPokemons) {
                if (listaPokemon.getPokemonId() == Integer.parseInt(pokemonId.getText())) {
                    errorMessage.append("El id está en uso\nUltimoID conocido: ").append(pokemon.ultimoID()).append("\n");
                }
            }
        }
        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage.append("El campo Nombre está vacío\n");
        }
        if (tipo.getSelectionModel().isEmpty()) {
            errorMessage.append("El campo tipo no se ha seleccionado\n");
        }
        if(entrenadorId.getSelectionModel().getSelectedItem() == null) {
            errorMessage.append("El campo entrenadorId no se ha seleccionado\n");
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
