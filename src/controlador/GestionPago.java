package controlador;

import datos.PagoDAO;
import java.util.ArrayList;
import modelo.Pago;

public class GestionPago {
    PagoDAO pagodao = new PagoDAO();

    public ArrayList<Pago> ObtenerCuotas(int id_venta){
        return pagodao.MostrarCuotas(id_venta);
    }

    public boolean GuardarPago(Pago pa, int idAsesor,int idVenta, int Cliente){
        return pagodao.CrearPago(pa, idAsesor, idVenta, Cliente);
    }
}