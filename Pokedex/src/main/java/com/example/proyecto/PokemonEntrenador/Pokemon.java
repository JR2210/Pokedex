package com.example.proyecto.PokemonEntrenador;

public class Pokemon {
    private int pokemonId;
    private String nombre;
    private Tipo tipo;
    private int entrenadorId;

    public Pokemon(int pokemonId, String nombre, Tipo tipo, int entrenadorId) {
        this.pokemonId = pokemonId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.entrenadorId = entrenadorId;
    }

    public Pokemon() {

    }

    public int getPokemonId() {
        return pokemonId;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    @Override
    public String toString() {
        return nombre+" de tipo "+tipo+". EntrenadorId: "+entrenadorId+" PokemonId: "+pokemonId;
    }
}