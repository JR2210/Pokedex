package com.example.proyecto.PokemonEntrenador;

import com.example.proyecto.Servidor.Servidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntrenadorDao implements EntrenadorI{
    private final Servidor servidor;

    public EntrenadorDao(Servidor servidor) {
        this.servidor = servidor;
    }



    // 7, Jorge, Rasho
    @Override
    public void crearEntrenador(Entrenador entrenador) throws SQLException {
        String insertar = "INSERT INTO TEntrenador(nEntrenadorID, cNombre, cApodo) " +
                "values(?,?,?);";
        PreparedStatement insertarStatement = servidor.conectarBaseDatos().prepareStatement(insertar);
        insertarStatement.setInt(1,entrenador.getId());
        insertarStatement.setString(2,entrenador.getNombre());
        insertarStatement.setString(3,entrenador.getApodo());
        int registrosInsertados = insertarStatement.executeUpdate();
        if (registrosInsertados > 0) {
            System.out.println("Se ha insertado un nuevo registro.");
        }
    }

    @Override
    public ObservableList<Entrenador> obtenerTodosLosEntrenadores() throws SQLException {
        ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();
        String buscar = "SELECT * FROM TEntrenador;";
        Statement buscarStatement = servidor.conectarBaseDatos().prepareStatement(buscar);
        try (ResultSet rs = buscarStatement.executeQuery(buscar)) {
            while (rs.next()) {
                Entrenador entrenador = new Entrenador(rs.getInt(1),rs.getString(2),
                        rs.getString(3));
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }


    public int ultimoID() throws SQLException {
        Statement stmt = servidor.conectarBaseDatos().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(nEntrenadorID) FROM TEntrenador");
        rs.next();
        long lastId = rs.getLong(1);
        rs.close();
        stmt.close();
        return (int)lastId;
    }

    @Override
    public void eliminarEntrenador(int id) throws SQLException {
        String borrado = "DELETE FROM TEntrenador WHERE nEntrenadorID = ?";
        PreparedStatement borrarStatement = servidor.conectarBaseDatos().prepareStatement(borrado);
        borrarStatement.setInt(1,id);
        int registrosBorrados = borrarStatement.executeUpdate();
        if (registrosBorrados > 0) {
            System.out.println("Se han borrado " + registrosBorrados + " registros");
        }
    }

    @Override
    public ObservableList<Pokemon> obtenerPokemonDeEntrenador(int id) throws SQLException {
        String query = "SELECT * FROM TPokemon WHERE nEntrenadorID = "+id+";";
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