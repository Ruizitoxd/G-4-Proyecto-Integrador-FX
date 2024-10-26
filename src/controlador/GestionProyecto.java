package controlador;

import datos.ProyectoDAO;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> 7a334b8097fd190c412dbccea12952a9f52f7f5d
import modelo.Proyecto;

public class GestionProyecto {
    ProyectoDAO proyectoDAO = new ProyectoDAO();
    
<<<<<<< HEAD
    public List<Proyecto> obtenerProyectosAdimin(int id){
=======
    public ArrayList<Proyecto> obtenerProyectosAdmin(String id){
>>>>>>> 7a334b8097fd190c412dbccea12952a9f52f7f5d
        return proyectoDAO.MostrarProyectos(id);
    }
    
    public int obtenerTotalProyecto(){
        return proyectoDAO.CantidadProyectos();
    }
    
    public boolean guardarProyecto(Proyecto pr,int id){
        return proyectoDAO.CrearProyecto(pr, id);
    }
    
    public boolean actualizarProyecto(int id,String nombre){
        return proyectoDAO.EditarProyecto(id, nombre);
    }
    
    public boolean borrarProyecto(int id){
        return proyectoDAO.EliminarProyecto(id);
    }
}
