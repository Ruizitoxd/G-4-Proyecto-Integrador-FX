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

    public int obtenerIdUltimaVenta() {
        return ventaDAO.ConsultarIdUltimaVenta();
    }

    public boolean GuardarVenta(Venta vt, int idApartamento, int idAsesor, int idCliente, boolean SISBEN) {
        if (SISBEN) {
            vt.setValor(vt.getValor() * 0.90); //Aplicar 10% de descuento si tiene SISBEN
        }

        return ventaDAO.CrearVenta(vt, idApartamento, idAsesor, idCliente);
    }

    public boolean EditarVenta(int idVenta, double valor, int cantCuotas, double interes) {
        return ventaDAO.EditarVenta(idVenta, valor, cantCuotas, interes);
    }

    public boolean EliminarVenta(int idVenta) {
        return ventaDAO.EliminarVenta(idVenta);
    }
}
