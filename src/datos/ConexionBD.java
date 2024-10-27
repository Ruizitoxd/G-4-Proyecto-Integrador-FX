package datos;

import java.sql.*;
import java.util.logging.*;

public class ConexionBD {
    private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL correcta para la base de datos Oracle
    //private String url = "jdbc:oracle:thin:@192.168.254.215:1521:orcl"
    public Connection con = null;
    private String user = "constructoraG_4"; // Nombre de usuario de Oracle
    private String password = "constructoraG_4"; // Contraseña de Oracle

    public ConexionBD() {
        try {
            // Establecer la conexión
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Base de datos conectada: " + meta.getDriverName());
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}