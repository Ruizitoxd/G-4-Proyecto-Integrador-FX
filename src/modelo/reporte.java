
package modelo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import datos.ApartamentoDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;



public class reporte {
    Document documento;
    FileOutputStream fileOutputStream;

    // fuentes de Titulo y párrafo
    Font fuenteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN,16);
    Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA,12);
    
    public void crearDocumento() throws FileNotFoundException, DocumentException {

        // creación del documento con sus márgenes
        documento = new Document(PageSize.A4,35,30,50,50);

        // archivo pdf que vamos a generar
        String ruta = System.getProperty("user.home");
        fileOutputStream = new FileOutputStream(ruta + "/reportedia.pdf");

        // obtener la instancia del PdfWriter
        PdfWriter.getInstance(documento,fileOutputStream);
    }

    public void abrirDocumento(){
        documento.open();
    }

    public void agregarTitulo() throws DocumentException {
        PdfPTable tabla = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(new Phrase("titulo reporte",fuenteTitulo));
        celda.setColspan(5);
        celda.setBorderColor(BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
        documento.add(tabla);
    }
    
    public void agregarSaltosDeLinea() throws DocumentException{
        Paragraph saltosdelinea = new Paragraph();
        saltosdelinea.add(new Phrase(Chunk.NEWLINE));
        saltosdelinea.add(new Phrase(Chunk.NEWLINE));
        documento.add(saltosdelinea);
    }
    public void agregarTablaApartamentos(List<Apartamento> apartamentos) throws DocumentException {
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("ID");
        tabla.addCell("Número");
        tabla.addCell("Valor");
        tabla.addCell("Área");
        tabla.addCell("Matrícula");

    
        for (Apartamento apa : apartamentos) {
            
            tabla.addCell(String.valueOf(apa.getId()));
            tabla.addCell(apa.getNumero());
            tabla.addCell(String.valueOf(apa.getValor()));
            tabla.addCell(apa.getArea()+" M²");
            tabla.addCell(apa.getMatricula());
        }

        documento.add(tabla);
  
    }
    
   public void cerrarDocumento() {
        if (documento != null) {
            documento.close();
        }
    }
    
        public static void main(String[] args) {
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        ArrayList<Apartamento> apartamentos = apartamentoDAO.MostrarApartamentos(4); // Cambia 1 por el ID de torre que necesites

        reporte reportePdf = new reporte();
 
        try {
            reportePdf.crearDocumento();
            reportePdf.abrirDocumento();
            reportePdf.agregarTitulo();
            reportePdf.agregarSaltosDeLinea();
            reportePdf.agregarSaltosDeLinea();

            // Agregar la tabla de apartamentos
            reportePdf.agregarTablaApartamentos(apartamentos);

            reportePdf.cerrarDocumento();
            System.out.println("PDF generado exitosamente.");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
    
    
}
