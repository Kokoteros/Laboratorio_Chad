package org.informatorio.labchad.service.archivos;

import org.informatorio.labchad.domain.Investigador;
import java.util.List;
public interface ExportInvestigadoresService {
    void exportInvestigadoresCSV(List<Investigador> investigadores);
}
