
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;

public class ClienteDAO {
        Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;
    
    public boolean CrearCliente(Cliente cl){
    String sql="insert into cliente (id, identificacion, nombre, apellido, SISBEN, subsidio,direccion,correoElectronico) "+
            "set values(SEQ_IDCLIENTE.NEXTVAL, ?,?,?,?,?,?,?)";
    try{
        conexion= new ConexionBD();
        con = conexion.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, cl.getIdentificacion());
        ps.setString(2, cl.getNombre());
        ps.setString(3, cl.getApellido());
        ps.setString(4, cl.getSISBEN());
        ps.setString(5, cl.getSubsidio());
        ps.setString(6, cl.getDireccion());
        ps.setString(7, cl.getCorreo());
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
    
    public int BuscarCliente(String cedula){
        String sql="select id "+ 
                    "from cliente "+   
                    "where cudula = ?";
        int id_cliente=0;
        try{
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if(rs.next()){
                id_cliente = rs.getInt("id");
                return id_cliente;
            }
        }catch (SQLException ex) {
            System.out.println("Error al añadir el cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return id_cliente ;
    }
    
    public boolean numeroDeTelefonoDelCliente(int id_cliente, String numero) {
        String sql = "INSERT INTO telefono_cliente (id, telefono, id_cliente) " +
                     "VALUES (SEQ_IDTELEFO_CLIENTE.NEXTVAL, ?, ?)";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, numero); 
            ps.setInt(2, id_cliente); 


            int resultado = ps.executeUpdate();


            return resultado > 0;
        } catch (SQLException ex) {
            System.out.println("Error al añadir el teléfono del cliente: " + ex.getMessage());
        } finally {
            // Cerrar la conexión
            conexion.closeConnection();
        }
        return false;
    }
    
    
    
    public boolean ConsultarSISBEN(int id_cliente) {
       String sql = "SELECT SISBEN " +
                    "FROM Cliente " +
                    "WHERE id = ?";
       try {
           conexion = new ConexionBD();
           con = conexion.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_cliente);
           rs = ps.executeQuery();
           if (rs.next()) {
               if (rs.getString("SISBEN").equals("NO")) {
                   return false;
               } else {
                   return true;
               }
           }
       } catch (SQLException ex) {
           System.out.println("Error al consultar SISBEN del cliente: " + ex.getMessage());
       } finally {
           conexion.closeConnection();
       }
       return false;
 
   }
    
    public Cliente buscarDatosCliente(int idIdentificacion) {
        Cliente cliente = null;
        String sql = "SELECT c.nombre AS nombre, c.apellido AS apellido, tc.numero AS numero " +
                     "FROM cliente c " +
                     "JOIN telefono_cliente tc ON c.id = tc.id_cliente " +
                     "WHERE c.identificacion = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idIdentificacion);
            rs = ps.executeQuery();

            if (rs.next()) { 
                cliente = new Cliente();
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setNumeroTelef(rs.getInt("numero"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar los datos del cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return cliente; 
    }


    
    public double consultarSubsidio(int idCliente) {
        String sql = "SELECT subsidio " + 
                     "FROM Cliente " +
                     "WHERE id = ?";
        double subsidio = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                subsidio = rs.getInt("subcidio");
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar subsidio del cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return subsidio;
    }
    
    
    public int BuscarLaIDdelCliente(){
        String sql="SELECT id " +
"                   FROM venta " +
"                   WHERE id = (SELECT MAX(id) FROM ventas)";
        int id_venta =0;
        try{
            conexion = new  ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if(rs.next()){
                id_venta = rs.getInt("id");
                return id_venta;
            }
        }catch (SQLException ex) {
            System.out.println("Error al buscar la venta: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return id_venta ;
    }


}
