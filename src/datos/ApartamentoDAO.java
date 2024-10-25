
package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Apartamento;

public class ApartamentoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();
    
    public List<Apartamento> MostrarApartamento(int idtorre)  {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT a.id as idApa, a.numero as numeroApa, a.valor as valorApa, " +
                     "a.area as AreaApa, a.matricula as matricula " +
                     "FROM apartamento a JOIN torre t ON a.idTorre = t.id " +  // Asegúrate de que 't.id' es el correcto
                     "WHERE a.id_torre = ?";

        try {
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
    
    public int CantidadApartamentos(){
        String sql = " select count(*) as apartamento"
                +"from apartamentos";
        int TotalApartamentos = 0;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                TotalApartamentos = rs.getInt("apartamento");
            }
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        finally {
            conexion.closeConnection();
        }
       return TotalApartamentos;
    }
    
    
    public boolean CrearApartamento(Apartamento a ,int idTorre, int idTipoUnidad) {
        String sql = " insert into apartamento (id,numero, valor,area,matricula,fechaEscritura,id_tipouni,id_torre)"+
                    "values (?,?,?,?,?,?,?,?)";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, a.getId());
            ps.setString(2, a.getNumero());
            ps.setDouble(3, a.getValor());
            ps.setString(4, a.getArea());
            ps.setString(5, a.getMatricula());
            ps.setDate(6, null);
            ps.setInt(7, idTipoUnidad);
            ps.setInt(7, idTorre);
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }   
        }catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
            
    }
    
    public boolean EditarApartamento(int id , String numero, double valor, String area ){
        String sql = "update apartamento"+
                    "set numero = ? , valor = ?, area = ?"+
                    "where id= id";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,numero);
            ps.setDouble(2, valor);
            ps.setString(3, area);
            ps.setInt(4, id);
            ps.executeUpdate();                      
            return true; 
        }catch (SQLException ex) {
            System.out.println("Error al actualizar proyecto: " + ex.getMessage());
            return false;                            
        } finally {
           conexion.closeConnection(); 
        }
    }
    
    public boolean EliminarApartamento(int id){
        String sql ="delete apartamento"+
                    "where id = id";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("Error al borrar proyecto: " + ex.getMessage());
            return false;
        }finally{
            conexion.closeConnection();
        }
    }
}
