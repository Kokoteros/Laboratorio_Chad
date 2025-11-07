package org.informatorio.labchad.repository.experimento.impl;

import org.informatorio.labchad.domain.Experimento;
import org.informatorio.labchad.repository.experimento.ExperimentoRepository;
import java.util.ArrayList;
import java.util.List;

public class ExperimentoRepositoryImpl implements ExperimentoRepository {

    private List<Experimento> experimentos = new ArrayList<>();

    @Override
    public void guardar(Experimento experimento) {
        experimentos.add(experimento);
    }

    @Override
    public List<Experimento> listarTodos() {
        return experimentos;
    }
}