package org.informatorio.labchad.service.investigador.impl;

import org.informatorio.labchad.domain.Investigador;
import org.informatorio.labchad.service.investigador.InvestigadorService;
import org.informatorio.labchad.repository.investigador.InvestigadorRepository;
import java.util.List;

public class InvestigadorServiceImpl implements InvestigadorService {

    private InvestigadorRepository repository;

    public InvestigadorServiceImpl(InvestigadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarInvestigador(String nombre, int edad) {
        if (repository.buscarPorNombre(nombre) != null) {
            System.out.println("Ya existe un investigador con ese nombre.");
            return;
        }
        repository.guardar(new Investigador(nombre, edad));
        System.out.println("Investigador registrado correctamente.");
    }

    @Override
    public Investigador buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    @Override
    public List<Investigador> listarInvestigadores() {
        return repository.listarTodos();
    }
}