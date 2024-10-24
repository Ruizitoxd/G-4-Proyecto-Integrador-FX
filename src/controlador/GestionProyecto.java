package controlador;

import datos.ProyectoDAO;
import java.util.List;
import modelo.Proyecto;

public class GestionProyecto {
    ProyectoDAO proyectoDAO = new ProyectoDAO();
    
    public List<Proyecto> obtenerProyectosAdimin(String id){
        return proyectoDAO.mostrarProyectos(id);
    }
    
    public int obtenerTotalProyecto(){
        return proyectoDAO.cantidadProyectos();
    }
    
    public boolean guardarProyecto(Proyecto pr,String id){
        return proyectoDAO.crearProyecto(pr, id);
    }
    
    public boolean actualizarProyecto(String id,String nombre){
        return proyectoDAO.editarProyecto(id, nombre);
    }
    
    public boolean borrarProyecto(String id){
        return proyectoDAO.EliminarProyecto(id);
    }
}
