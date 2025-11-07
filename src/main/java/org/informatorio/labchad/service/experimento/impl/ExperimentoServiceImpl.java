package org.informatorio.labchad.service.experimento.impl;

import org.informatorio.labchad.domain.*;
import org.informatorio.labchad.service.experimento.ExperimentoService;
import org.informatorio.labchad.repository.experimento.ExperimentoRepository;
import org.informatorio.labchad.repository.investigador.InvestigadorRepository;
import java.util.ArrayList;
import java.util.*;

public class ExperimentoServiceImpl implements ExperimentoService {

    private ExperimentoRepository experimentoRepository;
    private InvestigadorRepository investigadorRepository;

    public ExperimentoServiceImpl(ExperimentoRepository experimentoRepository,
                                  InvestigadorRepository investigadorRepository) {
        this.experimentoRepository = experimentoRepository;
        this.investigadorRepository = investigadorRepository;
    }

    @Override
    public void registrarExperimentoQuimico(String nombre, int duracion, boolean exito,
                                            String tipoReactivo, String nombreInvestigador) {

        Investigador investigador = investigadorRepository.buscarPorNombre(nombreInvestigador);

        if (investigador == null) {
            System.out.println("El investigador no existe. Regístrelo primero.");
            return;
        }

        ExperimentoQuimico exp = new ExperimentoQuimico(nombre, duracion, exito, tipoReactivo, investigador);
        experimentoRepository.guardar(exp);
        System.out.println("Experimento químico registrado correctamente.");
    }

    @Override
    public void registrarExperimentoFisico(String nombre, int duracion, boolean exito,
                                           String instrumento, List<String> nombresInvestigadores) {

        List<Investigador> investigadores = new ArrayList<>();

        for (String nombreInv : nombresInvestigadores) {
            Investigador inv = investigadorRepository.buscarPorNombre(nombreInv.trim());
            if (inv != null) investigadores.add(inv);
        }

        if (investigadores.isEmpty()) {
            System.out.println("No se encontraron investigadores válidos. Regístrelos primero.");
            return;
        }

        ExperimentoFisico exp = new ExperimentoFisico(nombre, duracion, exito, instrumento, investigadores);
        experimentoRepository.guardar(exp);
        System.out.println("Experimento físico registrado correctamente.");
    }

    @Override
    public void mostrarExperimentos() {
        List<Experimento> experimentos = experimentoRepository.listarTodos();

        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }

        for (Experimento e : experimentos) {
            System.out.println(e);
        }
    }

    @Override
    public void mostrarEstadisticasBasicas() {
        List<Experimento> experimentos = experimentoRepository.listarTodos();

        int exitosos = 0;
        int fallidos = 0;

        for (Experimento e : experimentos) {
            if (e.isExito()) exitosos++;
            else fallidos++;
        }

        System.out.println("Total de experimentos exitosos: " + exitosos);
        System.out.println("Total de experimentos fallidos: " + fallidos);
    }

    @Override
    public void mostrarExperimentoMasLargo() {
        List<Experimento> experimentos = experimentoRepository.listarTodos();

        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }

        Experimento masLargo = experimentos.get(0);
        for (Experimento e : experimentos) {
            if (e.getDuracion() > masLargo.getDuracion()) {
                masLargo = e;
            }
        }

        System.out.println("Experimento de mayor duración:");
        System.out.println(masLargo);
    }

    @Override
    public void generarReporte() {
        List<Experimento> experimentos = experimentoRepository.listarTodos();

        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }

        int total = experimentos.size();
        int exitos = 0;
        int sumaDuracion = 0;

        for (Experimento e : experimentos) {
            sumaDuracion += e.getDuracion();
            if (e.isExito()) exitos++;
        }

        double promedio = (double) sumaDuracion / total;
        double porcentajeExito = (double) exitos * 100 / total;

        System.out.printf("Promedio de duración: %.2f minutos%n", promedio);
        System.out.printf("Porcentaje de éxito: %.2f%%%n", porcentajeExito);
    }

    @Override
    public void mostrarInvestigadorDestacado() {
        List<Investigador> investigadores = investigadorRepository.listarTodos();

        if (investigadores.isEmpty()) {
            System.out.println("No hay investigadores registrados.");
            return;
        }

        Investigador top = investigadores.get(0);
        for (Investigador i : investigadores) {
            if (i.getCantidadExperimentos() > top.getCantidadExperimentos()) {
                top = i;
            }
        }

        System.out.println("Investigador con más experimentos:");
        System.out.println(top);
    }
}