package controlador;

import datos.TorreDAO;
import java.util.List;
import modelo.Torre;

public class GestionTorre {
    TorreDAO torreDAO = new TorreDAO();
    
    public List<Torre> obtenerTorre(int id){
        return torreDAO.MostrarTorre(id);
    }
    
    public int obtenerTotalProyecto(){
        return torreDAO.CantidadTorre();
    }
    
    public boolean guardarTorre(Torre tr,int idProy){
        return torreDAO.CrearTorre(tr, idProy);
    }
    
    public boolean actualizarTorre(int idTorre, String nombre){
        return torreDAO.EditarTorre(idTorre,nombre);
    }
    
    public boolean borrarTorre(int id){
        return torreDAO.EliminarTorre(id);
    }
}