package org.informatorio.labchad.repository.investigador;

import org.informatorio.labchad.domain.Investigador;
import java.util.List;
public interface InvestigadorRepository {
    void guardar(Investigador investigador);
    Investigador buscarPorNombre(String nombre);
    List<Investigador> listarTodos();
}
