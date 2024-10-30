package controlador;

import datos.ApartamentoDAO;
import java.util.ArrayList;
import modelo.Apartamento;

public class GestionApartamento {

    ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

    public ArrayList<Apartamento> ObtenerApartamento(int id) {
        return apartamentoDAO.MostrarApartamento(id);
    }

    public int ObtenerApartamentos() {
        return apartamentoDAO.CantidadApartamentos();
    }

    public boolean GuardarApartamento(Apartamento a, int idTorre, int idTipoUnidad) {
        return apartamentoDAO.CrearApartamento(a, idTorre, idTipoUnidad);
    }

    public boolean ActualizarApartamento(int id, String numero, double valor, String area) {
        return apartamentoDAO.EditarApartamento(id, numero, valor, area);
    }

    public boolean BorrarApartamento(int id) {
        return apartamentoDAO.EliminarApartamento(id);
    }

    public ArrayList<String> obtenerTipoUnidades() {
        return apartamentoDAO.ObtenerTipoUnidad();
    }

    public int ObtenerIdTipoUnidad(String nombre) {
        return apartamentoDAO.ObtenerIdTipoUnidad(nombre);
    }
}
