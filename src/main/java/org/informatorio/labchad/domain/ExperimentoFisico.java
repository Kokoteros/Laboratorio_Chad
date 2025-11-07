package org.informatorio.labchad.domain;
import java.util.List;

public class ExperimentoFisico extends Experimento {
    private String instrumento;
    private List<Investigador> investigadores;

    public ExperimentoFisico(String nombre, int duracion, boolean exito,
                             String instrumento, List<Investigador> investigadores) {
        super(nombre, duracion, exito);
        this.instrumento = instrumento;
        this.investigadores = investigadores;

        // PAra aumentar la cantidad de experimentos c/u investigador
        for (Investigador i : investigadores) {
            i.incrementarExperimentos();
        }
    }

    @Override
    public String getTipo() {
        return "Físico";
    }

    @Override
    public String toString() {
        StringBuilder nombres = new StringBuilder();
        for (Investigador i : investigadores) {
            nombres.append(i.getNombre()).append(", ");
        }
        if (nombres.length() > 2)
            nombres.setLength(nombres.length() - 2);

        return super.toString() + " | Tipo: Físico | Instrumento: " + instrumento +
                " | Investigadores: [" + nombres + "]";
    }
}