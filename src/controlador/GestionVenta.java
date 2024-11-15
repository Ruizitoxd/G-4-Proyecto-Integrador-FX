package controlador;

import datos.VentaDAO;
import java.util.ArrayList;
import modelo.Venta;

public class GestionVenta {

    VentaDAO ventaDAO = new VentaDAO();

    public ArrayList<Venta> ObtenerVentas(int id) {
        return ventaDAO.MostrarVentas(id);
    }

    public int obtenerCantidadVentas() {
        return ventaDAO.CantidadVentas();
    }

    public double obtenerGanancias() {
        return ventaDAO.CantidadGanancias();
    }

    public boolean GuardarVenta(Venta vt, int idAsesor, int idCliente) {
        return ventaDAO.CrearVenta(vt, idAsesor, idCliente);
    }

    public boolean EditarVenta(int idVenta, double valor, int cantCuotas, double interes) {
        return ventaDAO.EditarVenta(idVenta, valor, cantCuotas, interes);
    }

    public boolean EliminarVenta(int idVenta) {
        return ventaDAO.EliminarVenta(idVenta);
    }
}
