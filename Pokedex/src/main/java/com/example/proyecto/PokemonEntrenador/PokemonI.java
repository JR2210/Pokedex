package com.example.proyecto.PokemonEntrenador;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PokemonI {
    // Crea una nueva entidad Pokemon en la base de datos
    void crearPokemon(Pokemon pokemon) throws SQLException;

    // Actualiza una entidad Pokemon existente en la base de datos
    void actualizarPokemon(Pokemon pokemon) throws SQLException;

    // Elimina una entidad Pokemon de la base de datos
    void eliminarPokemon(int pokemonId) throws SQLException;

    // Elimina las entidades Pokemon asociadas a un Entrenador mediante su entrenadorId
    void eliminarPokemonEntrenador(int entrenadorId) throws SQLException;

    // Listar todos los pokemons de la base de datos
    ObservableList<Pokemon> obtenerTodosPokemons() throws SQLException;
}