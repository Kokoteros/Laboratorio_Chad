package org.informatorio.labchad.service.menu.impl;

import org.informatorio.labchad.domain.Investigador;
import org.informatorio.labchad.domain.ExperimentoFisico;
import org.informatorio.labchad.domain.ExperimentoQuimico;
import org.informatorio.labchad.service.experimento.ExperimentoService;
import org.informatorio.labchad.service.investigador.InvestigadorService;
import org.informatorio.labchad.service.archivos.ExportInvestigadoresService;
import org.informatorio.labchad.service.menu.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    private InvestigadorService investigadorService;
    private ExperimentoService experimentoService;
    private ExportInvestigadoresService exportService;
    private Scanner scanner;

    public MenuServiceImpl(InvestigadorService investigadorService,
                           ExperimentoService experimentoService,
                           ExportInvestigadoresService exportService) {
        this.investigadorService = investigadorService;
        this.experimentoService = experimentoService;
        this.exportService = exportService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void iniciarMenu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarInvestigador();
                case 2 -> registrarExperimento();
                case 3 -> experimentoService.mostrarExperimentos();
                case 4 -> experimentoService.mostrarEstadisticasBasicas();
                case 5 -> experimentoService.mostrarExperimentoMasLargo();
                case 6 -> experimentoService.generarReporte();
                case 7 -> experimentoService.mostrarInvestigadorDestacado();
                case 8 -> exportarInvestigadoresCSV();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("====== LABORATORIO CHAD ======");
        System.out.println("1. Registrar investigador");
        System.out.println("2. Registrar experimento");
        System.out.println("3. Listar experimentos");
        System.out.println("4. Mostrar total de éxitos o fallos");
        System.out.println("5. Experimento de mayor duración");
        System.out.println("6. Generar reporte global");
        System.out.println("7. Mostrar investigador destacado");
        System.out.println("8. Exportar investigadores a CSV");
        System.out.println("0. Salir");
    }

    private void registrarInvestigador() {
        System.out.print("Nombre del investigador: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        int edad = leerEntero("Edad: ");
        if (edad <= 0) {
            System.out.println("La edad debe ser mayor que cero.");
            return;
        }

        investigadorService.registrarInvestigador(nombre, edad);
        System.out.println("Investigador registrado correctamente.");
    }

    private void registrarExperimento() {
        if (investigadorService.listarInvestigadores().isEmpty()) {
            System.out.println("Debe registrar al menos un investigador antes de crear un experimento.");
            return;
        }

        System.out.println("Tipo de experimento: ");
        System.out.println("1. Químico");
        System.out.println("2. Físico");
        int tipo = leerEntero("Seleccione tipo: ");

        System.out.print("Nombre del experimento: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre del experimento no puede estar vacío.");
            return;
        }

        int duracion = leerEntero("Duración (en minutos): ");
        if (duracion <= 0) {
            System.out.println("La duración debe ser mayor que cero.");
            return;
        }

        boolean exito = false;
        while (true) {
            System.out.print("¿Fue exitoso? (si o no): ");
            String resp = scanner.nextLine().trim().toLowerCase();

            if (resp.equals("si")) {
                exito = true;
                break;
            } else if (resp.equals("no")) {
                exito = false;
                break;
            } else {
                System.out.println("Respuesta inválida. Escriba 'si' o 'no'.");
            }
        }

        if (tipo == 1) {
            System.out.print("Tipo de reactivo: ");
            String reactivo = scanner.nextLine().trim();
            if (reactivo.isEmpty()) {
                System.out.println("El tipo de reactivo no puede estar vacío.");
                return;
            }

            System.out.print("Nombre del investigador responsable: ");
            String nombreInvestigador = scanner.nextLine().trim();
            if (nombreInvestigador.isEmpty()) {
                System.out.println("El nombre del investigador no puede estar vacío.");
                return;
            }

            experimentoService.registrarExperimentoQuimico(
                    nombre, duracion, exito, reactivo, nombreInvestigador);

        } else if (tipo == 2) {
            System.out.print("Instrumento utilizado: ");
            String instrumento = scanner.nextLine().trim();
            if (instrumento.isEmpty()) {
                System.out.println("El instrumento no puede estar vacío.");
                return;
            }

            System.out.print("Ingrese nombres de investigadores (separados por coma): ");
            String entrada = scanner.nextLine();
            List<String> nombres = new ArrayList<>();

            for (String n : entrada.split(",")) {
                if (!n.trim().isEmpty()) {
                    nombres.add(n.trim());
                }
            }

            if (entrada.trim().isEmpty()) {
                System.out.println("Debe indicar al menos un investigador.");
                return;
            }

            experimentoService.registrarExperimentoFisico(
                    nombre, duracion, exito, instrumento, nombres);

        } else {
            System.out.println("Tipo inválido.Debe ser 1 o 2.");
        }
    }

    private void exportarInvestigadoresCSV() {
        List<Investigador> investigadores = investigadorService.listarInvestigadores();
        if (investigadores.isEmpty()) {
            System.out.println("No hay investigadores registrados para exportar.");
            return;
        }

        exportService.exportInvestigadoresCSV(investigadores);
        System.out.println("Archivo CSV exportado correctamente.");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }
}