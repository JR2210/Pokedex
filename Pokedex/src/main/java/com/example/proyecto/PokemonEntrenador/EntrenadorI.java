package com.example.proyecto.PokemonEntrenador;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface EntrenadorI {
    // Crea una nueva entidad Entrenador en la base de datos
    void crearEntrenador(Entrenador entrenador) throws SQLException;

    // Obtiene todas las entidades Entrenador de la base de datos
    ObservableList<Entrenador> obtenerTodosLosEntrenadores() throws SQLException;

    // Elimina una entidad Entrenador de la base de datos
    void eliminarEntrenador(int id) throws SQLException;

    // Obtiene todos los Pok�mon de un Entrenador de la base de datos a trav�s de su identificador
    List<Pokemon> obtenerPokemonDeEntrenador(int id) throws SQLException;

}