package datos;

import controlador.Validar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.RolUsuario;

public class ValidarAsesor implements Validar {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();


    public RolUsuario validar(String correo, String identificacion) {
        RolUsuario RU = new RolUsuario();
        String sqlAsesor = "SELECT ID, IDENTIFICACION, NOMBRE, DIRECCION, CORREOELECTRONICO FROM ASESOR WHERE CORREOELECTRONICO = ? AND IDENTIFICACION = ?";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sqlAsesor);
            ps.setString(1, correo);
            ps.setString(2, identificacion);
            rs = ps.executeQuery();

            if (rs.next()) {
                RU.setId(rs.getString("ID"));
                RU.setIdentificacion(rs.getString("IDENTIFICACION"));
                RU.setNombre(rs.getString("NOMBRE"));
                RU.setDireccion(rs.getString("DIRECCION"));
                RU.setCorreo(rs.getString("CORREOELECTRONICO"));
                RU.setRol("Asesor");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return RU;
    }
}
