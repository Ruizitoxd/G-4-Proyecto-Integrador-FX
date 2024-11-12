/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import datos.VentaDAO;
import java.util.ArrayList;
import modelo.Venta;

public class GestionVenta {
    VentaDAO ventadao = new VentaDAO();
    
    public ArrayList<Venta> obtenerVenta(int id_asesor){
        return ventadao.MostrarVenta(id_asesor);
    }
    
    public int TotalVentas(){
        return ventadao.CantidadVentas();
    }
    
    public boolean GuardarVentas(Venta vt, int idAsesor, int idAse){
        return ventadao.crearVenta(vt, idAsesor, idAse);
    }
    
    public boolean ActualizarVenta(int idVenta, double valor, int cuotas, double interes){
        return ventadao.EditarVenta(idVenta, valor, cuotas, interes);
    }
    
    public boolean BorrarVenta(int idVenta){
        return ventadao.EliminarVenta(idVenta);
    }
}
