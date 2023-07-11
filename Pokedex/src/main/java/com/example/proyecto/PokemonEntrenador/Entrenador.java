package com.example.proyecto.PokemonEntrenador;

public class Entrenador {
    private int id;
    private String nombre;
    private String apodo;

    public Entrenador(int id, String nombre, String apodo) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
    }

    public Entrenador() {

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    @Override
    public String toString() {
        return nombre+" también conocido como "+apodo+" con un EntrenadorId: "+id;
    }
}