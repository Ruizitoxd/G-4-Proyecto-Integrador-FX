package controlador;

import datos.PagoDAO;
import java.util.ArrayList;
import java.sql.Date;
import modelo.Pago;

public class GestionPago {

    PagoDAO pagoDAO = new PagoDAO();

    public ArrayList<Pago> ObtenerCuotas(int id_venta) {
        return pagoDAO.MostrarCuotas(id_venta);
    }

    public boolean GuardarPago(int cuotas, String DiaAPagar, double valor, int idAsesor, int idVenta, int idCliente) {
        return pagoDAO.GenerarCuotas(cuotas, DiaAPagar, valor, idAsesor, idVenta, idCliente);
    }
    
    public boolean EditarPago(int id, Date fechapago){
        return pagoDAO.RegistrarPago(id, fechapago);
    }
}
