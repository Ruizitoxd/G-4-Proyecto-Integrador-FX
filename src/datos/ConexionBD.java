package datos;

import java.sql.*;
import java.util.logging.*;

public class ConexionBD {
    private String url = "jdbc:oracle:thin:constructoraG_4/constructoraG_4@localhost:1521:orcl"; // URL correcta para la base de datos Oracle
    public Connection con = null;
    //private String user = "constructoraG_4"; // Nombre de usuario de Oracle
    //private String password = "constructoraG_4"; // Contraseña de Oracle

    public ConexionBD() {
        try {
            // Establecer la conexión
            con = DriverManager.getConnection(url);
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
    
    public static void main(String[] args) {
        // Crear una instancia de la clase ConexionBD
        ConexionBD conexionBD = new ConexionBD();

        // Verificar si la conexión se ha establecido
        if (conexionBD.getConnection() != null) {
            System.out.println("La conexión a la base de datos se estableció con éxito.");
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }

        // Cerrar la conexión
        conexionBD.closeConnection();
    }
}
