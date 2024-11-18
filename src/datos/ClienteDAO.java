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

    public boolean CrearCliente(String nombre, String apellido, String direccion, String identificacion, Double subsidio, String correoElectronico, String sisben) {
        String sql = "insert into cliente (id, identificacion, nombre, apellido, SISBEN, subsidio, direccion, correoElectronico) "
                + "values(SEQ_IDCLIENTE.NEXTVAL, ?,?,?,?,?,?,?)";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, identificacion);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, sisben);
            ps.setDouble(5, subsidio);
            ps.setString(6, direccion);
            ps.setString(7, correoElectronico);
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al añadir al cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    public int BuscarCliente(String cedula) {
        String sql = "select id "
                + "from cliente "
                + "where identificacion = ?";
        int id_cliente = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                id_cliente = rs.getInt("id");
                return id_cliente;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar el cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return id_cliente;
    }

    public boolean numeroDeTelefonoDelCliente(int id_cliente, String numero) {
        String sql = "INSERT INTO telefono_cliente (id, telefono, id_cliente) "
                + "VALUES (SEQ_IDTELEFO_CLIENTE.NEXTVAL, ?, ?)";
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
        String sql = "SELECT SISBEN "
                + "FROM Cliente "
                + "WHERE id = ?";
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

    public String consultarNombre(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT nombre||' '||apellido AS nombreCompleto "
                + "FROM cliente "
                + "WHERE id = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("nombreCompleto");
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar el nombre del cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return "";
    }

    public double consultarSubsidio(int idCliente) {
        String sql = "SELECT subsidio "
                + "FROM Cliente "
                + "WHERE id = ?";
        double subsidio = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                subsidio = rs.getDouble("subsidio");
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar subsidio del cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return subsidio;
    }

    public String consultarNumero(int idCliente) {
        String sql = "SELECT tc.telefono as celular "
                + "FROM TELEFONO_CLIENTE tc JOIN CLIENTE c ON c.ID = tc.ID_CLIENTE "
                + "WHERE c.ID  = ?";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("celular");
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar telefono del cliente: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return "";
    }
}
