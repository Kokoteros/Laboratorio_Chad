package org.informatorio.labchad.repository.investigador.impl;

import org.informatorio.labchad.domain.Investigador;
import org.informatorio.labchad.repository.investigador.InvestigadorRepository;
import java.util.ArrayList;
import java.util.List;

public class InvestigadorRepositoryImpl implements InvestigadorRepository {

    private List<Investigador> investigadores = new ArrayList<>();

    @Override
    public void guardar(Investigador investigador) {
        investigadores.add(investigador);
    }

    @Override
    public Investigador buscarPorNombre(String nombre) {
        for (Investigador i : investigadores) {
            if (i.getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Investigador> listarTodos() {
        return investigadores;
    }
}
