package controlador;

import datos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.RolUsuario;

public class ValidarAdmin {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();

    public RolUsuario validarAdmin(String correo, String identificacion) {
        RolUsuario RU = new RolUsuario();
        String sql = "SELECT * FROM administrador WHERE CORREOELECTRONICO = ? AND IDENTIFICACION = ?";


        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, identificacion);
            rs = ps.executeQuery();

            if (rs.next()) {

                RU.setId(rs.getInt("id"));
                RU.setNombre(rs.getString("nombre"));
                RU.setCorreo(rs.getString("correoElectronico")); // Corrección de columna
                RU.setIdentificacion(rs.getString("identificacion"));
                RU.setDireccion(rs.getString("direccion")); // Corrección de columna



            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }  catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return RU ; 
    }
}