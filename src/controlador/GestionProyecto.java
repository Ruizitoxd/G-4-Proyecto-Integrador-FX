package controlador;

import datos.ProyectoDAO;
import java.util.List;
import modelo.Proyecto;

public class GestionProyecto {
    ProyectoDAO proyectoDAO = new ProyectoDAO();
    
    public List<Proyecto> obtenerProyectosAdimin(int id){
        return proyectoDAO.mostrarProyectos(id);
    }
    
    public int obtenerTotalProyecto(){
        return proyectoDAO.cantidadProyectos();
    }
    
    public boolean guardarProyecto(Proyecto pr,int id){
        return proyectoDAO.crearProyecto(pr, id);
    }
    
    public boolean actualizarProyecto(int id,String nombre){
        return proyectoDAO.editarProyecto(id, nombre);
    }
    
    public boolean borrarProyecto(int id){
        return proyectoDAO.EliminarProyecto(id);
    }
}
