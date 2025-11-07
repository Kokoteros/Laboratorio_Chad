package org.informatorio.labchad.domain;
public class ExperimentoQuimico extends Experimento {
    private String tipoReactivo;
    private Investigador investigador;

    public ExperimentoQuimico(String nombre, int duracion, boolean exito,
                              String tipoReactivo, Investigador investigador) {
        super(nombre, duracion, exito);
        this.tipoReactivo = tipoReactivo;
        this.investigador = investigador;
        investigador.incrementarExperimentos();
    }

    @Override
    public String getTipo() {
        return "Químico";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Químico | Reactivo: " + tipoReactivo +
                " | Investigador: " + investigador.getNombre();
    }
}