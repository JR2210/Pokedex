package com.example.proyecto.Controladores;

import com.example.proyecto.PokemonEntrenador.Pokemon;
import com.example.proyecto.Servidor.Servidor;
import com.example.proyecto.PokemonEntrenador.Entrenador;
import com.example.proyecto.HelloApplication;
import com.example.proyecto.PokemonEntrenador.EntrenadorDao;
import com.example.proyecto.PokemonEntrenador.PokemonDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControladorPrincipal {
    @FXML
    private TableView<Entrenador> tabla;
    @FXML
    private TableColumn<Entrenador, String> entrenadorId;
    @FXML
    private TableColumn<Entrenador, String> nombreEntrenador;
    @FXML
    private TableColumn<Entrenador, String> apodoEntrenador;
    @FXML
    private TableView<Pokemon> tablaPokemon;
    @FXML
    private TableColumn<Pokemon, String> pokemonEntrenadorId;
    @FXML
    private TableColumn<Pokemon, String> pokemonId;
    @FXML
    private TableColumn<Pokemon, String> nombrePokemon;
    @FXML
    private TableColumn<Pokemon, String> tipo;
    private ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();
    private ObservableList<Pokemon> pokemons = FXCollections.observableArrayList();
    private EntrenadorDao entrenador;
    private PokemonDao pokemon;

    @FXML
    public void initialize() throws SQLException {
        Servidor conexionBaseDatos = Servidor.obtenerInstancia();
        entrenador = new EntrenadorDao(conexionBaseDatos);
        pokemon = new PokemonDao(conexionBaseDatos);
        inicializaLista();
        entrenadorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreEntrenador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apodoEntrenador.setCellValueFactory(new PropertyValueFactory<>("apodo"));
        nombrePokemon.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        pokemonId.setCellValueFactory(new PropertyValueFactory<>("pokemonId"));
        pokemonEntrenadorId.setCellValueFactory(new PropertyValueFactory<>("entrenadorId"));
        tabla.setItems(entrenadores);
        tabla.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !tabla.getSelectionModel().isEmpty()) {
                try {
                    mostrarPokemonsEntrenador(tabla.getSelectionModel().getSelectedItem());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }});
    }

    private void inicializaLista() throws SQLException {
        entrenadores = entrenador.obtenerTodosLosEntrenadores();
        pokemons = pokemon.obtenerTodosPokemons();
    }

    private void mostrarPokemonsEntrenador(Entrenador entrenador) throws SQLException {
        pokemons = this.entrenador.obtenerPokemonDeEntrenador(entrenador.getId());
        tablaPokemon.setItems(pokemons);
    }

    @FXML
    private void anadirEntrenador() {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("vistaEntrenadores.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 520, 340);

            // Se crea un nuevo Stage para mostrar el diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Crear un Entrenador");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene2);

            ControladorEntrenador controller2 = fxmlLoader2.getController();
            controller2.setDialogStage(dialogStage);

            controller2.setListaEntrenadores(entrenadores);
            //si quiero editar una persona le mando los datos
            controller2.setEntrenador(entrenador);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void anadirPokemon() throws SQLException {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("vistaAgregarPokemon.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 520, 340);

            // Se crea un nuevo Stage para mostrar el diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Crear un Pokemon");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene2);

            ControladorAnadirPokemon controller = fxmlLoader2.getController();
            controller.setDialogStage(dialogStage);

            controller.setPokemon(pokemon);
            controller.setListaPokemons(pokemons);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        mostrarPokemonsEntrenador(tabla.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void modificarPokemon() throws SQLException {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("vistaModificarPokemon.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 520, 340);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modificar un Pokemon");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene2);

            ControladorModificarPokemon controller = fxmlLoader2.getController();
            controller.setDialogStage(dialogStage);
            controller.setPokemon(pokemon);
            controller.setListaPokemons(pokemons);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        mostrarPokemonsEntrenador(tabla.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void eliminarEntrenador() throws SQLException {
        int selectedIndex = tabla.getSelectionModel().getSelectedIndex();
        Entrenador entrenadorAux = tabla.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            // Si se ha seleccionado una fila, se muestra un pop up de confirmaci�n
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

            confirm.setTitle("Confirmaciónn para eliminar");
            //errorAlert.setHeaderText("Va a eliminar la fila seleccionada");
            confirm.setContentText("¿Está seguro de eliminar la fila actual?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        pokemon.eliminarPokemonEntrenador(entrenadorAux.getId());
                        entrenador.eliminarEntrenador(entrenadorAux.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    tabla.getItems().remove(selectedIndex);
                }
            });
        } else {
            // Se muestra un alert si no se puede eliminar la fila
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);

            errorAlert.setTitle("Error al eliminar");
            errorAlert.setHeaderText("Se ha producido un error");
            errorAlert.setContentText("No se puede eliminar porque no ha seleccionado una fila o la tabla está vacía");

            errorAlert.showAndWait();
        }
        mostrarPokemonsEntrenador(tabla.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void eliminarPokemon() throws SQLException {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("vistaEliminarPokemon.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 520, 340);

            // Se crea un nuevo Stage para mostrar el diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Eliminar un Pokemon");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene2);

            ControladorEliminarPokemon controller = fxmlLoader2.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        mostrarPokemonsEntrenador(tabla.getSelectionModel().getSelectedItem());
    }
}