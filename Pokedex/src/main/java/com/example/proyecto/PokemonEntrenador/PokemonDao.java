package com.example.proyecto.PokemonEntrenador;

import com.example.proyecto.Servidor.Servidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PokemonDao implements PokemonI {
    private final Servidor servidor;

    public PokemonDao(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void crearPokemon(Pokemon pokemon) throws SQLException {
        String insertar = "INSERT INTO TPokemon(nPokemonID, cNombre, cTipo, nEntrenadorID) " +
                "values(?,?,?,?);";
        PreparedStatement insertarStatement = servidor.conectarBaseDatos().prepareStatement(insertar);
        insertarStatement.setInt(1,pokemon.getPokemonId());
        insertarStatement.setString(2,pokemon.getNombre());
        insertarStatement.setString(3,pokemon.getTipo().toString());
        insertarStatement.setInt(4,pokemon.getEntrenadorId());
        int registrosInsertados = insertarStatement.executeUpdate();
        if (registrosInsertados > 0) {
            System.out.println("Se ha insertado un nuevo registro.");
        }
    }

    public int ultimoID() throws SQLException {
        Statement stmt = servidor.conectarBaseDatos().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(nPokemonID) FROM TPokemon");
        rs.next();
        long lastId = rs.getLong(1);
        rs.close();
        stmt.close();
        return (int)lastId;
    }

    @Override
    public void actualizarPokemon(Pokemon pokemon) throws SQLException {
        String actualizar = "UPDATE TPokemon SET cNombre = ?, " +
                "cTipo = ?, nEntrenadorID = ? WHERE nPokemonID = ?";
        PreparedStatement actualizarStatement = servidor.conectarBaseDatos().prepareStatement(actualizar);
        actualizarStatement.setString(1,pokemon.getNombre());
        actualizarStatement.setString(2,pokemon.getTipo().toString());
        actualizarStatement.setInt(3,pokemon.getEntrenadorId());
        actualizarStatement.setInt(4,pokemon.getPokemonId());
        int registrosActualizados = actualizarStatement.executeUpdate();
        if (registrosActualizados > 0) {
            System.out.println("Se han actualizado " + registrosActualizados + " registros");
        }
    }

    @Override
    public void eliminarPokemon(int pokemonId) throws SQLException {
        String borrado = "DELETE FROM TPokemon WHERE nPokemonID = ?";
        PreparedStatement borrarStatement = servidor.conectarBaseDatos().prepareStatement(borrado);
        borrarStatement.setInt(1,pokemonId);
        int registrosBorrados = borrarStatement.executeUpdate();
        if (registrosBorrados > 0) {
            System.out.println("Se han borrado " + registrosBorrados + " registros");
        }
    }

    @Override
    public void eliminarPokemonEntrenador(int entrenadorId) throws SQLException {
        String borrado = "DELETE FROM TPokemon WHERE nEntrenadorId = ?";
        PreparedStatement borrarStatement = servidor.conectarBaseDatos().prepareStatement(borrado);
        borrarStatement.setInt(1,entrenadorId);
        int registrosBorrados = borrarStatement.executeUpdate();
        if (registrosBorrados > 0) {
            System.out.println("Se han borrado " + registrosBorrados + " registros");
        }
    }

    @Override
    public ObservableList<Pokemon> obtenerTodosPokemons() throws SQLException {
        String query = "SELECT * FROM TPokemon";
        Statement stm = servidor.conectarBaseDatos().createStatement();
        ObservableList<Pokemon> lista = FXCollections.observableArrayList();
        try (ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                Tipo tipo = Tipo.valueOf(rs.getString(3));
                Pokemon pokemon = new Pokemon(rs.getInt(1),rs.getString(2)
                        , tipo, rs.getInt(4));
                lista.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}