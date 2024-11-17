
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import modelo.DatosGrafica;
import modelo.Pago;
public class PagoDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;
    
    public boolean CrearPago(Pago pa, int idAsesor,int idVenta, int Cliente){
        String sql = "Insert into pago (id,valor, FechaApagar, FechaPago, FechaVencimiento, id_asesor, id_clinete, id_venta) "
                   + "values(SEQ_IDPAGO,?,?,?,?,?,?,?) ";
        
        try{
            conexion= new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, pa.getValor());            
            ps.setDate(2, pa.getFechaApagar());
            ps.setDate(3, pa.getFechaPago());
            ps.setDate(4, pa.getFechaVenciomiento());
            ps.setInt(5, idAsesor);
            ps.setString(6, pa.getIdCliente());
            ps.setString(7, pa.getIdVenta());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        }catch (SQLException ex) {
            System.out.println("Error al añadir el apartamento: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }
    
    public boolean RegistrarPago(int id, Date fechapago){
        String sql ="Update pago "+
                " set fechaPago = ?"+
                " where id = ?";
        
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(2, id);            
            ps.setDate(1, fechapago);
                        int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        }catch (SQLException ex) {
            System.out.println("Error al añadir el pago: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }
    
    
    public ArrayList<Pago> MostrarCuotas(int id_venta) {
        ArrayList<Pago> cuotas = new ArrayList<>();
        String sql = "SELECT valor, FechaPago, FechaVencimiento FROM pago WHERE id_venta = ?";
        LocalDate fechaActual = LocalDate.now();

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_venta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pago cuo = new Pago();
                cuo.setValor(rs.getDouble("valor"));
                
                Date fechaPagoSql = rs.getDate("FechaPago");
                Date fechaVencimientoSql = rs.getDate("FechaVencimiento");
                
                cuo.setFechaPago(fechaPagoSql);
                cuo.setFechaVencimiento(fechaVencimientoSql);

                if (fechaPagoSql != null) {
                    cuo.setEstado("Pagada");
                } else {
                    LocalDate fechaVencimiento = fechaVencimientoSql.toLocalDate();
                    if (fechaVencimiento.isBefore(fechaActual)) {
                        cuo.setEstado("Vencido");
                    } else {
                        cuo.setEstado("Pendiente");
                    }
                }

                cuotas.add(cuo);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return cuotas;
    }
    
    public boolean GenenrarCuotas(Pago pa,int cuotas, int idAsesor,int idVenta, int idCliente){
        String Procedimiento ="{CALL generar_fechas_cuotas(?,?,?,?,?,?)}";
        
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareCall(Procedimiento);
            ps.setInt(1, cuotas);
            ps.setDate(2, pa.getFechaApagar());
            ps.setDouble(3, pa.getValor());
            ps.setInt(4, idAsesor);
            ps.setInt(5, idVenta);
            ps.setInt(6, idCliente);
            
            ps.execute();
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        }catch (SQLException ex) {
            System.out.println("Error al añadir el apartamento: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }
    
    
    public DatosGrafica DatosGraficaDashboard(){
        String sql="select fechaPago "+
                    "from pago";
        int pagadas = 0;
        int noPagadas = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Date fechapago = rs.getDate("fechaPago");
                if ( fechapago != null) {
                    pagadas += 1;
                } else {
                     noPagadas += 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }

        return new DatosGrafica(pagadas, noPagadas);
    }
}
