package org.informatorio.labchad;

import org.informatorio.labchad.repository.experimento.impl.ExperimentoRepositoryImpl;
import org.informatorio.labchad.repository.investigador.impl.InvestigadorRepositoryImpl;
import org.informatorio.labchad.service.experimento.ExperimentoService;
import org.informatorio.labchad.service.experimento.impl.ExperimentoServiceImpl;
import org.informatorio.labchad.service.investigador.InvestigadorService;
import org.informatorio.labchad.service.investigador.impl.InvestigadorServiceImpl;
import org.informatorio.labchad.service.menu.MenuService;
import org.informatorio.labchad.service.menu.impl.MenuServiceImpl;
import org.informatorio.labchad.service.archivos.ExportInvestigadoresService;
import org.informatorio.labchad.service.archivos.impl.ExportInvestigadoresServiceImpl;

public class App {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE GESTIÓN DE EXPERIMENTOS ===");

        // 1 Crear repositorios
        var investigadorRepo = new InvestigadorRepositoryImpl();
        var experimentoRepo = new ExperimentoRepositoryImpl();

        // 2 Crear servicios
        InvestigadorService investigadorService = new InvestigadorServiceImpl(investigadorRepo);
        ExperimentoService experimentoService = new ExperimentoServiceImpl(experimentoRepo, investigadorRepo);
        ExportInvestigadoresService exportService = new ExportInvestigadoresServiceImpl();

        // 3 Crear menú interactivo
        MenuService menuService = new MenuServiceImpl(investigadorService, experimentoService, exportService);

        // 4 Iniciar la app
        menuService.iniciarMenu();

        System.out.println("=== FIN DEL PROGRAMA ===");
    }
}