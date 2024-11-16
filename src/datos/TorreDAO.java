package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Torre;

public class TorreDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;

    public ArrayList<Torre> MostrarTorre(int id) {
        ArrayList<Torre> torres = new ArrayList();
        String sql = "select t.id as idTorre, t.numero as numTorre, count(a.id) as cantidadApa "
                + "from torre t "
                + "left join apartamento a "
                + "on t.id = id_torre "
                + "where id_proy = ? "
                + "group by t.id, t.numero ";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Torre torre = new Torre();
                torre.setId(rs.getInt("idTorre"));
                torre.setNombre(rs.getString("numTorre"));
                torre.setCantidadApartamentos(rs.getInt("cantidadApa"));
                torres.add(torre);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return torres;
    }

    public int CantidadTorre() {
        String sql = "select count(*) as torres"
                + "from torre";
        int TotalTorres = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                TotalTorres = rs.getInt("torres");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return TotalTorres;
    }

    public boolean CrearTorre(Torre tr, int idProy) {
        String sql = "insert into torre(id, numero ,id_Proy) "
                + "values (SEQ_IDTORRE.NEXTVAL,?,?)";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tr.getNombre());
            ps.setInt(2, idProy);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error :" + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    public boolean EditarTorre(int idTorre, String nombre) {
        String sql = "update torre "
                + "set numero = ? "
                + "where id = ? ";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, idTorre);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar Torre :" + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public boolean EliminarTorre(int id) {
        String sql = "delete from torre "
                + "where id = ?";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al borrar torre" + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public int ObtenerIdTorreUnica(String nombre) {
        int id = 0;
        String sql = "SELECT id as idTor FROM TORRE "
                + "WHERE UPPER(numero) = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idTor");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener id de la torre: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return id;
    }
}