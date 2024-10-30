package controlador;

import datos.ProyectoDAO;
import java.util.ArrayList;
import modelo.Proyecto;

public class GestionProyecto {

    ProyectoDAO proyectoDAO = new ProyectoDAO();

    public ArrayList<Proyecto> ObtenerProyectosAdmin(int id) {
        return proyectoDAO.MostrarProyectos(id);
    }

    public int ObtenerTotalProyecto() {
        return proyectoDAO.CantidadProyectos();
    }

    public boolean GuardarProyecto(Proyecto pr, int id) {
        return proyectoDAO.CrearProyecto(pr, id);
    }

    public boolean ActualizarProyecto(int id, String nombre) {
        return proyectoDAO.EditarProyecto(id, nombre);
    }

    public boolean BorrarProyecto(int id) {
        return proyectoDAO.EliminarProyecto(id);
    }

    public int ObtenerIdProyectoUnico(String nombre) {
        return proyectoDAO.ObtenerIdProyectoUnico(nombre);
    }
}
