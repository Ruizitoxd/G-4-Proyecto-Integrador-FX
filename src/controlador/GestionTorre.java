package controlador;

import datos.TorreDAO;
import java.util.ArrayList;
import modelo.Torre;

public class GestionTorre {

    TorreDAO torreDAO = new TorreDAO();

    public ArrayList<Torre> ObtenerTorre(int id) {
        return torreDAO.MostrarTorre(id);
    }

    public int ObtenerTotalProyecto() {
        return torreDAO.CantidadTorre();
    }

    public boolean GuardarTorre(Torre tr, int idProy) {
        return torreDAO.CrearTorre(tr, idProy);
    }

    public boolean ActualizarTorre(int idTorre, String nombre) {
        return torreDAO.EditarTorre(idTorre, nombre);
    }

    public boolean BorrarTorre(int id) {
        return torreDAO.EliminarTorre(id);
    }

    public int ObtenerIdTorreUnica(String nombre) {
        return torreDAO.ObtenerIdTorreUnica(nombre);
    }
}
