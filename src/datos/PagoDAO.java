package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import modelo.DatosGrafica;
import modelo.Pago;

public class PagoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;

    public boolean GenerarCuotas(int cuotas, String DiaAPagar, double valor, int idAsesor, int idVenta, int idCliente) {
        String Procedimiento = "CALL generar_fechas_cuotas(?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)";

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        String fechaPago = DiaAPagar + "/" + String.format("%02d", currentMonth) + "/" + currentYear;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareCall(Procedimiento);
            ps.setInt(1, cuotas);
            ps.setString(2, fechaPago);
            ps.setDouble(3, valor);
            ps.setInt(4, idAsesor);
            ps.setInt(5, idVenta);
            ps.setInt(6, idCliente);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al añadir las cuotas: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    public boolean RegistrarPago(int id, Date fechapago) {
        String sql = "UPDATE pago "
                + "SET fechaPago = ? "
                + "WHERE id = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechapago);
            ps.setInt(2, id);
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al pagar el pago: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    public ArrayList<Pago> MostrarCuotas(int id_venta) {
        ArrayList<Pago> cuotas = new ArrayList<>();
        String sql = "SELECT id, valor, FechaPago, FechaVencimiento FROM pago WHERE id_venta = ?";
        LocalDate fechaActual = LocalDate.now();

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_venta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pago cuo = new Pago();
                cuo.setId(rs.getInt("id"));
                cuo.setValor(rs.getDouble("valor"));
                Date fechaPagoSql = rs.getDate("FechaPago");
                Date fechaVencimientoSql = rs.getDate("FechaVencimiento");
                LocalDate fechaVencimiento = fechaVencimientoSql.toLocalDate();
                cuo.setFechaVencimiento(fechaVencimiento);

                if (fechaPagoSql != null) {
                    cuo.setFechaPago(fechaPagoSql.toLocalDate());
                    cuo.setEstado("Pagada");
                } else {
                    if (fechaVencimiento.isBefore(fechaActual)) {
                        cuo.setEstado("Vencido");
                    } else {
                        cuo.setEstado("Pendiente");
                    }
                }

                cuotas.add(cuo);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return cuotas;
    }

    public DatosGrafica DatosGraficaDashboard() {
        String sql = "select fechaPago "
                + "from pago";
        int pagadas = 0;
        int noPagadas = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Date fechapago = rs.getDate("fechaPago");
                if (fechapago != null) {
                    pagadas += 1;
                } else {
                    noPagadas += 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }

        return new DatosGrafica(pagadas, noPagadas);
    }

    public ArrayList<Pago> DatosdelDashboard(int dia) {
        ArrayList<Pago> cuotas = new ArrayList<>();
        String sql = "SELECT p.valor AS valor, p.fechaVencimiento AS fechaVencimiento, "
                + "c.nombre||' '||c.apellido AS nombreCliente "
                + "FROM pago p "
                + "JOIN cliente c ON p.id_cliente = c.id "
                + "WHERE TO_CHAR(p.fechaVencimiento, 'dd') = ?";

        LocalDate fechaActual = LocalDate.now();

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dia);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pago cuo = new Pago();
                cuo.setValor(rs.getDouble("valor"));
                cuo.setIdCliente(rs.getString("nombreCliente"));
                Date fechaVencimientoSql = rs.getDate("fechaVencimiento");
                LocalDate fechaVencimiento = fechaVencimientoSql.toLocalDate();
                cuo.setFechaVencimiento(fechaVencimiento);

                if (fechaVencimiento.isBefore(fechaActual)) {
                    cuo.setEstado("Vencido");
                } else {
                    cuo.setEstado("Pendiente");
                }
                cuotas.add(cuo);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return cuotas;
    }
}
