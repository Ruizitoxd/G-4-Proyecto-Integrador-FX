package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controlador.RolUsuario;

public class ValidarAdmin {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();

    public boolean validarAdmin(String correo, String identificacion) {
        String sql = "SELECT * FROM admin WHERE correoElectronico = ? AND identificacion = ?";
        boolean esAdmin = false; // Iniciamos asumiendo que no es admin

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, identificacion);
            rs = ps.executeQuery();

            if (rs.next()) {
                esAdmin = true; 

                
                RolUsuario RU = new RolUsuario();
                RU.setId(rs.getInt("id"));
                RU.setNombre(rs.getString("nombre"));
                RU.setCorreo(rs.getString("correoElectronico")); // Corrección de columna
                RU.setIdentificacion(rs.getString("identificacion"));
                RU.setDirecion(rs.getString("direccion")); // Corrección de columna
                RU.setTelefono(rs.getString("telefono"));

             
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }  catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        

        return esAdmin; 
    }
}

