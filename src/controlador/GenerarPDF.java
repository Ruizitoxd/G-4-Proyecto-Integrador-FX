package controlador;

import java.util.ArrayList;
import modelo.Apartamento;
import modelo.Reporte;

public class GenerarPDF {

    
    public boolean geneararpdf(ArrayList<Apartamento> t) {
        Reporte re = new Reporte();  
        return re.generarPdf(t);     
    }

}
