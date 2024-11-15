package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Proyecto;

public class ProyectoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;

    public ArrayList<Proyecto> MostrarProyectos(int idAdmin) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT p.id as idProy, p.nombre as nombreProy, COUNT(t.id) as cantidadTor "
                + "FROM proyecto p "
                + "LEFT JOIN torre t ON p.id = t.id_proy "
                + "WHERE p.id_admin = ? "
                + "GROUP BY p.id, p.nombre";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAdmin);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setId(rs.getInt("idProy"));
                proyecto.setNombre(rs.getString("nombreProy"));
                proyecto.setCantidadTorres(rs.getInt("cantidadTor"));
                proyectos.add(proyecto);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return proyectos;
    }

    public int CantidadProyectos() {
        String sql = "select count(*) as proyectos from proyecto";
        int TotalProyectos = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                TotalProyectos = rs.getInt("proyectos");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return TotalProyectos;
    }

    public boolean CrearProyecto(Proyecto pr, int idAdmin) {
        String sql = "INSERT INTO constructoraG_4.proyecto(id, nombre, id_admin) "
                + "VALUES (SEQ_IDPROYECTO.NEXTVAL, ?, ?)";

        try {
            conexion = new ConexionBD();
            con = conexion.getAdminConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNombre());
            ps.setInt(2, idAdmin);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    public boolean EditarProyecto(int id, String nombre) {
        String sql = "UPDATE proyecto "
                + "SET nombre = ? "
                + "WHERE id = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar proyecto: " + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public boolean EliminarProyecto(int id) {
        String sql = "delete from proyecto "
                + "where id = ?";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al borrar proyecto: " + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public int ObtenerIdProyectoUnico(String nombre) {
        int id = 0;
        String sql = "SELECT id as idProy FROM PROYECTO "
                + "WHERE UPPER(nombre) = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idProy");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener id del proyecto: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return id;
    }
}
