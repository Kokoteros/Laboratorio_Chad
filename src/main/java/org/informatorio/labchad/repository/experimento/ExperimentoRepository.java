package org.informatorio.labchad.repository.experimento;

import org.informatorio.labchad.domain.Experimento;
import java.util.List;

public interface ExperimentoRepository {
    void guardar(Experimento experimento);
    List<Experimento> listarTodos();
}