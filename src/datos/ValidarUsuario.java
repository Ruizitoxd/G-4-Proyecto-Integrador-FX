package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.RolUsuario;

public class ValidarUsuario {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();

    public RolUsuario validarAdminOAsesor(String correo, String identificacion) {
        RolUsuario RU = new RolUsuario();
        String sqlAdmin = "SELECT ID, IDENTIFICACION, NOMBRE, DIRECCION, CORREOELECTRONICO FROM ADMINISTRADOR WHERE CORREOELECTRONICO = ? AND IDENTIFICACION = ?";
        String sqlAsesor = "SELECT ID, IDENTIFICACION, NOMBRE, DIRECCION, CORREOELECTRONICO FROM ASESOR WHERE CORREOELECTRONICO = ? AND IDENTIFICACION = ?";

        try {
            // Búsqueda en la tabla ADMINISTRADOR
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlAdmin);
            ps.setString(1, correo);
            ps.setString(2, identificacion);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si encuentra en ADMINISTRADOR
                RU.setId(rs.getString("ID"));
                RU.setIdentificacion(rs.getString("IDENTIFICACION"));
                RU.setNombre(rs.getString("NOMBRE"));
                RU.setDireccion(rs.getString("DIRECCION"));
                RU.setCorreo(rs.getString("CORREOELECTRONICO"));
                RU.setRol("Administrador");
            } else {
                // Si no encuentra en ADMINISTRADOR, buscar en ASESOR
                ps = con.prepareStatement(sqlAsesor);
                ps.setString(1, correo);
                ps.setString(2, identificacion);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Si encuentra en ASESOR
                    RU.setId(rs.getString("ID"));
                    RU.setIdentificacion(rs.getString("IDENTIFICACION"));
                    RU.setNombre(rs.getString("NOMBRE"));
                    RU.setDireccion(rs.getString("DIRECCION"));
                    RU.setCorreo(rs.getString("CORREOELECTRONICO"));
                    RU.setRol("Asesor");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return RU;
    }
}
