package org.informatorio.labchad.service.investigador;


import org.informatorio.labchad.domain.Investigador;
import java.util.List;

public interface InvestigadorService {
    void registrarInvestigador(String nombre, int edad);
    Investigador buscarPorNombre(String nombre);
    List<Investigador> listarInvestigadores();
}