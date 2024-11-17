
package modelo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;



public class Reporte {
    Document documento;
    FileOutputStream fileOutputStream;

    // fuentes de Titulo y párrafo
    Font fuenteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN,16);
    Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA,12);
    Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
    
    public void crearDocumento() throws FileNotFoundException, DocumentException{

        documento = new Document(PageSize.A4,35,30,50,50);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
        String fechaActual = sdf.format(new Date()); 

        String ruta = System.getProperty("user.home");
        String nombreArchivo = ruta + "/reportedia_" + fechaActual + ".pdf";
        fileOutputStream = new FileOutputStream(nombreArchivo);

        PdfWriter.getInstance(documento,fileOutputStream);
    }

    public void abrirDocumento(){
        documento.open();
    }

    public void agregarTitulo() throws DocumentException {
        PdfPTable tabla = new PdfPTable(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
        String fechaActual = sdf.format(new Date()); 

        PdfPCell celda = new PdfPCell(new Phrase("reprotes vendidos este mes"+ fechaActual,fuenteTitulo));
        celda.setColspan(5);
        celda.setBorderColor(BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
        documento.add(tabla);
    }
    
    
    public void agregarParrafo(String nombre) throws DocumentException {
        Paragraph parrafo = new Paragraph();
        parrafo.add(new Phrase("Chaux\n"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaActual = sdf.format(new Date());
        parrafo.add(new Phrase("Fecha: " + fechaActual + "\n"));
        parrafo.add(new Phrase("Asesor a cargo: " + nombre + "\n"));
        documento.add(parrafo);
    }

    
    public void agregarSaltosDeLinea() throws DocumentException{
        Paragraph saltosdelinea = new Paragraph();
        saltosdelinea.add(new Phrase(Chunk.NEWLINE));
        saltosdelinea.add(new Phrase(Chunk.NEWLINE));
        documento.add(saltosdelinea);
    }
    public void agregarTablaApartamentos(List<Apartamento> apartamentos) throws DocumentException {
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell(new PdfPCell(new Phrase("ID", fuenteNegrita)));
        tabla.addCell(new PdfPCell(new Phrase("Número", fuenteNegrita)));
        tabla.addCell(new PdfPCell(new Phrase("Valor", fuenteNegrita)));
        tabla.addCell(new PdfPCell(new Phrase("Área", fuenteNegrita)));
        tabla.addCell(new PdfPCell(new Phrase("Matrícula", fuenteNegrita)));

    
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
    

        
    public boolean generarPdf(ArrayList<Apartamento> apartamentos) {
        Reporte reportePdf = new Reporte();
        try {
            reportePdf.crearDocumento();
            reportePdf.abrirDocumento();
            reportePdf.agregarTitulo();
            reportePdf.agregarSaltosDeLinea();
            reportePdf.agregarSaltosDeLinea();

            reportePdf.agregarTablaApartamentos(apartamentos);

            reportePdf.cerrarDocumento();
            System.out.println("PDF generado exitosamente.");
            return true; 
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            return false; 
        }
    }


    
    
}
