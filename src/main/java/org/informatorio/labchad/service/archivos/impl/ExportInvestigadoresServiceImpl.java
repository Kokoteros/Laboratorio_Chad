package org.informatorio.labchad.service.archivos.impl;

import org.informatorio.labchad.domain.Investigador;
import org.informatorio.labchad.service.archivos.ExportInvestigadoresService;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportInvestigadoresServiceImpl implements ExportInvestigadoresService {

    private final String UBICACION_ARCHIVO = "\\src\\main\\java\\org\\informatorio\\labchad\\resource\\";
    CSVWriter csvWriter;
    @Override
    public void exportInvestigadoresCSV(List<Investigador> investigadores) {
        String ruta = System.getProperty("user.dir").concat(UBICACION_ARCHIVO).concat("lista-investigadores.csv");
        try{
            this.csvWriter = new CSVWriter(new FileWriter(ruta));

            String[] encabezado = {"NOMBRE", "EDAD", "CANTIDAD DE EXPERIMENTOS"};
            this.csvWriter.writeNext( encabezado );

            for (Investigador inv : investigadores) {
                String[] datos = {
                        inv.getNombre(),
                        Integer.toString(inv.getEdad()),
                        Integer.toString(inv.getCantidadExperimentos())
                };
                this.csvWriter.writeNext(datos);
            }

            System.out.println("Exportación exitosa. Guardado en: ");
            System.out.println(ruta);
            this.cerrarWriter();

        }catch (IOException ioException){
            System.out.println(
                    "Ocurrió un error: " + ioException.getMessage().concat( "Ubicación del archivo : " + ruta )
            );
        }
    }
    private void cerrarWriter(){
        if ( this.csvWriter != null ){
            try {
                this.csvWriter.close();
            }catch (IOException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }
}