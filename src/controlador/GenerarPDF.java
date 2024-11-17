/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.itextpdf.text.DocumentException;
import datos.ApartamentoDAO;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import modelo.Apartamento;
import modelo.Reporte;

/**
 *
 * @author josem
 */
public class GenerarPDF {

    
    public boolean geneararpdf(ArrayList<Apartamento> t) {
        Reporte re = new Reporte();  
        return re.generarPdf(t);     
    }

}
