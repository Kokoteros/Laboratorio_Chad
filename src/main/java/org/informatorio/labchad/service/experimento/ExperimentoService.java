package org.informatorio.labchad.service.experimento;

import org.informatorio.labchad.domain.Experimento;
import java.util.List;
import java.util.Optional;

public interface ExperimentoService {
    void registrarExperimentoQuimico(String nombre, int duracion, boolean exito,
                                     String tipoReactivo, String nombreInvestigador);

    void registrarExperimentoFisico(String nombre, int duracion, boolean exito,
                                    String instrumento, List<String> nombresInvestigadores);

    void mostrarExperimentos();

    void mostrarEstadisticasBasicas();

    void mostrarExperimentoMasLargo();

    void generarReporte(); // promedio duracion + porcentaje exito

    void mostrarInvestigadorDestacado();
}