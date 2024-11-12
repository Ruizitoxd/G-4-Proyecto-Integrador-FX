
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Torre;
import modelo.Venta;

public class VentaDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;
    
    public ArrayList<Venta> MostrarVenta(int id_asesor) {
        ArrayList<Venta> ventas = new ArrayList();
        String sql= "select v.id as idVenta, v.valortotal as valor , v.numcuotas as numCuotas, v.intereses as interes, v.id_apart as idApartament"+
                    " from venta v "+
                    "join asesor a on v.id_asesor = a.id"+
                    " where id_asesor = ?";
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_asesor);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta venta = new Venta();
                venta.setId(rs.getInt("idVenta"));
                venta.setValor(rs.getDouble("valor"));
                venta.setInteres(rs.getDouble("interes"));
                venta.setNumCuotas(rs.getInt("numCuotas"));

                ventas.add(venta); 
            }        
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return ventas;
    }
    
    public int CantidadVentas(){
        String sql = "select count(*) as cantidadVentas "+
                    "from venta";
        int TotalVentas =0;
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if(rs.next()){
                TotalVentas= rs.getInt("cantidadVentas");
            }
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return TotalVentas;
    }
    
    public boolean crearVenta( Venta vt, int idAsesor, int idAse){
        String sql = "insert into venta(id, valortotal, numcuotas, intereses, id_apart, id_ases) "+
                    "values (SEQ_IDVENTA, ?,?,?,?,?)";
        
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, vt.getValor());          // valor total de la venta
            ps.setInt(2, vt.getNumCuotas());         // número de cuotas
            ps.setDouble(3, vt.getInteres());        // interés
            ps.setInt(4, idAse);                     // id del apartamento
            ps.setInt(5, idAsesor);  
            
            int resultado = ps.executeUpdate();
            if(resultado> 0){
                return true;
            } 
        }catch (SQLException ex) {
            System.out.println("Error :" + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }
    
    public boolean EditarVenta(int idVenta, double valor, int cuotas, double interes) {
        String sql = "update venta "+
                    "set valortotal= ? , numcuotas = ? , intereses = ? "+
                    " where id = ?";
        
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setInt(2, cuotas);
            ps.setDouble(3, interes);
            ps.setInt(4, idVenta);
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar Torre :" + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }
    
    public boolean EliminarVenta(int idVenta){
        String sql = "delete from venta "+
                    "where id = ?";
        
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al borrar torre" + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

}
