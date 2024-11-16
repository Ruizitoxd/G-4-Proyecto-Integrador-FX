package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Venta;

public class VentaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion;

    public ArrayList<Venta> MostrarVentas(int id_asesor) {
        ArrayList<Venta> ventas = new ArrayList();
        String sql = "select v.id as idVenta, v.valortotal as valor , v.numcuotas as numCuotas, v.intereses as interes, v.id_aparta as idApartamento "
                + "from venta v "
                + "join asesor a on v.id_asesor = a.id "
                + "where id_asesor = ?";
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_asesor);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("idVenta"));
                venta.setValor(rs.getDouble("valor"));
                venta.setInteres(rs.getDouble("interes"));
                venta.setNumCuotas(rs.getInt("numCuotas"));
                venta.setIdApartamento(rs.getString("idApartamento"));
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println("Error al mostrar las ventas: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return ventas;
    }

    public int CantidadVentas() {
        String sql = "select count(*) as cantidadVentas "
                + "from venta";
        int TotalVentas = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                TotalVentas = rs.getInt("cantidadVentas");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la cantidad de ventas: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return TotalVentas;
    }

    public double CantidadGanancias() {
        String sql = "select SUM(valortotal) as ganancias "
                + "from venta";
        double ganancias = 0;
        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                ganancias = rs.getDouble("ganancias");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener las ganancias: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return ganancias;
    }

    public boolean CrearVenta(Venta vt,int idApartamento, int idAsesor, int idCliente) {
        String sql = "insert into venta(id, valortotal, numcuotas, intereses, id_aparta, id_asesor, id_cliente) "
                + "values (SEQ_IDVENTA.NEXTVAL, ?,?,?,?,?,?)";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, vt.getValor());          // valor total de la venta
            ps.setInt(2, vt.getNumCuotas());         // número de cuotas
            ps.setDouble(3, vt.getInteres());        // interés
            ps.setInt(4, idApartamento);                     // id del apartamento
            ps.setInt(5, idAsesor);
            ps.setInt(6, idCliente);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al crear venta:" + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    public boolean EditarVenta(int idVenta, double valor, int cantCuotas, double interes) {
        String sql = "update venta "
                + "set valortotal = ? , numcuotas = ? , intereses = ? "
                + "where id = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setInt(2, cantCuotas);
            ps.setDouble(3, interes);
            ps.setInt(4, idVenta);

            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la venta:" + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }

    public boolean EliminarVenta(int idVenta) {
        String sql = "delete from venta "
                + "where id = ?";

        try {
            conexion = new ConexionBD();
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al borrar la venta" + ex.getMessage());
            return false;
        } finally {
            conexion.closeConnection();
        }
    }
}
