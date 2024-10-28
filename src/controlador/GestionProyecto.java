package controlador;

import datos.ProyectoDAO;
import java.util.ArrayList;
import modelo.Proyecto;

public class GestionProyecto {
    ProyectoDAO proyectoDAO = new ProyectoDAO();
    
    public ArrayList<Proyecto> obtenerProyectosAdmin(int id){
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
    
    public int obtenerProyectoUnico(String nombre){
        return proyectoDAO.ObtenerIdProyectoUnico(nombre);
    }
}
