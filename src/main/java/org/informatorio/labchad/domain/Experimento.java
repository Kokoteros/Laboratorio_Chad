package org.informatorio.labchad.domain;

public abstract class Experimento {
    private String nombre;
    private int duracion; // en minutos
    private boolean exito;

    public Experimento(String nombre, int duracion, boolean exito) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.exito = exito;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean isExito() {
        return exito;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        String resultado = exito ? "Éxito" : "Fallo";
        return "Experimento: " + nombre + " | Duración: " + duracion + " min | Resultado: " + resultado;
    }
}