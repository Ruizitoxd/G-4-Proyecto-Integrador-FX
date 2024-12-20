package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import modelo.Apartamento;
import modelo.DatosGrafica;

public class ApartamentoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;

    public ArrayList<Apartamento> MostrarApartamentos(int idtorre) {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT a.id as idApa, a.numero as numeroApa, a.valor as valorApa, "
                + "a.area as AreaApa, a.matricula as matricula "
                + "FROM apartamento a JOIN torre t ON a.id_Torre = t.id "
                + "WHERE a.id_torre = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtorre);
            rs = ps.executeQuery();

            while (rs.next()) {
                Apartamento apa = new Apartamento();
                apa.setId(rs.getInt("idApa"));
                apa.setNumero(rs.getString("numeroApa"));
                apa.setValor(rs.getDouble("valorApa"));
                apa.setArea(rs.getString("AreaApa"));
                apa.setMatricula(rs.getString("matricula"));
                apartamentos.add(apa);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return apartamentos;
    }

    public int CantidadApartamentos() {
        String sql = " select count(*) as apartamento from apartamento";
        int TotalApartamentos = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                TotalApartamentos = rs.getInt("apartamento");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return TotalApartamentos;
    }

    public DatosGrafica DatosGraficaMenu() {
        String sql = "SELECT FECHAESCRITURA FROM apartamento";
        int ventas = 0;
        int noVendido = 0;

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Date fechaVentaSql = rs.getDate("FECHAESCRITURA");
                if (fechaVentaSql != null) {
                    ventas += 1;
                } else {
                    noVendido += 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }

        return new DatosGrafica(ventas, noVendido); // Retorna un objeto con los dos valores
    }

    public boolean CrearApartamento(Apartamento a, int idTorre, int idTipoUnidad) {
        String sql = "insert into apartamento (id,numero,valor,area,matricula,fechaEscritura,id_tipouni,id_torre) "
                + "values(SEQ_IDAPARTAMENTO.NEXTVAL,?,?,?,?,?,?,?)";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getNumero());
            ps.setDouble(2, a.getValor());
            ps.setString(3, a.getArea());
            ps.setString(4, a.getMatricula());
            ps.setDate(5, null);
            ps.setInt(6, idTipoUnidad);
            ps.setInt(7, idTorre);
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al añadir el apartamento: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;

    }

    public boolean EditarApartamento(int id, String numero, double valor, String area, int idTipoUnidad) {
        String sql = "update apartamento "
                + "set numero = ? , valor = ?, area = ? , id_tipouni = ?"
                + "where id = ?";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, numero);
            ps.setDouble(2, valor);
            ps.setString(3, area);
            ps.setInt(4, idTipoUnidad);
            ps.setInt(5, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar proyecto: " + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public boolean EliminarApartamento(int id) {
        String sql = "delete apartamento "
                + "where id = ? ";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al borrar apartamento: " + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public ArrayList<String> ObtenerTipoUnidad() {
        ArrayList<String> tipoUnidades = new ArrayList<>();
        String sql = "SELECT nombre as unidad FROM tipounidad";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tipoUnidades.add(rs.getString("unidad"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener los tipos de unidad: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return tipoUnidades;
    }

    public int ObtenerIdTipoUnidad(String nombre) {
        int id = 0;
        String sql = "SELECT id AS idUnidad FROM tipounidad "
                + "WHERE UPPER(nombre) = ?";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("idUnidad");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener el id de la unidad: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return id;
    }

    public ArrayList<Apartamento> ObtenerApartamentosProyecto(int idProy) {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT a.id as idApa, a.numero as numeroApa, a.valor as valorApa, tu.nombre as tipoUnidad, "
                + "a.area as AreaApa, a.matricula as matricula, t.numero as torreNombre "
                + "FROM apartamento a JOIN torre t ON a.id_Torre = t.id JOIN proyecto p ON p.id = t.id_Proy JOIN tipounidad tu ON a.id_tipouni = tu.id "
                + "WHERE p.id = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProy);
            rs = ps.executeQuery();

            while (rs.next()) {
                Apartamento apa = new Apartamento();
                apa.setId(rs.getInt("idApa"));
                apa.setNumero(rs.getString("numeroApa"));
                apa.setValor(rs.getDouble("valorApa"));
                apa.setArea(rs.getString("AreaApa"));
                apa.setMatricula(rs.getString("matricula"));
                apa.setTipoUnidad(rs.getString("tipoUnidad"));
                apa.setIdTorre(rs.getString("torreNombre"));
                apartamentos.add(apa);
            }
        } catch (SQLException ex) {
            System.out.println("Error obteniendo los apartamentos del proyecto: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return apartamentos;
    }

    public ArrayList<Apartamento> ObtenerApartamentosNoVendidos() {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT a.numero as numeroApa, t.numero as torreNombre, p.nombre as nombreProyecto, a.valor as valorApartamento "
                + "FROM apartamento a "
                + "JOIN torre t ON a.id_Torre = t.id "
                + "JOIN proyecto p ON p.id = t.id_Proy "
                + "WHERE a.FechaEscritura IS NULL "
                + "ORDER BY nombreProyecto, torreNombre, numeroApa";  // Filtrar solo los apartamentos no vendidos

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Apartamento apa = new Apartamento();
                apa.setNumero(rs.getString("numeroApa"));
                apa.setIdTorre(rs.getString("torreNombre"));
                apa.setIdProyecto(rs.getString("nombreProyecto"));
                apa.setValor(rs.getDouble("valorApartamento"));
                apartamentos.add(apa);
            }

        } catch (SQLException ex) {
            System.out.println("Error obteniendo los apartamentos no vendidos: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return apartamentos;
    }

    public ArrayList<Apartamento> ObtenerApartamentosVendidos() {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT a.numero as numeroApa, t.numero as torreNombre, a.valor as valorApartamento, a.fechaEscritura as fecha "
                + "FROM apartamento a "
                + "JOIN torre t ON a.id_Torre = t.id "
                + "JOIN proyecto p ON p.id = t.id_Proy "
                + "WHERE a.FechaEscritura IS NOT NULL "
                + "ORDER BY torreNombre, numeroApa, valorApartamento";  // Filtrar solo los apartamentos no vendidos
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Apartamento apa = new Apartamento();
                apa.setNumero(rs.getString("numeroApa"));
                apa.setIdTorre(rs.getString("torreNombre"));
                apa.setValor(rs.getDouble("valorApartamento"));
                Date fechaSql = rs.getDate("fecha");
                LocalDate fecha = fechaSql.toLocalDate();
                apa.setFecha(fecha);
                apartamentos.add(apa);
            }

        } catch (SQLException ex) {
            System.out.println("Error obteniendo los apartamentos vendidos: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return apartamentos;
    }

    public int BuscarApartamentoUnico(String nomApartamento, String nomTorre, String nomProyecto) {
        String sql = "SELECT a.id AS idApar "
                + "FROM apartamento a "
                + "JOIN Torre t ON a.id_torre = t.id "
                + "JOIN Proyecto p ON t.id_proy = p.id "
                + "WHERE UPPER(a.numero) LIKE UPPER(?) AND UPPER(t.numero) LIKE UPPER(?) AND UPPER(p.nombre) LIKE UPPER(?)";
        int idApartamento = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nomApartamento + "%");
            ps.setString(2, "%" + nomTorre + "%");
            ps.setString(3, "%" + nomProyecto + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                idApartamento = rs.getInt("idApar");
            }
        } catch (SQLException ex) {
            System.out.println("Error obteniendo el apartamento del proyecto: " + ex.getMessage());
        } finally {

            conexion.closeConnection();
        }
        return idApartamento;
    }

    public ArrayList<Apartamento> DatosReportes() {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT id, numero, valor, area, matricula "
                + "FROM apartamento "
                + "WHERE fechaEscritura IS NOT NULL "
                + "AND TO_CHAR(fechaEscritura, 'MM/YY') = TO_CHAR(SYSDATE, 'MM/YY')";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Apartamento apa = new Apartamento();
                apa.setId(rs.getInt("id"));
                apa.setNumero(rs.getString("numero"));
                apa.setValor(rs.getDouble("valor"));
                apa.setArea(rs.getString("area"));
                apa.setMatricula(rs.getString("matricula"));
                apartamentos.add(apa);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return apartamentos;
    }
}
