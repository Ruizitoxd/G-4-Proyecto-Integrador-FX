
package controlador;

import datos.ApartamentoDAO;
import java.util.List;
import modelo.Apartamento;

public class GestionApartamento {
    ApartamentoDAO apartamentoDAO= new ApartamentoDAO();
    
    public List<Apartamento> obtenerApartamento(int id){
        return apartamentoDAO.MostrarApartamento(id);
    }
    
    public int obtenerApartamentos(){
        return apartamentoDAO.CantidadApartamentos();
    }
    
    public boolean guardarApartamento(Apartamento a ,int idTorre, int idTipoUnidad){
        return apartamentoDAO.CrearApartamento(a, idTorre, idTipoUnidad);
    }
    
    public boolean ActualizarApartamento(int id , String numero, double valor, String area){
        return apartamentoDAO.EditarApartamento(id, numero, valor, area);
    }
    
    public boolean borrarApartamento(int id){
        return apartamentoDAO.EliminarApartamento(id);
    }
}
