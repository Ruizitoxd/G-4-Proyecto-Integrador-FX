
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;

public class ClienteDAO {
        Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;
    
    public boolean CrearCliente(Cliente cl){
    String sql="insert into cliente (id, identificacion, nombre, apellido, SISBEN, subsidio,direccion,correoElectronico) "+
            "set values(SEQ_IDCLIENTE, ?,?,?,?,?,?,?)";
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
    
    
}
