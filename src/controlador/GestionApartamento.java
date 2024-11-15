package controlador;

import datos.ApartamentoDAO;
import java.util.ArrayList;
import modelo.Apartamento;
import modelo.DatosGrafica;

public class GestionApartamento {

    ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

    public ArrayList<Apartamento> ObtenerApartamento(int id) {
        return apartamentoDAO.MostrarApartamentos(id);
    }

    public int ObtenerApartamentos() {
        return apartamentoDAO.CantidadApartamentos();
    }

    public DatosGrafica DatosGrafica() {
        return apartamentoDAO.DatosGraficaMenu();
    }

    public boolean GuardarApartamento(Apartamento a, int idTorre, int idTipoUnidad) {
        return apartamentoDAO.CrearApartamento(a, idTorre, idTipoUnidad);
    }

    public boolean ActualizarApartamento(int id, String numero, double valor, String area, int idTipoUnidad) {
        return apartamentoDAO.EditarApartamento(id, numero, valor, area, idTipoUnidad);
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

    public ArrayList<Apartamento> ObtenerApartamentosProyecto(int idProy) {
        return apartamentoDAO.ObtenerApartamentosProyecto(idProy);
    }

    public ArrayList<Apartamento> ObtenerApartamentosNoVendidos() {
        return apartamentoDAO.ObtenerApartamentosNoVendidos();
    }
    
    public ArrayList<Apartamento> ObtenerApartamentosVendidos() {
        return apartamentoDAO.ObtenerApartamentosVendidos();
    }
}
