package datos;

import java.sql.*;
import java.util.logging.*;

public class ConexionBD {

    private String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL correcta para la base de datos Oracle
    //private String url = "jdbc:oracle:thin:@192.168.254.215:1521:orcl"
    public Connection con = null;
    private String user = "constructoraG_4"; // Nombre de usuario de Oracle
    private String password = "constructoraG_4"; // Contraseña de Oracle
    
    private String adminUser = "admin"; // Usuario para tabla admin
    private String adminPassword = "admin"; // Contraseña para admin

    private String asesorUser = "asesor"; // Usuario para tabla asesor
    private String asesorPassword = "asesor"; // Contraseña para asesor

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
    
    // Método para obtener conexión como admin
    public Connection getAdminConnection() {
        try {
            Connection adminCon = DriverManager.getConnection(url, adminUser, adminPassword);
            System.out.println("Conexión establecida como admin.");
            return adminCon;
        } catch (SQLException ex) {
            System.out.println("Error al conectar como admin: " + ex.getMessage());
            return null;
        }
    }

    // Método para obtener conexión como asesor
    public Connection getAsesorConnection() {
        try {
            Connection asesorCon = DriverManager.getConnection(url, asesorUser, asesorPassword);
            System.out.println("Conexión establecida como asesor.");
            return asesorCon;
        } catch (SQLException ex) {
            System.out.println("Error al conectar como asesor: " + ex.getMessage());
            return null;
        }
    }
    
}
